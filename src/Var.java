import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * The Var class.
 */
public final class Var implements Expression {
    private final String name;

    /**
     * Constructor of the Var class.
     * @param name the name of the Var
     */
    public Var(String name) {
        this.name = new String(name);
    }

    /**
     * Copy constructor of the Var class.
     * @param var the Var to be copied
     */
    private Var(Var var) {
        this(var.getName());
    }
    /**
     * Getter of the Name field.
     * @return the name field
     */
    public String getName() {
        return new String(this.name);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.name)) {
            return assignment.get(this.name);
        }
        throw new Exception("Var " + this.toString() + " is not assigned");
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Var " + this.toString() + " is not assigned");
    }

    @Override
    public List<String> getVariables() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(new String(this.name));

        return list;
    }

    @Override
    public String toString() {
        return new String(this.name);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
        return new Var(this.name);
    }

    @Override
    public Expression nandify() {
        return new Var(this);
    }

    @Override
    public Expression norify() {
        return new Var(this);
    }

    @Override
    public Expression simplify() {
        return new Var(this);
    }
}