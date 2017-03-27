/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class Subtract extends BinaryOperator {
    public Subtract( AbstractExpression frst, AbstractExpression scnd ) {
        first = frst;
        second = scnd;
    }

    @Override
    public double evaluate(double val)  {
        return first.evaluate(val) - second.evaluate(val);
    }

    @Override
    public int evaluate(int value) {
        return first.evaluate(value) - second.evaluate(value);
    }

    @Override
    public int evaluate(int x, int y, int z)  {
        return first.evaluate(x, y, z) - second.evaluate(x, y, z);
    }
}
