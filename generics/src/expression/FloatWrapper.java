package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.MyExceptions;
import expression.exceptions.ParsingException;

/**
 * Created by daminovn on 11.04.2017.
 */
public class FloatWrapper implements Type<FloatWrapper, Float> {
    float val;
    public FloatWrapper() {
        val = 0;
    }
    public FloatWrapper(float a) {
        val = a;
    }
    public FloatWrapper add(FloatWrapper rhs) {
        return new FloatWrapper(val + rhs.val);
    }

    public FloatWrapper subtract(FloatWrapper rhs) {
        return new FloatWrapper(val - rhs.val);
    }

    public FloatWrapper multiply(FloatWrapper rhs) {
        return new FloatWrapper(val * rhs.val);
    }

    public FloatWrapper divide(FloatWrapper rhs) {
        return new FloatWrapper(val / rhs.val);
    }

    @Override
    public FloatWrapper mod(FloatWrapper rhs) throws MyExceptions {
        float res;
        try {
            res = val % rhs.val;
        } catch (Exception e) {
            throw new DivisionByZeroException();
        }
        return new FloatWrapper(res);
    }

    public FloatWrapper negate() {
        return new FloatWrapper(-val);
    }

    public FloatWrapper toConst(String val) throws ParsingException {
        float num;
        try {
            num = Float.parseFloat(val);
        } catch (Exception e) {
            throw new ParsingException(0);
        }
        return new FloatWrapper(num);
    }

    public FloatWrapper abs() throws MyExceptions {
        if (val < 0) {
            return this.negate();
        } else {
            return this;
        }
    }

    @Override
    public FloatWrapper square() throws MyExceptions {
        return this.multiply(this);
    }

    @Override
    public FloatWrapper get(String s) {
        return new FloatWrapper(Float.parseFloat(s));
    }

    public Float take() {
        return val;
    }
}
