package expression;

import expression.exceptions.*;

/**
 * Created by daminovn on 03.04.2017.
 */
public class CheckedSqrt implements TripleExpression {
    private TripleExpression val;

    public CheckedSqrt(TripleExpression value) {
        val = value;
    }

    @Override
    public int evaluate(int x, int y, int z) throws MyExceptions {
        int result = val.evaluate(x, y, z);
        if (result < 0) {
            throw new FunctionExceptions("sqrt of negative expression");
        }
        int l = 0;
        int r = 46340 + 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (m * m > result) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r - 1;
    }
}
