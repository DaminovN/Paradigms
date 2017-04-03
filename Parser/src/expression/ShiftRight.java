package expression;

/**
 * Created by daminovn on 03.04.2017.
 */
public class ShiftRight extends BinaryOperator {
    public ShiftRight( TripleExpression frst, TripleExpression scnd ) {
        first = frst;
        second = scnd;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (first.evaluate(x, y, z) >> second.evaluate(x, y, z));
    }
}
