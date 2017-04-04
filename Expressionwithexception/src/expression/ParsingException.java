package expression;

/**
 * Created by daminovn on 04.04.2017.
 */
public class ParsingException extends Exception {
    public ParsingException(int a) {
        super("Parsing Exception in position " + a);
    }
    public ParsingException() {
        super("Parsing Exception");
    }
}
