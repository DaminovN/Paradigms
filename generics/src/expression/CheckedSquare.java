package expression;

import expression.TripleExpression;
import expression.exceptions.MyExceptions;

/**
 * Created by daminovn on 12.04.2017.
 */
public class CheckedSquare<T extends Type<T, ? extends Number>> implements TripleExpression<T> {
    private TripleExpression<T> val;

    public CheckedSquare(TripleExpression<T> value) {
        val = value;
    }
    @Override
    public T evaluate(T x, T y, T z) throws MyExceptions {
        T res = val.evaluate(x, y, z);
        return res.square();
    }
}
