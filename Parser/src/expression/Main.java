/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String expression = "(square(-1305951089)*square1929567999)";
        // answer = -788641311
        int a = -1305951089;
        int b = 1929567999;
        System.out.println("a*a = " + a*a);
        System.out.println("b*b = " + b*b);
        System.out.println(a*a*b*b);
        System.out.println(new ExpressionParser().parse(expression).evaluate(2, 2, 2));
    }
}
