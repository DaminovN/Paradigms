/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class Divide extends BinaryOperator {
    public Divide( TripleExpression frst, TripleExpression scnd ) {
        super(frst,scnd);
    }

    @Override
    public int evaluate(int x, int y, int z)  {
        return first.evaluate(x, y, z) / second.evaluate(x, y, z);
    }
}
