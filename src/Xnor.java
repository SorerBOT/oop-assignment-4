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
}
