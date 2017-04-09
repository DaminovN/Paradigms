/**
 * Created by HP on 23.03.2017.
 */
package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class ExpressionParser implements Parser {
    private String expression;
    private int pointer;

    public TripleExpression parse(String expression) throws MyExceptions {
        pointer = 0;
        //this.expression = expression.replaceAll("\\p{javaWhitespace}", "");
        this.expression = expression;
        TripleExpression result = minOrMax();
        if (pointer != this.expression.length()) {
            throw new ParsingException(pointer);
        }
        return result;
    }

    private String[] operations = new String[]{"minmax", "+-", "*/"};

    private int getOperator(int id, int len) {
        if (pointer + len <= expression.length()
                && expression.substring(pointer, pointer + len).equals(operations[id].substring(0, len))) {
            return id * 2 + 0;
        } else if (pointer + len <= expression.length()
                && expression.substring(pointer, pointer + len).equals(operations[id].substring(len, len + len))) {
            return id * 2 + 1;
        } else {
            return -1;
        }
    }

    private ArrayList<BiFunction<TripleExpression, TripleExpression, TripleExpression>> opApplier = new ArrayList<>();

    {
        opApplier.add((x, y) -> new Min(x, y));
        opApplier.add((x, y) -> new Max(x, y));
        opApplier.add((x, y) -> new CheckedAdd(x, y));
        opApplier.add((x, y) -> new CheckedSubtract(x, y));
        opApplier.add((x, y) -> new CheckedMultiply(x, y));
        opApplier.add((x, y) -> new CheckedDivide(x, y));
    }

    private TripleExpression minOrMax() throws MyExceptions {
        TripleExpression ans = addOrSub();
        while (pointer < expression.length()) {
            if (Character.isWhitespace(expression.charAt(pointer))) {
                pointer++;
                continue;
            }
            int op = getOperator(0, 3);
            if (op != -1) {
                if (!(pointer == 0 || Character.isWhitespace(expression.charAt(pointer - 1))
                        || expression.charAt(pointer - 1) == ')' || Character.isDigit(expression.charAt(pointer - 1)))) {
                    throw new ParsingException(pointer);
                }
                pointer += 3;
                if (pointer < expression.length() && expression.charAt(pointer) != '('
                        && !Character.isWhitespace(expression.charAt(pointer)) && expression.charAt(pointer) != '-') {
                    throw new ParsingException(pointer);
                }
                ans = opApplier.get(op).apply(ans, addOrSub());
            } else {
                break;
            }
        }
        return ans;
    }

    private TripleExpression addOrSub() throws MyExceptions {
        TripleExpression ans = mulOrDiv();
        while (pointer < expression.length()) {
            if (Character.isWhitespace(expression.charAt(pointer))) {
                pointer++;
                continue;
            }
            int op = getOperator(1, 1);
            if (op != -1) {
                pointer++;
                ans = opApplier.get(op).apply(ans, mulOrDiv());
            } else {
                break;
            }
        }
        return ans;
    }


    private TripleExpression mulOrDiv() throws MyExceptions {
        TripleExpression ans = unaryOperator();
        while (pointer < expression.length()) {
            if (Character.isWhitespace(expression.charAt(pointer))) {
                pointer++;
                continue;
            }
            int op = getOperator(2, 1);
            if (op != -1) {
                pointer++;
                ans = opApplier.get(op).apply(ans, unaryOperator());
            } else {
                break;
            }
        }
        return ans;
    }


    private String[] functionsName = new String[]{"abs", "sqrt"};
    private ArrayList<UnaryOperator<TripleExpression>> funcApplier = new ArrayList<>();
    {
        funcApplier.add(x -> new CheckedAbs(x));
        funcApplier.add(x -> new CheckedSqrt(x));
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


    private TripleExpression unaryOperator() throws MyExceptions {
        pointer = skipWhitespace(pointer);
        if (pointer < expression.length() && expression.charAt(pointer) == '(') {
            pointer++;
            TripleExpression ans = minOrMax();
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
                return new CheckedNegate(unaryOperator());
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

    private TripleExpression constOrVar() throws MyExceptions {
        TripleExpression ans;
        pointer = skipWhitespace(pointer);
        if (pointer < expression.length() && Character.isAlphabetic(expression.charAt(pointer))) {
            ans = new Variable(Character.toString(expression.charAt(pointer)), pointer);
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
                } else if (Character.isDigit(expression.charAt(pointer))) {
                    number += expression.charAt(pointer);
                    pointer++;
                } else {
                    break;
                }
            }
            try {
                ans = new Const(Integer.parseInt(number));
            } catch (Exception e) {
                throw new ParsingException(sp);
            }
        } else {
            throw new ParsingException(pointer);
        }
        return ans;
    }

    private int skipWhitespace(int curPointer) {
        while (curPointer < expression.length() && Character.isWhitespace(expression.charAt(curPointer))) {
            curPointer++;
        }
        return curPointer;
    }
}