package expression.exceptions;

/**
 * Created by daminovn on 05.04.2017.
 */
public class MyExceptions extends Exception {
    public MyExceptions(String message) {
        super(message);
    }

    public MyExceptions() {
        super("Exceptions found");
    }

}
