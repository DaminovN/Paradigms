package expression;

/**
 * Created by daminovn on 04.04.2017.
 */
public class Negate implements TripleExpression {
    private TripleExpression val;
    public Negate(TripleExpression value) {
        val = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -val.evaluate(x, y, z);
    }
}
