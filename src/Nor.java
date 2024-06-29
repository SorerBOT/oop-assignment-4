/**
 * The Nor class.
 */
public class Nor extends BinaryExpression {
    /**
     * Constructor of the Nor class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public Nor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "V");
    }
    @Override
    public Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return !(firstOperand || secondOperand);
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        return new Not(new Or(this.getFirstExpression(), this.getSecondExpression())).nandify();
    }
}