/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.MyExceptions;
import expression.exceptions.Parser;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws MyExceptions {
        //String expression = "x + y mod (z + 1)";
        String expression = "-14 + -16 mod (2 + 1)";
        int res = new ExpressionParser<CheckedInteger>(new CheckedInteger())
                .parse(expression).evaluate(new CheckedInteger(-14),
                        new CheckedInteger(-16), new CheckedInteger(2)).take();
        System.out.println(res);
    }
}
