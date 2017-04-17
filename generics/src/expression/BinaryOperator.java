/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.MyExceptions;

public abstract class BinaryOperator<T extends Type<T, ? extends Number>> implements TripleExpression<T> {
    protected TripleExpression<T> first;
    protected TripleExpression<T> second;
    protected BinaryOperator(TripleExpression<T> frst, TripleExpression<T> scnd) {
        first = frst;
        second = scnd;
    }
    
    protected abstract T apply(T a, T b) throws MyExceptions;

    public T evaluate(T x, T y, T z) throws MyExceptions {
        return apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
