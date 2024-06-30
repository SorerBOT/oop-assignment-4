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
    @Override
    public Expression simplify() {
        try {
            // 1 and x case
            if (this.getFirstExpression().evaluate()) {
                return this.getSecondExpression().simplify();
            }
            // 0 and x case
            return new Val(false);
        } catch (Exception error) {
            System.err.println(error);
        }

        try {
            // x and 1 case
            if (this.getSecondExpression().evaluate()) {
                return this.getFirstExpression().simplify();
            }
            // x and 0 case
            return new Val(false);
        } catch (Exception error) {
            System.err.println(error);
        }
    }
}