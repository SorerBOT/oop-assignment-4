/**
 * The Or class.
 */
public class Or extends BinaryExpression {
    /**
     * Constructor of the Or class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public Or(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "|");
    }
    @Override
    public Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return firstOperand || secondOperand;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
}