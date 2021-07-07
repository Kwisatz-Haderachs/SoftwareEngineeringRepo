
/**
 * node constructor
 */
class Node<Key, Value> {
    private Key key;
    private Value val;
    private Node next;

    public Node(Key key, Value val, Node next){
        this.key = key;
        this.val = val;
        this.next = next;
    }

    public Node next(){
        return this.next;
    }

    public Key getKey(){
        return this.key;
    }

    public Value getValue(){
        return this.val;
    }

    public void setKey(Key key){
        this.key = key;
    }

    public void setValue(Value val){
        this.val = val;
    }
}
