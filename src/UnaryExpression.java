/**
 * The UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression firstExpression;

    /**
     * The constructor of the UnaryExpression class.
     * @param expression the Expression
     * @param operator the symbol of the operator
     */
    public UnaryExpression(Expression expression, String operator) {
        super(operator);
        this.firstExpression = expression;
    }

    @Override
    public Boolean equals(Expression expression) {
        return this.toString().equals(expression.toString());
    }

}
