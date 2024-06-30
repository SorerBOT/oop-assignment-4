/**
 * The BaseExpression class.
 */
public abstract class BaseExpression implements Expression {
    private final String operator;

    /**
     * Constructor of the BaseExpression class.
     * @param operator the symbol of the operator to be used
     */
    protected BaseExpression(String operator) {
        this.operator = new String(operator);
    }
    /**
     * Getter of the operator field.
     * @return the operator
     */
    protected String getOperator() {
        return new String(this.operator);
    }
}