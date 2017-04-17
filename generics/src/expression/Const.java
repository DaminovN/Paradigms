/**
 * Created by HP on 22.03.2017.
 */
package expression;

import expression.exceptions.MyExceptions;

public class Const<T extends Type<T, ? extends Number>> implements TripleExpression<T> {
    private T cnts;
    private int position;
    private void put(T val, int pos) {
        position = pos;
        cnts = val;
    }

    public Const(T val) {
        put(val, 0);
    }

    public Const(T val, int pos) {
        put(val, pos);
    }

    public T evaluate(T x, T y, T z) throws MyExceptions{
        return cnts;
    }
}
