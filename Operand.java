/**
 * An operand is a numeric value represented as a Double.
 */
public class Operand extends Token{

    private double mValue;

    public Operand(double pValue){
        mValue = 0.0;
        setValue(pValue);
    }

    public double getValue(){
        return mValue;
    }

    public void setValue(double pValue){
        mValue = pValue;
    }
}

