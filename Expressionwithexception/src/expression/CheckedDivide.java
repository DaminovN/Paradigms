/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.*;

public class CheckedDivide extends BinaryOperator {
    public CheckedDivide(TripleExpression frst, TripleExpression scnd) {
        super(frst, scnd);
    }

    private void check(int a, int b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        } else if (b == -1 && a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int a, int b) throws MyExceptions {
        check(a, b);
        return a / b;
    }

}
