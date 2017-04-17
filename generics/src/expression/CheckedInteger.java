package expression;

import expression.exceptions.*;
import expression.parser.ExpressionParser;

/**
 * Created by daminovn on 11.04.2017.
 */
public class CheckedInteger implements Type<CheckedInteger,Integer> {
    private int a;

    public CheckedInteger() {
        a = 0;
    }

    public CheckedInteger(int b) {
        a = b;
    }


    private int checkedAdd(int b) throws MyExceptions {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException();
        } else if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new OverflowException();
        }
        return a + b;
    }

    public CheckedInteger add(CheckedInteger rhs) throws MyExceptions {
        return new CheckedInteger(checkedAdd(rhs.a));
    }

    private int checkedSubtract(int b) throws MyExceptions {
        if (b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        } else if (b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
        return a - b;
    }

    public CheckedInteger subtract(CheckedInteger rhs) throws MyExceptions {
        return new CheckedInteger(checkedSubtract(rhs.a));
    }

    private int checkedMultiply(int b) throws MyExceptions {
        //System.out.println(a + " " + b);
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b > 0) {
            if (b > Integer.MAX_VALUE / a) {
                throw new OverflowException();
            }
        } else if (a > 0) {
            if (b < Integer.MIN_VALUE / a) {
                throw new OverflowException();
            }
        } else if (a < 0) {
            if (a < Integer.MAX_VALUE / b) {
                throw new OverflowException();
            }
        }
        return a * b;
    }

    public CheckedInteger multiply(CheckedInteger rhs) throws MyExceptions {
        return new CheckedInteger(checkedMultiply(rhs.a));
    }

    private int checkedDivide(int b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        } else if (b == -1 && a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return a / b;
    }

    public CheckedInteger divide(CheckedInteger rhs) throws MyExceptions {
        return new CheckedInteger(checkedDivide(rhs.a));
    }

    private int checkedMod(int b) throws MyExceptions {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a % b;
    }

    public CheckedInteger mod(CheckedInteger b) throws MyExceptions {
        return new CheckedInteger(checkedMod(b.a));
    }

    private int checkedNegate() throws MyExceptions {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -a;
    }

    public CheckedInteger negate() throws MyExceptions {
        return new CheckedInteger(checkedNegate());
    }

    public CheckedInteger toConst(String val) throws ParsingException {
        int num;
        try {
            num = Integer.parseInt(val);
        } catch (Exception e) {
            throw new ParsingException(0);
        }
        return new CheckedInteger(num);
    }

    private int checkedAbs() throws MyExceptions {
        if (a < 0) {
            try {
                a = checkedNegate();
            } catch (MyExceptions e) {
                throw new FunctionExceptions("Abs has wrong expression");
            }
            return a;
        } else {
            return a;
        }
    }
    public CheckedInteger abs() throws MyExceptions {
        return new CheckedInteger(checkedAbs());
    }

    public CheckedInteger square() throws MyExceptions {
        return this.multiply(this);
    }

    @Override
    public CheckedInteger get(String s) {
        return new CheckedInteger(Integer.parseInt(s));
    }


    public Integer take() {
        return a;
    }
}
