/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class CheckedSubtract extends BinaryOperator {
    public CheckedSubtract(TripleExpression frst, TripleExpression scnd) {
        super(frst,scnd);
    }

    private void check(int a, int b) throws Exception{
        if (b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        } else if (b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int a, int b) throws Exception {
        check(a, b);
        return a - b;
    }
}
