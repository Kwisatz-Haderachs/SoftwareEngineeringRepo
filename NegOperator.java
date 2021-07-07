/**
 * Represents the negation operator which is a specific type of unary operator.
 */
public class NegOperator extends UnaryOperator {

    public NegOperator(){
    }

    @Override
    public Operand evaluate(Operand pOperand){
        return new Operand(-(pOperand.getValue()));
    }

    @Override
    public int precedence(){
        return 4;
    }

    @Override
    public int stackPrecedence(){
        return 4;
    }

}
