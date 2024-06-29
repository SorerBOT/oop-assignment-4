import java.util.Map;
import java.util.List;

/**
 * The Expression interface.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a map of variable values
     * @return the result of the evaluation
     * @throws Exception if a variable in the expression is not in the assignment
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the evaluate(assignment) method above,
     * but uses an empty assignment.
     *
     * @return the result of the evaluation
     * @throws Exception if a variable in the expression is not in the assignment
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return the string representation of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     * (Does not modify the current expression)
     *
     * @param var the variable to replace
     * @param expression the expression to replace the variable with
     * @return the new expression with the variable replaced
     */
    Expression assign(String var, Expression expression);

    /**
     * Nandifies the expression.
     * @return the nandified expression
     */
    Expression nandify();

    /**
     * Norifies the expression.
     * @return the norified expression
     */
    Expression norify();
}