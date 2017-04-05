/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.*;

public abstract class BinaryOperator implements TripleExpression {
    protected TripleExpression first;
    protected TripleExpression second;
    protected BinaryOperator(TripleExpression frst, TripleExpression scnd) {
        first = frst;
        second = scnd;
    }
    
    protected abstract int apply(int a, int b) throws MyExceptions;

    public int evaluate(int x, int y, int z) throws MyExceptions {
        return apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
