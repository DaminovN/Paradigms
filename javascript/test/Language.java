package test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Language {
    private final BaseJavascriptTest.Dialect parsed;
    private final BaseJavascriptTest.Dialect unparsed;

    protected final List<BaseJavascriptTest.Expr<UnaryOperator<Double>>> unary = new ArrayList<>();
    protected final List<BaseJavascriptTest.Expr<BinaryOperator<Double>>> binary = new ArrayList<>();
    private final Map<String, BaseJavascriptTest.Expr<UnaryOperator<Double>>> us;
    final Map<String, BaseJavascriptTest.Expr<BinaryOperator<Double>>> bs;

    protected List<BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr>> tests = new ArrayList<>();

    protected final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> vx;
    protected final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> vy;
    protected final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> vz;

    public Language(final BaseJavascriptTest.Dialect parsed, final BaseJavascriptTest.Dialect unparsed, final BaseJavascriptTest.Ops ops) {
        this.parsed = parsed;
        this.unparsed = unparsed;

        us = ops.unary;
        bs = ops.binary;
        unary.addAll(us.values());
        binary.addAll(bs.values());

        vx = variable("x", (x, y, z) -> x);
        vy = variable("y", (x, y, z) -> y);
        vz = variable("z", (x, y, z) -> z);
    }

    private boolean safe(final char ch) {
        return !Character.isLetterOrDigit(ch) && "+-*/.".indexOf(ch) == -1;
    }

    protected String addSpaces(final String expression, final Random random) {
        String spaced = expression;
        for (int n = StrictMath.min(10, 200 / expression.length()); n > 0;) {
            final int index = random.nextInt(spaced.length() + 1);
            final char c = index == 0 ? 0 : spaced.charAt(index - 1);
            final char nc = index == spaced.length() ? 0 : spaced.charAt(index);
            if ((safe(c) || safe(nc)) && c != '\'' && nc != '\'' && c != '"' && nc != '"') {
                spaced = spaced.substring(0, index) + " " + spaced.substring(index);
                n--;
            }
        }
        return spaced;
    }

    protected <T> BaseJavascriptTest.Expr<T> variable(final String name, final T answer) {
        return expr(parsed.variable(name), unparsed.variable(name), answer);
    }

    protected <T> BaseJavascriptTest.Expr<T> constant(final int value, final T answer) {
        return expr(parsed.constant(value), unparsed.constant(value), answer);
    }

    protected <T> BaseJavascriptTest.Expr<T> unary(final BaseJavascriptTest.Expr<UnaryOperator<T>> op, final BaseJavascriptTest.Expr<T> arg) {
        return expr(
                parsed.unary(op.parsed, arg.parsed),
                unparsed.unary(op.unparsed, arg.unparsed),
                op.answer.apply(arg.answer)
        );
    }

    protected <T> BaseJavascriptTest.Expr<T> binary(final BaseJavascriptTest.Expr<BinaryOperator<T>> op, final BaseJavascriptTest.Expr<T> t1, final BaseJavascriptTest.Expr<T> t2) {
        return expr(
                parsed.binary(op.parsed, t1.parsed, t2.parsed),
                unparsed.binary(op.unparsed, t1.unparsed, t2.unparsed),
                op.answer.apply(t1.answer, t2.answer)
        );
    }

    protected <T> BaseJavascriptTest.Expr<T> nary(final BaseJavascriptTest.Expr<BinaryOperator<T>> op, final List<BaseJavascriptTest.Expr<T>> ts) {
        return expr(
                parsed.nary(op.parsed, ts.stream().map(t -> t.parsed).collect(Collectors.toList())),
                unparsed.nary(op.unparsed, ts.stream().map(t -> t.unparsed).collect(Collectors.toList())),
                ts.stream().map(t -> t.answer).reduce(op.answer).get()
        );
    }

    protected BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> constant(final int value) {
        return constant(value, (x, y, z) -> value);
    }

    protected BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> u(final String name, final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> a) {
        final BaseJavascriptTest.Expr<UnaryOperator<Double>> op = us.get(name);
        final UnaryOperator<BaseJavascriptTest.TExpr> t = q -> (x, y, z) -> op.answer.apply(q.evaluate(x, y, z));
        return unary(expr(op.parsed, op.unparsed, t), a);
    }

    protected BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> b(final String name, final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> a, final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> b) {
        final BaseJavascriptTest.Expr<BinaryOperator<Double>> op = bs.get(name);
        final BinaryOperator<BaseJavascriptTest.TExpr> t = (q, r) -> (x, y, z) -> op.answer.apply(q.evaluate(x, y, z), r.evaluate(x, y, z));
        return binary(expr(op.parsed, op.unparsed, t), a, b);
    }

    @SafeVarargs
    protected final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr> n(final String name, final BaseJavascriptTest.Expr<BaseJavascriptTest.TExpr>... as) {
        final BaseJavascriptTest.Expr<BinaryOperator<Double>> op = bs.get(name);
        final BinaryOperator<BaseJavascriptTest.TExpr> t = (q, r) -> (x, y, z) -> op.answer.apply(q.evaluate(x, y, z), r.evaluate(x, y, z));
        return nary(expr(op.parsed, op.unparsed, t), Arrays.asList(as));
    }

    public static <T> BaseJavascriptTest.Expr<T> expr(final String parsed, final String unparsed, final T answer) {
        return new BaseJavascriptTest.Expr<>(parsed, unparsed, answer);
    }
}
