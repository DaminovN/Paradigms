package expression;

import com.sun.org.apache.xpath.internal.operations.Div;
import expression.exceptions.DivisionByZeroException;
import expression.exceptions.MyExceptions;
import expression.exceptions.ParsingException;

import java.math.BigInteger;

/**
 * Created by daminovn on 12.04.2017.
 */
public class BigIntegerWrapper implements Type<BigIntegerWrapper, BigInteger> {
    private BigInteger a;

    public BigIntegerWrapper() {
        a = BigInteger.ZERO;
    }

    public BigIntegerWrapper(int value) {
        a = BigInteger.valueOf(value);
    }

    private BigIntegerWrapper(BigInteger value) {
        a = value;
    }

    @Override
    public BigIntegerWrapper add(BigIntegerWrapper b) throws MyExceptions {
        return new BigIntegerWrapper(a.add(b.a));
    }

    @Override
    public BigIntegerWrapper subtract(BigIntegerWrapper b) throws MyExceptions {
        return new BigIntegerWrapper(a.subtract(b.a));
    }

    @Override
    public BigIntegerWrapper multiply(BigIntegerWrapper b) throws MyExceptions {
        return new BigIntegerWrapper(a.multiply(b.a));
    }

    @Override
    public BigIntegerWrapper divide(BigIntegerWrapper b) throws MyExceptions {
        BigInteger res;
        try {
            res = a.divide(b.a);
        } catch (Exception e) {
            throw new DivisionByZeroException();
        }
        return new BigIntegerWrapper(res);
    }

    @Override
    public BigIntegerWrapper mod(BigIntegerWrapper b) throws MyExceptions {
        if (b.a.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException();
        }
        return new BigIntegerWrapper(a.remainder(b.a));
    }

    @Override
    public BigIntegerWrapper negate() throws MyExceptions {
        return new BigIntegerWrapper(a.negate());
    }

    @Override
    public BigIntegerWrapper toConst(String val) throws MyExceptions {
        BigInteger res;
        try {
            res = new BigInteger(val);
        } catch (Exception e) {
            throw new ParsingException(0);
        }
        return new BigIntegerWrapper(res);
    }

    public BigIntegerWrapper abs() throws MyExceptions {
        if (a.compareTo(BigInteger.ZERO) == -1) {
            return this.negate();
        } else {
            return this;
        }
    }

    public BigIntegerWrapper square() throws MyExceptions {
        return this.multiply(this);
    }

    @Override
    public BigIntegerWrapper get(String s) {
        return new BigIntegerWrapper(Integer.parseInt(s));
    }

    public BigInteger take() {
        return a;
    }
}
