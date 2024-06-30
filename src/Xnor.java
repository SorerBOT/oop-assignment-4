/**
 * The Xnor class.
 */
public class Xnor extends BinaryExpression {
    /**
     * Constructor of the Xnor class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    protected Xnor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "#");
    }
    @Override
    protected Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return !(firstOperand ^ secondOperand);
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        return new Nand(
            new Nand(
                new Not(this.getFirstExpression().nandify()).nandify(),
                new Not(this.getSecondExpression().nandify()).nandify()
            ),
            new Nand(this.getFirstExpression().nandify(), this.getSecondExpression().nandify())
        );
    }
    @Override
    public Expression norify() {
        Expression first = this.getFirstExpression().norify();
        Expression second = this.getSecondExpression().norify();

        return new Nor(new Nor(first, new Nor(first, second)), new Nor(second, new Nor(first, second)));
    }
    @Override
    public Expression simplify() {
        Expression firstSimplified = this.getFirstExpression().simplify();
        Expression secondSimplified = this.getSecondExpression().simplify();

        // F # x, x # F => F
        if ((firstSimplified.toString().equals("T") && secondSimplified.toString().equals("F"))
        || (firstSimplified.toString().equals("F") && secondSimplified.toString().equals("T"))) {
            return new Val(false);
        }

        // x # x => T
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return new Val(true);
        }
        return new Xnor(firstSimplified, secondSimplified);
    }
}
