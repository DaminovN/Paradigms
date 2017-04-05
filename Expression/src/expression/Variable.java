/**
 * Created by HP on 22.03.2017.
 */
package expression;

public class Variable implements AnyExpression {
    private
    String name;

    public Variable(String nm) {
        name = nm;
    }

    @Override
    public double evaluate(double val) {
        return val;
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
