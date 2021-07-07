import java.util.LinkedList;
import java.util.Queue;
/**
 * two probe chain hashtable implementation
 */
public class TwoProbeChainHT<Key,Value> implements SymbolTable{
    private static final int CAPACITY = 997;
    private int n;
    private int m;
    private Node[] chain;

    public TwoProbeChainHT(){
        this(CAPACITY);
    }

    public TwoProbeChainHT(int m){
        this.m = m;
        chain = new Node[m];
    }

    private int hash(Object key){
        int z = key.hashCode();
        z =(z & 0x7fffffff)% m;
        return z;
    }

    private int secondHash(Object key){
        int z = key.hashCode();
        z =(((z & 0x7fffffff)% m)*31)%m;
        return z;
    }

    @Override
    public void put(Object key, Object value){
        int i = hash(key);
        int j = secondHash(key);
        int c = 0; int k = 0;
        for (Node x = chain[i]; x != null; x = x.next()) {
            if (key.equals(x.getKey())) {
                x.setValue(value);
                return;
            }
            c++;
        }
        for(Node y = chain[j]; y != null; y = y.next()){
            if (key.equals(y.getKey())) {
                y.setValue(value);
                return;
            }
            k++;
        }
        if(c > k){
            chain[j] = new Node(key, value, chain[j]);
        } else {
            chain[i] = new Node(key, value, chain[i]);
        }
        n++;
    }

    @Override
    public Value get(Object key){
        int h = hash(key);
        int j = secondHash(key);
        for(Node x = chain[h]; x != null; x = x.next()){
            if(key.equals(x.getKey())) return (Value) x.getValue();
        }
        for(Node y = chain[j]; y != null; y = y.next()){
            if(key.equals(y.getKey())) return (Value) y.getValue();
        }
        return null;
    }

    @Override
    public void delete(Object key){
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        int i = hash(key);
        int j = secondHash(key);
        for(Node x = chain[i]; x != null; x = x.next()){
            if(key.equals(x.getKey())){
                x.setValue(null);
                n--;
            }
        }
        for(Node y = chain[j]; y != null; y = y.next()){
            if(key.equals(y.getKey())){
                y.setValue(null);
                n--;
            }
        }

    }
    @Override
    public boolean contains(Object key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public int size(){
        return n;
    }

    @Override
    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (Node x = chain[i]; x != null; x = x.next()) {
                queue.add((Key) x.getKey());
            }
        }
        return queue;
    }

}
