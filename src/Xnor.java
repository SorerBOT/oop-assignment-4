/**
 * The Xnor class.
 */
public class Xnor extends BinaryExpression {
    /**
     * Constructor of the Xnor class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public Xnor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "#");
    }
    @Override
    public Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return !(firstOperand ^ secondOperand);
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
}