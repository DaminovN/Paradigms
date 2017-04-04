/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class CheckedDivide extends BinaryOperator {
    public CheckedDivide( TripleExpression frst, TripleExpression scnd ) {
        super(frst,scnd);
    }

    private void check(int a, int b) throws Exception {
        if (b == 0) {
            throw new DivisionByZeroException();
        } else if (b == -1 && a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    @Override
    protected int apply(int a, int b) throws Exception {
        check(a, b);
        return a / b;
    }

}
