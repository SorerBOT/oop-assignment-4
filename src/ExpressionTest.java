//yuval Grofman 214975245
import java.util.HashMap;

/**
 * Class with main to test the Expression classes.
 */
public class ExpressionTest {

    /**
     * Main which runs code.
     * @param args relevant
     */
    public static void main(String[] args) throws Exception{
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");

        Expression ex = new Not(
            new And(
                new Var("x"),
                new Xnor(new Var("y"),
                        new Nand(new And(new Val(true),
                                new Var("z")), new Val(false)))
            )
        );
        System.out.println(ex);
        System.out.println(ex.nandify());
        System.out.println(ex.norify());
        System.out.println(ex.simplify());
    }
}