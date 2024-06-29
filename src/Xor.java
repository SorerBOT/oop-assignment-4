/**
 * The Xor class.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructor of the Xor class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public Xor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "^");
    }
    @Override
    public Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return firstOperand ^ secondOperand;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        return new Or(
            new And(
                new Not(this.getFirstExpression().nandify()).nandify(),
                this.getSecondExpression().nandify()
            ).nandify(),
            new And(
                this.getFirstExpression().nandify(),
                new Not(this.getSecondExpression().nandify()).nandify()
            ).nandify()
        ).nandify();
    }
    @Override
    public Expression norify() {
        return this.nandify().norify();
    }
}