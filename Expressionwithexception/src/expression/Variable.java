/**
 * Created by HP on 22.03.2017.
 */

package expression;

import expression.TripleExpression;
import expression.exceptions.*;

public class Variable implements TripleExpression {
    private String name;

    public Variable(String nm) throws ParsingException {
        name = nm;
    }

    @Override
    public int evaluate(int x, int y, int z) throws ParsingException {
        System.out.println(name + "HERE");
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else if (name.equals("z")) {
            return z;
        } else {
            throw new ParsingException();
        }
    }
}
