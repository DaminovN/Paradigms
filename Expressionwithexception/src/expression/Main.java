/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.*;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws MyExceptions {
        final Parser parser = new ExpressionParser();
        String expression = "5max y";
        //337591585
        System.out.println(new ExpressionParser().parse(expression).evaluate(0, 0, 0));
    }
}
