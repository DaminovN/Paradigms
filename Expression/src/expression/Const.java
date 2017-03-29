/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class Const implements AnyExpression {
    double value;
    public Const(double val) {
        value = val;
    }

    @Override
    public double evaluate(double val) {
        return value;
    }

    @Override
    public int evaluate(int val) {
        return (int) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}
