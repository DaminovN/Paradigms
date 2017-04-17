package expression.exceptions;

/**
 * Created by daminovn on 04.04.2017.
 */
public class ParsingException extends MyExceptions {
    public ParsingException(int a) {
        super("Parsing Exception in position " + a);
    }
    public ParsingException(int a, String b) {
        super("Parsing Exception in position " + a + " " + b);
    }

    public ParsingException() {
        super("Parsing Exception");
    }
}
