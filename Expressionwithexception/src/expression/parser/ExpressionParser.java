/**
 * Created by HP on 23.03.2017.
 */
package expression.parser;

import expression.*;
import expression.exceptions.Parser;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class ExpressionParser implements Parser {
    private String expression;
    private int pointer;

    public TripleExpression parse(String expression) throws Exception {
        pointer = 0;
        this.expression = expression.replaceAll("\\p{javaWhitespace}", "");
        TripleExpression result = addOrSub();
        if (pointer != this.expression.length()) {
            throw new ParsingException(pointer);
        }
        return result;
    }

    private TripleExpression addOrSub() throws Exception {
        TripleExpression ans = mulOrDiv();
        while (pointer < expression.length()) {
            if (expression.charAt(pointer) == '+') {
                pointer++;
                ans = new CheckedAdd(ans, mulOrDiv());
            } else if (expression.charAt(pointer) == '-') {
                pointer++;
                ans = new CheckedSubtract(ans, mulOrDiv());
            } else {
                break;
            }
        }
        return ans;
    }

    private TripleExpression mulOrDiv() throws Exception {
        TripleExpression ans = unaryOperator();
        while (pointer < expression.length()) {
            if (expression.charAt(pointer) == '*') {
                pointer++;
                ans = new CheckedMultiply(ans, unaryOperator());
            } else if (expression.charAt(pointer) == '/') {
                pointer++;
                ans = new CheckedDivide(ans, unaryOperator());
            } else {
                break;
            }
        }
        return ans;
    }

    private TripleExpression constOrVar() throws ParsingException {
        TripleExpression ans;
        if (Character.isAlphabetic(expression.charAt(pointer))) {
            try {
                ans = new Variable(Character.toString(expression.charAt(pointer)));
            } catch (Exception e) {
                throw new ParsingException(pointer);
            }
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


    /*private String[] functionsName = new String[]{"abs", "square"};
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
    }*/


    private TripleExpression unaryOperator() throws Exception {
        if (expression.charAt(pointer) == '(') {
            pointer++;
            TripleExpression ans = addOrSub();
            if (expression.charAt(pointer) != ')') {
                throw new ParsingException(pointer);
            }
            pointer++;
            return ans;
        } else if (expression.charAt(pointer) == '-') {
            if (pointer + 1 < expression.length() && Character.isDigit(expression.charAt(pointer + 1))) {
                return constOrVar();
            } else {
                pointer++;
                return new CheckedNegate(unaryOperator());
            }
        } else {
            return constOrVar();
        }
    }
}