package expression.exceptions;

import expression.TripleExpression;
import expression.Type;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T extends Type<T, ? extends Number>> {
    TripleExpression<T> parse(String expression) throws MyExceptions;
}