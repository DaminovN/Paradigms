/**
 * Created by HP on 23.03.2017.
 */
package expression.parser;

import expression.*;
import expression.exceptions.MyExceptions;
import expression.exceptions.Parser;
import expression.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class ExpressionParser<T extends Type<T,? extends Number>> implements Parser {
    private String expression;
    private int pointer;
    private T helper;


    public ExpressionParser(T hlp) {
        helper = hlp;
    }

    public TripleExpression<T> parse(String expression) throws MyExceptions {
        pointer = 0;
        //this.expression = expression.replaceAll("\\p{javaWhitespace}", "");
        this.expression = expression;
        //TripleExpression result = minOrMax();
        TripleExpression<T> result = addOrSub();
        if (pointer != this.expression.length()) {
            throw new ParsingException(pointer);
        }
        return result;
    }

    private String[] operations = new String[]{"+-", "*/", "modmod"};

    private int getOperator(int id, int len) {
        if (pointer + len <= expression.length()
                && expression.substring(pointer, pointer + len).equals(operations[id].substring(0, len))) {
            return id * 2;
        } else if (pointer + len <= expression.length()
                && expression.substring(pointer, pointer + len).equals(operations[id].substring(len, len + len))) {
            return id * 2 + 1;
        } else {
            return -1;
        }
    }

    private ArrayList<BiFunction<TripleExpression<T>, TripleExpression<T>, TripleExpression<T>>> opApplier = new ArrayList<>();

    {
        opApplier.add((x, y) -> new CheckedAdd<T>(x, y));
        opApplier.add((x, y) -> new CheckedSubtract<T>(x, y));
        opApplier.add((x, y) -> new CheckedMultiply<T>(x, y));
        opApplier.add((x, y) -> new CheckedDivide<T>(x, y));
        opApplier.add((x, y) -> new CheckedMod<T>(x, y));
    }

    private TripleExpression<T> addOrSub() throws MyExceptions {
        TripleExpression<T> ans = mulOrDiv();
        while (pointer < expression.length()) {
            if (Character.isWhitespace(expression.charAt(pointer))) {
                pointer++;
                continue;
            }
            int op = getOperator(0, 1);
            if (op != -1) {
                pointer++;
                ans = opApplier.get(op).apply(ans, mulOrDiv());
            } else {
                break;
            }
        }
        return ans;
    }


    private TripleExpression<T> mulOrDiv() throws MyExceptions {
        TripleExpression<T> ans = unaryOperator();
        while (pointer < expression.length()) {
            if (Character.isWhitespace(expression.charAt(pointer))) {
                pointer++;
                continue;
            }
            int op = getOperator(1, 1);
            if (op != -1) {
                pointer++;
                ans = opApplier.get(op).apply(ans, unaryOperator());
            } else {
                op = getOperator(2, 3);
                if (op != -1) {
                    pointer += 3;
                    ans = opApplier.get(op).apply(ans, unaryOperator());
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    private String[] functionsName = new String[]{"abs", "square"};
    private ArrayList<UnaryOperator<TripleExpression<T>>> funcApplier = new ArrayList<>();
    {
        funcApplier.add(x -> new CheckedAbs<T>(x));
        funcApplier.add(x -> new CheckedSquare<T>(x));
    }

    private int getFunctionIndex() {
        for (int i = 0; i < functionsName.length; i++) {
            String s = functionsName[i];
            if (pointer + s.length() <= expression.length() &&
                    expression.substring(pointer, pointer + s.length()).equals(s)) {
                return i;
            }
        }
        return -1;
    }


    private TripleExpression<T> unaryOperator() throws MyExceptions {
        pointer = skipWhitespace(pointer);
        if (pointer < expression.length() && expression.charAt(pointer) == '(') {
            pointer++;
            TripleExpression<T> ans = addOrSub();
            if (pointer >= expression.length() || expression.charAt(pointer) != ')') {
                throw new ParsingException(pointer);
            }
            pointer++;
            return ans;
        } else if (pointer < expression.length() && expression.charAt(pointer) == '-') {
            int sp = skipWhitespace(pointer + 1);
            if (sp < expression.length() && Character.isDigit(expression.charAt(sp))) {
                return constOrVar();
            } else {
                pointer++;
                return new CheckedNegate<T>(unaryOperator());
            }
        } else {
            int pos = getFunctionIndex();
            if (pos != -1) {
                pointer += functionsName[pos].length();
                if (pointer < expression.length() && expression.charAt(pointer) != '('
                        && !Character.isWhitespace(expression.charAt(pointer)) && expression.charAt(pointer) != '-') {
                    throw new ParsingException(pointer);
                }
                return funcApplier.get(pos).apply(unaryOperator());
            } else {
                return constOrVar();
            }
        }
    }

    private TripleExpression<T> constOrVar() throws MyExceptions {
        TripleExpression<T> ans;
        pointer = skipWhitespace(pointer);
        if (pointer < expression.length() && Character.isAlphabetic(expression.charAt(pointer))) {
            ans = new Variable<T>(Character.toString(expression.charAt(pointer)), pointer);
            pointer++;
        } else if (pointer < expression.length()) {
            int sp = pointer;
            String number = "";
            if (expression.charAt(pointer) == '-') {
                number += expression.charAt(pointer);
                pointer++;
            }
            while (pointer < expression.length()) {
                if (Character.isWhitespace(expression.charAt(pointer))) {
                    pointer++;
                } else if (Character.isDigit(expression.charAt(pointer)) || expression.charAt(pointer) == 'e'
                        || expression.charAt(pointer) == '.') {
                    number += expression.charAt(pointer);
                    pointer++;
                } else {
                    break;
                }
            }
            try {
                ans = new Const<T>(constant(number));
            } catch (MyExceptions e) {
                throw new ParsingException(sp);
            }
        } else {
            throw new ParsingException(pointer);
        }
        return ans;
    }

    private T constant(String number) throws MyExceptions {
        return helper.toConst(number);
    }

    private int skipWhitespace(int curPointer) {
        while (curPointer < expression.length() && Character.isWhitespace(expression.charAt(curPointer))) {
            curPointer++;
        }
        return curPointer;
    }
}