package expression;

import expression.exceptions.*;
import expression.parser.ExpressionParser;

/**
 * Created by daminovn on 11.04.2017.
 */
public class UnCheckedInteger implements Type<UnCheckedInteger, Integer> {
    private int a;

    public UnCheckedInteger() {
        a = 0;
    }

    public UnCheckedInteger(int b) {
        a = b;
    }

    private int checkedAdd(int b) throws MyExceptions {
        return a + b;
    }

    public UnCheckedInteger add(UnCheckedInteger rhs) throws MyExceptions {
        return new UnCheckedInteger(checkedAdd(rhs.a));
    }

    private int checkedSubtract(int b) throws MyExceptions {
        return a - b;
    }

    public UnCheckedInteger subtract(UnCheckedInteger rhs) throws MyExceptions {
        return new UnCheckedInteger(checkedSubtract(rhs.a));
    }

    private int checkedMultiply(int b) throws MyExceptions {
        return a * b;
    }

    public UnCheckedInteger multiply(UnCheckedInteger rhs) throws MyExceptions {
        return new UnCheckedInteger(checkedMultiply(rhs.a));
    }

    private int checkedDivide(int b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a / b;
    }

    public UnCheckedInteger divide(UnCheckedInteger rhs) throws MyExceptions {
        return new UnCheckedInteger(checkedDivide(rhs.a));
    }

    private int checkedMod(int b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a % b;
    }

    public UnCheckedInteger mod(UnCheckedInteger b) throws MyExceptions {
        return new UnCheckedInteger(checkedMod(b.a));
    }

    private int checkedNegate() throws MyExceptions {
        return -a;
    }

    public UnCheckedInteger negate() throws MyExceptions {
        return new UnCheckedInteger(checkedNegate());
    }

    public UnCheckedInteger toConst(String val) throws ParsingException {
        int num;
        try {
            num = Integer.parseInt(val);
        } catch (Exception e) {
            throw new ParsingException(0);
        }
        return new UnCheckedInteger(num);
    }

    private int checkedAbs() throws MyExceptions {
        if (a < 0) {
            a = checkedNegate();
            return a;
        } else {
            return a;
        }
    }
    public UnCheckedInteger abs() throws MyExceptions {
        return new UnCheckedInteger(checkedAbs());
    }

    public UnCheckedInteger square() throws MyExceptions {
        return this.multiply(this);
    }

    @Override
    public UnCheckedInteger get(String s) {
        return new UnCheckedInteger(Integer.parseInt(s));
    }

    public Integer take() {
        return a;
    }
}
