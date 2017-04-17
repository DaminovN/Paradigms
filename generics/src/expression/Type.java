package expression;

import expression.exceptions.MyExceptions;

/**
 * Created by daminovn on 11.04.2017.
 */
public interface Type<T,S extends Number> {
    public T add(T b) throws MyExceptions;
    public T subtract(T b) throws MyExceptions;
    public T multiply(T b) throws MyExceptions;
    public T divide(T b) throws MyExceptions;
    public T mod(T b) throws MyExceptions;
    public T negate() throws MyExceptions;
    public T toConst(String val) throws MyExceptions;
    public T abs() throws MyExceptions;
    public T square() throws MyExceptions;
    public T get(String s);
    public S take();
}
