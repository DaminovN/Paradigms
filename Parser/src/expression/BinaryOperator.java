/**
 * Created by HP on 22.03.2017.
 */
package expression;
abstract public class BinaryOperator extends AbstractExpression {
    protected AbstractExpression first;
    protected AbstractExpression second;
    public abstract double evaluate(double val);
    public abstract int evaluate(int val);
    public abstract int evaluate(int x, int y, int z);
}