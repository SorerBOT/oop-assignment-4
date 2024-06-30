import java.util.Map;
import java.util.List;

/**
 * The Val class.
 */
public class Val implements Expression {
    private final Boolean value;
    /**
     * The constructor of the Val class.
     * @param value the value of the Val
     */
    public Val(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value;
    }

    @Override
    public Boolean evaluate() {
        return this.value;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public String toString() {
        return this.value ? "T" : "F";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(this.value);
    }

    @Override
    public Expression nandify() {
        return new Val(this.value);
    }

    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    @Override
    public Expression simplify() {
        return new Val(this.value);
    }
}
