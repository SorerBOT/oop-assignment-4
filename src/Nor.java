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
    protected Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
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
        return new Not(
            new Nand(
                new Not(this.getFirstExpression().nandify()).nandify(),
                new Not(this.getSecondExpression().nandify()).nandify()
            )
        ).nandify();
    }
    @Override
    public Expression norify() {
        return new Nor(this.getFirstExpression().norify(), this.getSecondExpression().norify());
    }
    @Override
    public Expression simplify() {
        Expression firstSimplified = this.getFirstExpression().simplify();
        Expression secondSimplified = this.getSecondExpression().simplify();

        // x V 1, 1 V x => F
        if (firstSimplified.toString().equals("T") || secondSimplified.toString().equals("T")) {
            return new Val(false);
        }
        // 0 V x => ~x
        if (firstSimplified.toString().equals("F")) {
            return new Not(secondSimplified);
        }
        // x V 0 => ~x
        if (secondSimplified.toString().equals("F")) {
            return new Not(firstSimplified);
        }
        // x V x => ~x
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return new Not(firstSimplified);
        }
        return new Nor(firstSimplified, secondSimplified);
    }

}