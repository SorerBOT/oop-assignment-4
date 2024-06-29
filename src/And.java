import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * The And class.
 */
public class And implements Expression {
    private final Expression firstExpression;
    private final Expression secondExpression;

    /**
     * Constructor of the And class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    public And(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.firstExpression.evaluate(assignment) && this.secondExpression.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.firstExpression.evaluate() && this.secondExpression.evaluate();
    }

    @Override
    public List<String> getVariables() {
        Set<String> set = new LinkedHashSet<>(this.firstExpression.getVariables());
        set.addAll(this.secondExpression.getVariables());

        return new ArrayList<String>(set);
    }

    @Override
    public String toString() {
        return this.firstExpression.toString() + '&' + this.secondExpression.toString();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(this.firstExpression.assign(var, expression), this.secondExpression.assign(var, expression));
    }
}