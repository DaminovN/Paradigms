/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String expression = "1+(2*2 - 3*x*x)";
        //System.out.println(new ExpressionParser().parse(expression).evaluate(2));
    }
}
