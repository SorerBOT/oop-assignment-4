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
        return new Nand(
            new Nand(
                this.getFirstExpression().nandify(),
                this.getSecondExpression().nandify()
            ),
            new Nand(
                this.getFirstExpression().nandify(),
                this.getSecondExpression().nandify()
            )
        );
    }
    @Override
    public Expression norify() {
        Expression first = this.getFirstExpression().norify();
        Expression second = this.getSecondExpression().norify();

        return new Nor(new Nor(first, first), new Nor(second, second));
    }
    @Override
    public Expression simplify() {
        Expression firstSimplified = this.getFirstExpression().simplify();
        Expression secondSimplified = this.getSecondExpression().simplify();

        // x & x => x case
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return firstSimplified;
        }
        // x & 0, 0 & x => 0
        if (firstSimplified.toString().equals("F") || secondSimplified.toString().equals("F")) {
            return new Val(false);
        }
        // 1 & x => x
        if (firstSimplified.toString().equals("T")) {
            return secondSimplified;
        }
        // x & 1 => x
        if (secondSimplified.toString().equals("T")) {
            return firstSimplified;
        }

        return new And(firstSimplified, secondSimplified);
    }
}