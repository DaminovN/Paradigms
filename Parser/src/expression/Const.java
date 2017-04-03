/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class Const implements TripleExpression {
    double value;
    public Const(double val) {
        value = val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}
