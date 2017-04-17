/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.MyExceptions;
import expression.exceptions.OverflowException;

public class CheckedAdd<T extends Type<T, ? extends Number>> extends BinaryOperator<T> {
    public CheckedAdd(TripleExpression<T> frst, TripleExpression<T> scnd ) {
        super(frst,scnd);
    }

    @Override
    protected T apply(T a, T b) throws MyExceptions {
        return a.add(b);
    }
}
