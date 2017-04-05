/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.*;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws MyExceptions {

//        String expression = "(square(-1305951089)*square1929567999)";
        String expression = "2 * A 2";
        // answer = -788641311
        System.out.println(new ExpressionParser().parse(expression).evaluate(2, 0, 0));
    }
}
