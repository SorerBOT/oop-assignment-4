/**
 * The Xor class.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructor of the Xor class.
     * @param firstExpression the first Expression
     * @param secondExpression the second Expression
     */
    protected Xor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "^");
    }
    @Override
    protected Boolean handleOperation(Boolean firstOperand, Boolean secondOperand) {
        return firstOperand ^ secondOperand;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(
            this.getFirstExpression().assign(var, expression),
            this.getSecondExpression().assign(var, expression)
        );
    }
    @Override
    public Expression nandify() {
        Nand nandAb = new Nand(this.getFirstExpression(), this.getSecondExpression());
        return new Nand(
            new Nand(this.getFirstExpression(), nandAb),
            new Nand(this.getSecondExpression(), nandAb)
        );
    }
    @Override
    public Expression norify() {
        Expression first = this.getFirstExpression().norify();
        Expression second = this.getSecondExpression().norify();
        Expression norFirst = new Nor(first, first);
        Expression norSecond = new Nor(second, second);
        Expression norFirstSecond = new Nor(first, second);
        return new Nor(new Nor(norFirst, norSecond), norFirstSecond);
    }

    @Override
    public Expression simplify() {
      Expression firstSimplified = this.getFirstExpression().simplify();
      Expression secondSimplified = this.getSecondExpression().simplify();

      // T ^ F, F ^ T => T
      if ((firstSimplified.toString().equals("T") && secondSimplified.toString().equals("F"))
      || (firstSimplified.toString().equals("F") && secondSimplified.toString().equals("T"))) {
        return new Val(true);
      }

      // x ^ x => F
      if (firstSimplified.toString().equals(secondSimplified.toString())) {
        return new Val(false);
      }

      // 1 ^ x => ~x
      if (firstSimplified.toString().equals("T")) {
        return new Not(secondSimplified);
      }

      // x ^ 1 => ~x
      if (secondSimplified.toString().equals("T")) {
        return new Not(firstSimplified);
      }

      // 0 ^ x => x
      if (firstSimplified.toString().equals("F")) {
        return secondSimplified;
      }

      // x ^ 0 => x
      if (secondSimplified.toString().equals("F")) {
        return firstSimplified;
      }

      return new Xor(firstSimplified, secondSimplified);
  }
}