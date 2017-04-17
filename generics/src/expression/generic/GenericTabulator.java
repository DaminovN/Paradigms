package expression.generic;

import expression.*;
import expression.exceptions.MyExceptions;
import expression.exceptions.Parser;
import expression.parser.ExpressionParser;

/**
 * Created by daminovn on 11.04.2017.
 */

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {

        switch (mode) {
            case "i":
                return myTabulate(new ExpressionParser<CheckedInteger>(new CheckedInteger()).parse(expression),
                        new CheckedInteger(), x1, x2, y1, y2, z1, z2);
            case "d":
                return myTabulate(new ExpressionParser<DoubleWrapper>(new DoubleWrapper()).parse(expression)
                        , new DoubleWrapper(), x1, x2, y1, y2, z1, z2);
            case "u":
                return myTabulate(new ExpressionParser<UnCheckedInteger>(new UnCheckedInteger()).parse(expression),
                        new UnCheckedInteger(), x1, x2, y1, y2, z1, z2);
            case "b":
                return myTabulate(new ExpressionParser<ByteWrapper>(new ByteWrapper()).parse(expression),
                        new ByteWrapper(), x1, x2, y1, y2, z1, z2);
            case "f":
                return myTabulate(new ExpressionParser<FloatWrapper>(new FloatWrapper()).parse(expression),
                        new FloatWrapper(), x1, x2, y1, y2, z1, z2);
            default:
                return myTabulate(new ExpressionParser<BigIntegerWrapper>(new BigIntegerWrapper()).parse(expression),
                        new BigIntegerWrapper(), x1, x2, y1, y2, z1, z2);
        }
    }

    private <T extends Type<T, ? extends Number>> Object[][][] myTabulate(TripleExpression<T> res, Type<T,? extends Number> tp, int x1, int x2, int y1, int y2, int z1, int z2) {

        int szx = x2 - x1 + 1;
        int szy = y2 - y1 + 1;
        int szz = z2 - z1 + 1;
        Object[][][] result = new Object[szx][szy][szz];
        for (int i = 0; i < szx; i++) {
            for (int j = 0; j < szy; j++) {
                for (int k = 0; k < szz; k++) {
                    try {
                        result[i][j][k] = res.evaluate(tp.get(Integer.toString(i + x1)),
                                tp.get(Integer.toString(j + y1)), tp.get(Integer.toString(k + z1))).take();
                    } catch (MyExceptions e) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }

}
