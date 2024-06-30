/**
 * The Not class.
 */
public final class Not extends UnaryExpression {
    /**
     * Constructor of the Not class.
     * @param expression the expression
     */
    public Not(Expression expression) {
        super(expression, "~");
    }

    @Override
    public Boolean handleOperation(Boolean operand) {
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
        return this.nandify().norify();
    }
}
