package expression;

import expression.exceptions.MyExceptions;
import expression.exceptions.OverflowException;

/**
 * Created by daminovn on 09.04.2017.
 */
public class Min extends BinaryOperator {
    public Min(TripleExpression frst, TripleExpression scnd ) {
        super(frst,scnd);
    }

    @Override
    protected int apply(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }
}
