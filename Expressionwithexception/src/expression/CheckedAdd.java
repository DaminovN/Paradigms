/**
 * Created by HP on 22.03.2017.
 */
package expression;

public class CheckedAdd extends BinaryOperator {
    public CheckedAdd(TripleExpression frst, TripleExpression scnd ) {
        super(frst,scnd);
    }

    private void check(int a, int b) throws Exception {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException();
        } else if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int a, int b) throws Exception{
        check(a, b);
        return a + b;
    }
}
