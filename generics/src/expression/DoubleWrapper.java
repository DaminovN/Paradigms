package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.MyExceptions;
import expression.exceptions.ParsingException;

/**
 * Created by daminovn on 11.04.2017.
 */
public class DoubleWrapper implements Type<DoubleWrapper, Double> {
    double val;
    public DoubleWrapper() {
        val = 0.0;
    }
    public DoubleWrapper(double a) {
        val = a;
    }
    public DoubleWrapper add(DoubleWrapper rhs) {
        return new DoubleWrapper(val + rhs.val);
    }

    public DoubleWrapper subtract(DoubleWrapper rhs) {
        return new DoubleWrapper(val - rhs.val);
    }

    public DoubleWrapper multiply(DoubleWrapper rhs) {
        return new DoubleWrapper(val * rhs.val);
    }

    public DoubleWrapper divide(DoubleWrapper rhs) {
        return new DoubleWrapper(val / rhs.val);
    }

    @Override
    public DoubleWrapper mod(DoubleWrapper rhs) throws MyExceptions {
        double res;
        try {
            res = val % rhs.val;
        } catch (Exception e) {
            throw new DivisionByZeroException();
        }
        return new DoubleWrapper(res);
    }

    public DoubleWrapper negate() {
        return new DoubleWrapper(-val);
    }

    public DoubleWrapper toConst(String val) throws ParsingException {
        Double num;
        try {
            num = Double.parseDouble(val);
        } catch (Exception e) {
            throw new ParsingException(0);
        }
        return new DoubleWrapper(num);
    }

    public DoubleWrapper abs() throws MyExceptions {
        if (val < 0) {
            return this.negate();
        } else {
            return this;
        }
    }

    @Override
    public DoubleWrapper square() throws MyExceptions {
        return this.multiply(this);
    }

    @Override
    public DoubleWrapper get(String s) {
        return new DoubleWrapper(Double.parseDouble(s));
    }

    public Double take() {
        return val;
    }
}
