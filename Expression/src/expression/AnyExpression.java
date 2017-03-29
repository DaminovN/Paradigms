package expression;

/**
 * Created by HP on 22.03.2017.
 */
public interface AnyExpression extends Expression, DoubleExpression, TripleExpression {
    public abstract double evaluate(double val);
    public abstract int evaluate(int val);
    public abstract int evaluate(int x, int y, int z);
}