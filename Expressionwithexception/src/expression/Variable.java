/**
 * Created by HP on 22.03.2017.
 */

package expression;

import expression.TripleExpression;
import expression.exceptions.*;

public class Variable implements TripleExpression {
    private String name;
    private int pos;
    public Variable(String nm) {
        name = nm;
        pos = 0;
    }
    public Variable(String nm, int pointer) throws MyExceptions {
        name = nm;
        pos = pointer;
        if (!name.equals("x") && !name.equals("y") && !name.equals("z")) {
            throw new ParsingException(pos);
        }
    }
    @Override
    public int evaluate(int x, int y, int z) throws ParsingException {
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else if (name.equals("z")) {
            return z;
        } else {
            throw new ParsingException(pos);
        }
    }
}
