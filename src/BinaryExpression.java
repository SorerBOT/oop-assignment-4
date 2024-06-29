/**
 * The BinaryExpression class.
 */
public abstract class BinaryExpression {
    private final Expression firstExpression;
    private final Expression secondExpression;
    private final String operator;

    /**
     * Applies the binary operation to the expressions.
     * @return
     */
    public abstract Boolean handleOperation();
}