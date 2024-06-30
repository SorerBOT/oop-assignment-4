/**
 * The Nand class.
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor of the Nand class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public Nand(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "A");
    }
    @Override
    public Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return !(firstOperand && secondOperand);
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        return new Nand(
            this.getFirstExpression().nandify(),
            this.getSecondExpression().nandify()
        );
    }
    @Override
    public Expression norify() {
        Expression first = this.getFirstExpression().norify();
        Expression second = this.getSecondExpression().norify();
        Expression norFirst = new Nor(first, first);
        Expression norSecond = new Nor(second, second);

        return new Nor(new Nor(norFirst, norSecond), new Nor(norFirst, norSecond));
    }
    @Override
    public Expression simplify() {
        Expression firstSimplified = this.getFirstExpression().simplify();
        Expression secondSimplified = this.getSecondExpression().simplify();

        // 1 A 1 => F
        if (firstSimplified.toString().equals("T") && secondSimplified.toString().equals("T")) {
            return new Val(false);
        }
        // x A 0, 0 A x => T
        if (firstSimplified.toString().equals("F") || secondSimplified.toString().equals("F")) {
            return new Val(true);
        }
        // 1 A x => ~x
        if (firstSimplified.toString().equals("T")) {
            return new Not(secondSimplified);
        }
        // x A 1 => ~x
        if (secondSimplified.toString().equals("T")) {
            return new Not(firstSimplified);
        }
        // x A x => ~x
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return new Not(firstSimplified);
        }

        return new Nand(firstSimplified, secondSimplified);
    }
}