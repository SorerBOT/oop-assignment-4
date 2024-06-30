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
}