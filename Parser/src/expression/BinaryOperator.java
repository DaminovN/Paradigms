/**
 * Created by HP on 22.03.2017.
 */
package expression;
public abstract class BinaryOperator implements AnyExpression {
    protected AnyExpression first;
    protected AnyExpression second;
    public abstract double evaluate(double val);
    public abstract int evaluate(int val);
    public abstract int evaluate(int x, int y, int z);
}
