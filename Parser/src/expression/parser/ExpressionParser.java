/**
 * Created by HP on 23.03.2017.
 */
package expression.parser;

import expression.*;

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
            int value = 0;
            while (pointer < expression.length() && Character.isDigit(expression.charAt(pointer))) {
                value = value * 10 + Integer.parseInt(String.valueOf(expression.charAt(pointer)));
                pointer++;
            }
            ans = new Const(value);
        }
        return ans;
    }

    private TripleExpression unaryOperator() {
        TripleExpression ans;
        if (expression.charAt(pointer) == '(') {
            pointer++;
            ans = shifts();
            assert expression.charAt(pointer) == ')' : "Parse Problem";
            pointer++;
        } else if (expression.charAt(pointer) == '-') {
            pointer++;
            ans = new Multiply(new Const(-1), unaryOperator());
        } else if (pointer + 2 < expression.length() && expression.substring(pointer, pointer + 3).equals("abs")) {
            pointer += 3;
            if (!Character.isDigit(expression.charAt(pointer)) && expression.charAt(pointer) != 'x'
                    && expression.charAt(pointer) != 'y' && expression.charAt(pointer) != 'z') {
                ans = new Abs(unaryOperator());
            } else {
                ans = new Abs(constOrVar());
            }
        } else if (pointer + 5 < expression.length() && expression.substring(pointer, pointer + 6).equals("square")) {
            pointer += 6;
            if (!Character.isDigit(expression.charAt(pointer)) && expression.charAt(pointer) != 'x'
                    && expression.charAt(pointer) != 'y' && expression.charAt(pointer) != 'z') {
                ans = new Square(unaryOperator());
            } else {
                ans = new Square(constOrVar());
            }
        } else{
            ans = constOrVar();
        }
        return ans;
    }
}
