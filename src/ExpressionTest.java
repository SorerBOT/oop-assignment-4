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
        // Creating an expression with at least three variables
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");

        Expression ex = new And(
            new Or(
                x,
                new And(y, new Val(true))
            ),
            new And(z, z)
        );
        // Printing the expression
        System.out.println(ex);


        // Assigning the three varialbes: x, y, z with boolean values
        Expression ex2 = ex.assign("x", new Val(true));
        Expression ex3 = ex2.assign("y", new Val(false));
        Expression ex4 = ex3.assign("z", new Val(false));
        // Printing the expression after the assignment
        System.out.println(ex4);

        // Printing the nandified version of the expression:
        System.out.println(ex.nandify());

        // Printing the norified version of the expression:
        System.out.println(ex.norify());

        // Printing the simplified version of the expression:
        System.out.println(ex.simplify());
    }
}