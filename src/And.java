/**
 * The And class.
 */
public class And extends BinaryExpression {
    /**
     * Constructor of the And class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public And(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "&");
    }
    @Override
    public Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return firstOperand && secondOperand;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new And(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        return new Not(
            new Nand(
                this.getFirstExpression().nandify(),
                this.getSecondExpression().nandify()
            )
        ).nandify();
    }
    @Override
    public Expression norify() {
        return this.nandify().norify();
    }
}