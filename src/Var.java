import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * The Var class.
 */
public final class Var implements Expression {
    private final String name;
    private final Expression expression;

    /**
     * Constructor of the Var class.
     * @param name the name of the Variable
     * @param expression the expression which is represented by the Variable.
     */
    public Var(String name, Expression expression) {
        this.name = new String(name);
        this.expression = expression;
    }
    /**
     * Copy constructor of the Var class.
     * @param var the Var instance to be copied
     */
    public Var(Var var) {
        this(var.getName(), var.getExpression());
    }

    /**
     * Getter of the Name field.
     * @return the name field
     */
    public String getName() {
        return new String(this.name);
    }
    /**
     * Getter of the expression field.
     * @return the expression field
     */
    public Expression getExpression() {
        return this.expression;
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        final Boolean newValue = this.expression.evaluate(assignment);
        for (Map.Entry<String, Boolean> entry : assignment.entrySet()) {
            if (!this.name.equals(entry.getKey())) {
                continue;
            }
            entry.setValue(newValue);
        }
        return newValue;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.expression.evaluate();
    }

    @Override
    public List<String> getVariables() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(new String(this.name));

        return list;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
        return new Var(this);
    }
}