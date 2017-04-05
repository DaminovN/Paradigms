package expression;

import expression.exceptions.*;

/**
 * Created by daminovn on 03.04.2017.
 */
public class CheckedAbs implements TripleExpression {
    private TripleExpression val;

    public CheckedAbs(TripleExpression value) {
        val = value;
    }

    @Override
    public int evaluate(int x, int y, int z) throws MyExceptions {
        int result = val.evaluate(x, y, z);
        if (result < 0) {
            try {
                result = new CheckedNegate(val).evaluate(x, y, z);
            } catch (MyExceptions e) {
                throw new FunctionExceptions("Abs has wrong expression");
            }
            return result;
        } else {
            return result;
        }
    }
}
