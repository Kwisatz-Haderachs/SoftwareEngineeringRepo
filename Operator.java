
/**
 * Operator is the superclass of all binary and unary operators.
 */
public abstract class Operator extends Token{

    public Operator(){}

    public abstract boolean isBinaryOperator();

    public abstract int precedence();

    public abstract int stackPrecedence();

}
