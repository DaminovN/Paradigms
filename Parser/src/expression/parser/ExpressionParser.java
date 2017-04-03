/**
 * Created by HP on 23.03.2017.
 */
package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    private String expression;
    private int pointer;

    public AnyExpression parse(String expression) {
        pointer = 0;
        this.expression = expression.replaceAll("\\p{javaWhitespace}", "");
        return addOrSub();
    }

    private AnyExpression shifts() {
        AnyExpression and = addOrSub();
        while (pointer < expression.length()) {
            if (pointer + 1 < expression.length()) {
                if (expression.charAt(pointer) == '<' && expression.charAt(pointer + 1) == '<') {

                }
            }
        }
    }

    private AnyExpression addOrSub() {
        AnyExpression ans = mulOrDiv();
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

    private AnyExpression mulOrDiv() {
        AnyExpression ans = unaryOperator();
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

    private AnyExpression constOrVar() {
        AnyExpression ans;
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

    private AnyExpression unaryOperator() {
        AnyExpression ans;
        if (expression.charAt(pointer) == '(') {
            pointer++;
            ans = addOrSub();
            assert expression.charAt(pointer) == ')' : "Parse Problem";
            pointer++;
        } else if (expression.charAt(pointer) == '-') {
            pointer++;
            ans = new Multiply(new Const(-1), unaryOperator());
        } else {
            ans = constOrVar();
        }
        return ans;
    }
}
