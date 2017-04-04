package expression;

/**
 * Created by daminovn on 04.04.2017.
 */
public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
