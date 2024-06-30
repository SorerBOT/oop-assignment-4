import java.util.List;
import java.util.Map;

/**
 * The UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    /**
     * Determines whether the expressions are visually identical.
     * @param expression the expression to be compared with
     * @return true if the expressions are equal and false otherwise
     */
    public abstract Boolean equals(Expression expression);
    @Override
    public abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    @Override
    public abstract Boolean evaluate() throws Exception;

    @Override
    public abstract List<String> getVariables();

    @Override
    public abstract String toString();

    @Override
    public abstract Expression assign(String var, Expression expression);

    @Override
    public abstract Expression nandify();

    @Override
    public abstract Expression norify();

    @Override
    public abstract Expression simplify();
}
