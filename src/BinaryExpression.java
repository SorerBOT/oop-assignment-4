import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * The BinaryExpression class.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression firstExpression;
    private final Expression secondExpression;

    /**
     * The constructor of the BinaryExpression class.
     * @param firstExpression the first operand of the BinaryExpression
     * @param secondExpression the second operand of the BinaryExpression
     * @param operator the symbol of the operator to be used
     */
    protected BinaryExpression(Expression firstExpression, Expression secondExpression, String operator) {
        super(operator);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
    /**
     * Applies the binary operation to the operands.
     * @param firstOperand the first operand
     * @param secondOperand the second operand
     * @return the result of the binary operation
     */
    protected abstract Boolean handleOperation(Boolean firstOperand, Boolean secondOperand);
    /**
     * Getter of the firstExpression field.
     * @return the firstExpression field
     */
    protected Expression getFirstExpression() {
        return this.firstExpression;
    }
    /**
     * Getter of the secondExpression field.
     * @return the secondExpression field
     */
    protected Expression getSecondExpression() {
        return this.secondExpression;
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.handleOperation(
            this.firstExpression.evaluate(assignment),
            this.secondExpression.evaluate(assignment)
        );
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.handleOperation(
            this.firstExpression.evaluate(),
            this.secondExpression.evaluate()
        );
    }

    @Override
    public List<String> getVariables() {
        Set<String> set = new LinkedHashSet<>(this.firstExpression.getVariables());
        set.addAll(this.secondExpression.getVariables());

        return new ArrayList<String>(set);
    }

    @Override
    public String toString() {
        return
            "("
            + this.firstExpression.toString()
            + " " + this.getOperator()
            + " "
            + this.secondExpression.toString()
            + ")";
    }

    @Override
    public abstract Expression assign(String var, Expression expression);
}