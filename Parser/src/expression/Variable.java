/**
 * Created by HP on 22.03.2017.
 */
package expression;

public class Variable implements TripleExpression {
    private String name;

    public Variable(String nm) {
        name = nm;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
