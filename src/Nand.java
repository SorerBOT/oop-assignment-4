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
}