package expression;

import expression.exceptions.MyExceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T extends Type<T, ? extends Number>> {
    T evaluate(T x, T y, T z) throws MyExceptions;
}