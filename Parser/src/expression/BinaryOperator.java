/**
 * Created by HP on 22.03.2017.
 */
package expression;
public abstract class BinaryOperator implements TripleExpression {
    protected TripleExpression first;
    protected TripleExpression second;
    public abstract int evaluate(int x, int y, int z);
}
