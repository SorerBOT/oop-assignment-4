import java.util.Map;
import java.util.List;

/**
 * The UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression expression;

    /**
     * The constructor of the UnaryExpression class.
     * @param expression the Expression
     * @param operator the symbol of the operator
     */
    public UnaryExpression(Expression expression, String operator) {
        super(operator);
        this.expression = expression;
    }

    /**
     * Applies the binary operation to the operand.
     * @param operand the operand
     * @return the result of the binary operation
     */
    public abstract Boolean handleOperation(Boolean operand);

    /**
     * Getter of the expression field.
     * @return the expression
     */
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return handleOperation(this.expression.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return handleOperation(this.expression.evaluate());
    }

    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

    @Override
    public String toString() {
        return this.getOperator() + "(" + this.expression.toString() + ")";
    }
    @Override
    public abstract Expression assign(String var, Expression expression);
}
