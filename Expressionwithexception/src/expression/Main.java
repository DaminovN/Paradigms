/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws Exception {

//        String expression = "(square(-1305951089)*square1929567999)";
        String expression = "-2147483648";
        // answer = -788641311
        System.out.println(new ExpressionParser().parse(expression).evaluate(0, 0, 0));
    }
}
