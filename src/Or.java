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
    protected Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return firstOperand || secondOperand;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        return new Nand(
            new Not(this.getFirstExpression().nandify()).nandify(),
            new Not(this.getSecondExpression().nandify()).nandify()
        );
    }
    @Override
    public Expression norify() {
        Expression first = new Nor(this.getFirstExpression().norify(), this.getSecondExpression().norify());

        return new Nor(first, first);
    }
    @Override
    public Expression simplify() {
        Expression firstSimplified = this.getFirstExpression().simplify();
        Expression secondSimplified = this.getSecondExpression().simplify();

        // x | x => x
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return firstSimplified;
        }
        // T | x, x | T => T
        if (firstSimplified.toString().equals("T") || secondSimplified.toString().equals("T")) {
            return new Val(true);
        }
        // F | x => x
        if (firstSimplified.toString().equals("F")) {
            return secondSimplified;
        }
        // x | F => F
        if (secondSimplified.toString().equals("F")) {
            return firstSimplified;
        }


        return new Or(firstSimplified, secondSimplified);
    }
}