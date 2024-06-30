import java.util.List;
import java.util.Map;

/**
 * The BaseExpression class.
 */
public abstract class BaseExpression implements Expression {
    private final String operator;

    /**
     * Constructor of the BaseExpression class.
     * @param operator the symbol of the operator to be used
     */
    public BaseExpression(String operator) {
        this.operator = new String(operator);
    }
    /**
     * Getter of the operator field.
     * @return the operator
     */
    public String getOperator() {
        return new String(this.operator);
    }
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

    // @Override
    // public abstract Expression simplify();
}