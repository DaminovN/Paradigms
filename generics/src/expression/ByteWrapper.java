package expression;

import expression.exceptions.*;
import expression.parser.ExpressionParser;

/**
 * Created by daminovn on 11.04.2017.
 */
public class ByteWrapper implements Type<ByteWrapper, Byte> {
    private byte a;

    public ByteWrapper() {
        a = 0;
    }

    public ByteWrapper(int b) {
        a = (byte)(b & 255);
    }

    private byte checkedAdd(byte b) throws MyExceptions {
        return (byte)((a + b) & 255);
    }

    public ByteWrapper add(ByteWrapper rhs) throws MyExceptions {
        return new ByteWrapper(checkedAdd(rhs.a));
    }

    private byte checkedSubtract(byte b) throws MyExceptions {
        return (byte)((a - b) & 255);
    }

    public ByteWrapper subtract(ByteWrapper rhs) throws MyExceptions {
        return new ByteWrapper(checkedSubtract(rhs.a));
    }

    private byte checkedMultiply(byte b) throws MyExceptions {
        return (byte)((a * b) & 255);
    }

    public ByteWrapper multiply(ByteWrapper rhs) throws MyExceptions {
        return new ByteWrapper(checkedMultiply(rhs.a));
    }

    private byte checkedDivide(byte b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return (byte)((a / b) & 255);
    }

    public ByteWrapper divide(ByteWrapper rhs) throws MyExceptions {
        return new ByteWrapper(checkedDivide(rhs.a));
    }

    private byte checkedMod(byte b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return (byte)((a % b) & 255);
    }

    public ByteWrapper mod(ByteWrapper b) throws MyExceptions {
        return new ByteWrapper(checkedMod(b.a));
    }

    private byte checkedNegate() throws MyExceptions {
        return (byte)((-a) & 255);
    }

    public ByteWrapper negate() throws MyExceptions {
        return new ByteWrapper(checkedNegate());
    }

    public ByteWrapper toConst(String val) throws ParsingException {
        byte num;
        try {
            num = Byte.parseByte(val);
        } catch (Exception e) {
            throw new ParsingException(0);
        }
        return new ByteWrapper(num);
    }

    private byte checkedAbs() throws MyExceptions {
        if (a < 0) {
            a = checkedNegate();
            return a;
        } else {
            return a;
        }
    }
    public ByteWrapper abs() throws MyExceptions {
        return new ByteWrapper(checkedAbs());
    }

    public ByteWrapper square() throws MyExceptions {
        return this.multiply(this);
    }

    public ByteWrapper get(String s) {
        return new ByteWrapper(Byte.parseByte(s));
    }

    public Byte take() {
        return a;
    }
}
