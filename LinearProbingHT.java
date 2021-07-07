import java.util.LinkedList;
import java.util.Queue;

/**
 * linear probing hash table
 */
public class LinearProbingHT<Key, Value> implements SymbolTable{
    private static final int CAPACITY = 997;
    private int n;
    private int m;
    private Entry<Key, Value>[] table;


    public LinearProbingHT(){
        this(CAPACITY);
    }
    public LinearProbingHT(int capacity){
        super();
        m = capacity;
        n = 0;
        table = new Entry[m];
    }

    public int hash(Object key){
        int i = 0;
        int k = ((key.hashCode()&0x7fffffff) + i) % m;
        while(table[i] !=null){
            k = hash(k, i++);
        }
        return k;
    }

    private int hash(Object key, int index){
        int i = index;
        int k = ((key.hashCode()&0x7fffffff) + i) % m;
        return k;
    }

    @Override
    public void put(Object key, Object val){
     int probeCount = 0;
        int hash = hash(key);
        while (table[hash] != null && !table[hash].getK().equals(key) && probeCount <= m) {
            hash = (hash + 1) % m;
            probeCount++;
        }
        if (probeCount <= m) {
            if (table[hash] != null) {
                table[hash].setV((Value)val);
            } else {
                table[hash] = new Entry(key, val);
                probeCount++;
                n++;
            }
        }

    }
    @Override
    public Value get(Object key){
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        int probeCount = 0;
        for(int hash = hash(key); table[hash] != null;  hash = (hash + 1) % m) {
            if (table[hash] != null && probeCount <= m) {
                return table[hash].getV();
            }
            probeCount++;
        }

        return null;
    }

    /**
     * Do not degrade performance by using tags or extra nulls;
     * you must update the probe sequence containing the element being deleted.
     * [8 points extra credit]
    */
    @Override
    public void delete(Object key){
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        int probeCount = 0;
        int hash = hash(key);
        while (table[hash] != null && !table[hash].getK().equals(key) && probeCount <= m) {
            hash = (hash + 1) % m;
            probeCount++;
        }
        if (table[hash] != null && probeCount <= m) {
            table[hash].setV(null);
            table[hash].setK(null);
            n--;
        }
        hash = (hash + 1) % m;
        while (table[hash] != null){
            Key rehashK = table[hash].getK();
            Value rehashV = table[hash].getV();
            table[hash].setK(null);
            table[hash].setV(null);
            n--;
            put(rehashK, rehashV);
            hash = (hash + 1) % m;
        }

    }

    @Override
    public boolean contains(Object key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty(){
        return size()==0;
    }
    @Override
    public int size(){
        return n;
    }
    @Override
    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < m; i++)
            if (table[i] != null){
                queue.add(table[i].getK());
            }
        return queue;
    }
}
