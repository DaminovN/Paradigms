package expression;

import expression.exceptions.MyExceptions;
import expression.exceptions.OverflowException;

/**
 * Created by daminovn on 04.04.2017.
 */
public class CheckedNegate<T extends Type<T, ? extends Number>> implements TripleExpression<T> {
    private TripleExpression<T> val;
    public CheckedNegate(TripleExpression<T> value) {
        val = value;
    }

    public T evaluate(T x, T y, T z) throws MyExceptions {
        T a = val.evaluate(x, y, z);
        return a.negate();
    }
}
