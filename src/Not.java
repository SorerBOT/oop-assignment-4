/**
 * The Not class.
 */
public final class Not extends UnaryExpression {
    /**
     * Constructor of the Not class.
     * @param expression the expression
     */
    protected Not(Expression expression) {
        super(expression, "~");
    }

    @Override
    protected Boolean handleOperation(Boolean operand) {
        return !operand;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(this.getExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(this.getExpression().nandify(), this.getExpression().nandify());
    }
    @Override
    public Expression norify() {
        Expression expression = this.getExpression().norify();
        return new Nor(expression, expression);
    }
    @Override
    public Expression simplify() {
        Expression expressionSimplified = this.getExpression().simplify();
        // ~F => T
        if (expressionSimplified.toString().equals("F")) {
            return new Val(true);
        }
        // ~T => F
        if (expressionSimplified.toString().equals("T")) {
            return new Val(false);
        }
        return new Not(expressionSimplified);
    }
}
