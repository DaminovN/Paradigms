package expression;

/**
 * Created by daminovn on 03.04.2017.
 */
public class ShiftLeft extends BinaryOperator {
    public ShiftLeft(TripleExpression frst, TripleExpression scnd) {
        super(frst, scnd);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (first.evaluate(x, y, z) << second.evaluate(x, y, z));
    }
}
