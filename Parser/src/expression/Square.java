package expression;

/**
 * Created by daminovn on 03.04.2017.
 */
public class Square implements TripleExpression {
    private TripleExpression val;

    public Square(TripleExpression value) {
        val = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int res = val.evaluate(x, y, z);
        return res * res;
    }
}
