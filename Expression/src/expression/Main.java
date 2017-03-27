/**
 * Created by HP on 22.03.2017.
 */
package expression;
public class Main {
    public static void main(String[] args) {
        assert args.length > 0 : "SIZE SMALL";
        int val = Integer.parseInt(args[0]);
        System.out.println( new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")),
                new Multiply(new Const(2), new Variable("x"))), new Const(1)).evaluate(val) );
    }
}
