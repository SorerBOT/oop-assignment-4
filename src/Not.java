import java.util.Map;
import java.util.List;

/**
 * The Not class.
 */
public final class Not implements Expression {
    private final Expression expression;

    /**
     * Constructor of the Not class.
     * @param expression the expression
     */
    public Not(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !this.expression.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !this.expression.evaluate();
    }

    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

    @Override
    public String toString() {
        return "~" + this.expression.toString();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(this.expression.assign(var, expression));
    }
}
