/**
 * Created by HP on 23.03.2017.
 */
package expression.parser;

import expression.*;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class ExpressionParser implements Parser {
    private String expression;
    private int pointer;

    public TripleExpression parse(String expression) {
        pointer = 0;
        this.expression = expression.replaceAll("\\p{javaWhitespace}", "");
        return shifts();
    }

    private TripleExpression shifts() {
        TripleExpression ans = addOrSub();
        while (pointer < expression.length()) {
            if (pointer + 1 < expression.length()) {
                if (expression.charAt(pointer) == '<' && expression.charAt(pointer + 1) == '<') {
                    pointer += 2;
                    ans = new ShiftLeft(ans, addOrSub());
                } else if (expression.charAt(pointer) == '>' && expression.charAt(pointer + 1) == '>') {
                    pointer += 2;
                    ans = new ShiftRight(ans, addOrSub());
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return ans;
    }

    private TripleExpression addOrSub() {
        TripleExpression ans = mulOrDiv();
        while (pointer < expression.length()) {
            if (expression.charAt(pointer) == '+') {
                pointer++;
                ans = new Add(ans, mulOrDiv());
            } else if (expression.charAt(pointer) == '-') {
                pointer++;
                ans = new Subtract(ans, mulOrDiv());
            } else {
                break;
            }
        }
        return ans;
    }

    private TripleExpression mulOrDiv() {
        TripleExpression ans = unaryOperator();
        while (pointer < expression.length()) {
            if (expression.charAt(pointer) == '*') {
                pointer++;
                ans = new Multiply(ans, unaryOperator());
            } else if (expression.charAt(pointer) == '/') {
                pointer++;
                ans = new Divide(ans, unaryOperator());
            } else {
                break;
            }
        }
        return ans;
    }

    private TripleExpression constOrVar() {
        TripleExpression ans;
        if (Character.isAlphabetic(expression.charAt(pointer))) {
            ans = new Variable(Character.toString(expression.charAt(pointer)));
            pointer++;
        } else {
            int sp = pointer;
            if (expression.charAt(pointer) == '-') {
                pointer++;
            }
            while (pointer < expression.length() && Character.isDigit(expression.charAt(pointer))) {
                pointer++;
            }
            ans = new Const(Integer.parseInt(expression.substring(sp, pointer)));
        }
        return ans;
    }


    private String[] functionsName = new String[]{"abs", "square"};
    private ArrayList<UnaryOperator<TripleExpression>> funcApplier = new ArrayList<>();

    {
        funcApplier.add(x -> new Abs(x));
        funcApplier.add(x -> new Square(x));
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

    private TripleExpression unaryOperator() {
        if (expression.charAt(pointer) == '(') {
            pointer++;
            TripleExpression ans = shifts();
            assert expression.charAt(pointer) == ')' : "Parse Problem";
            pointer++;
            return ans;
        } else if (expression.charAt(pointer) == '-') {
            if (pointer + 1 < expression.length() && Character.isDigit(expression.charAt(pointer + 1))) {
                return constOrVar();
            } else {
                pointer++;
                return new Negate(unaryOperator());
            }
        } else {
            int pos = getFunctionIndex();
            if (pos != -1) {
                pointer += functionsName[pos].length();
                return funcApplier.get(pos).apply(unaryOperator());
            } else {
                return constOrVar();
            }
        }
    }
}
