package expression;

import expression.exceptions.*;

/**
 * Created by daminovn on 04.04.2017.
 */
public class CheckedNegate implements TripleExpression {
    private TripleExpression val;
    public CheckedNegate(TripleExpression value) {
        val = value;
    }

    private void check(int a) throws MyExceptions {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws MyExceptions {
        int a = val.evaluate(x, y, z);
        check(a);
        return -a;
    }
}
