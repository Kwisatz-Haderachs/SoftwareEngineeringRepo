package cranmerhashtable;
/**
 * symbol table constructor
 */
public class Entry<Key, Value> {
    private Key key;
    private Value val;

    public Entry(Key key, Value val){
        this.key = key;
        this.val = val;

    }

    public Key getK(){return this.key;}
    public Value getV(){return this.val;}
    public void setK(Key key){this.key = key;}
    public void setV(Value val){this.val = val;}
}
