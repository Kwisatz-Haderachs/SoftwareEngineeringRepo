/**
 * Quad probing hashtable
 */
public class QuadProbingHT<Key,Value> extends LinearProbingHT{
    private static final int CAPACITY = 997;
    private int n;
    private int m;
    private Entry<Key, Value>[] table;


    public QuadProbingHT(){
        this(CAPACITY);
    }
    public QuadProbingHT(int capacity){
        super();
        m = capacity;
        n = 0;
        table = new Entry[m];
    }

    @Override
    public int hash(Object key){
        int i = 0;
        int k = ((key.hashCode()&0x7fffffff) + i) % m;
        while(table[i] !=null){
            k = hash(k, i);
            i++;
        }
        return k;
    }
    private int hash(Object key, int index){
        int i = index;
        int k = ((key.hashCode()&0x7fffffff) + i*i) % m;
        return k;
    }



}

