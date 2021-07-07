import java.util.HashMap;
import java.util.Stack;
/**
 *
 */
public class DirectedCycle {
    private HashMap<Integer, Boolean> marked;
    private HashMap<Integer, Integer> edgeTo;
    private HashMap<Integer, Boolean> onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(BetterDiGraph G){
        onStack = new HashMap<>();
        edgeTo = new HashMap<>();
        marked = new HashMap<>();

        Iterable vertex = G.vertices();
        for(Object v: vertex){
            if(marked.get((int)v) == null){
                dfs(G, (int) v);
            }
        }
    }

    public void dfs(BetterDiGraph G, int v){
        onStack.put(v, Boolean.TRUE);
        marked.put(v, Boolean.TRUE);
        //System.out.println(G.getAdj(v));
        if(G.getAdj(v) == null){ return;}

        for(int w: G.getAdj(v)){
            if(this.hasCycle()){
                return;
            }
            //!marked[w]
            else if (marked.get(w) == null){
                edgeTo.put(w, v);
                dfs(G, w);
            }
            else if (onStack.get(w) == true){
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo.get(x)){
                    cycle.push(x);
                    cycle.push(v);
                }
            }
        }
        onStack.put(v, Boolean.FALSE);

    }

    public boolean hasCycle(){ return cycle != null; }
    public Iterable<Integer> cycle(){ return cycle; }
}
