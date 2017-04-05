/**
 * Created by HP on 22.03.2017.
 */

package expression;

import expression.exceptions.*;


public class CheckedMultiply extends BinaryOperator {
    public CheckedMultiply(TripleExpression frst, TripleExpression scnd) {
        super(frst, scnd);
    }

    private void check(int a, int b) throws MyExceptions {
        if (a < b) {
            check(b, a);
        } else if (b > 0) {
            if (b > Integer.MAX_VALUE / a) {
                throw new OverflowException();
            }
        } else if (a > 0) {
            if (b < Integer.MIN_VALUE / a) {
                throw new OverflowException();
            }
        } else if (a < 0) {
            if (a < Integer.MAX_VALUE / b) {
                throw new OverflowException();
            }
        }
    }

    @Override
    protected int apply(int a, int b) throws MyExceptions {
        check(a, b);
        return a * b;
    }

}
