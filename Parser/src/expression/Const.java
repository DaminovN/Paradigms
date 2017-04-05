/**
 * Created by HP on 22.03.2017.
 */
package expression;

public class Const implements TripleExpression {
    int value;

    public Const(int val) {
        value = val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }
}
