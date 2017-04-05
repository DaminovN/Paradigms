package expression;

/**
 * Created by daminovn on 03.04.2017.
 */
public class Abs implements TripleExpression {
    private TripleExpression val;

    public Abs(TripleExpression value) {
        val = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.abs(val.evaluate(x, y, z));
    }
}
