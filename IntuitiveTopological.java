import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
/**
 * implements an intutitive topological sort
 */
public class IntuitiveTopological implements TopologicalSort {
    /**
     * Returns an iterable object containing a topological sort.
     * @return a topological sort.
     */
    private Stack<Integer> topo;

    /**
     * Instead of using DFS to find a topological sort, implement the following algorithm:
     * "IntuitiveTopological". This algorithm works as follows: look at your graph,
     * pick out a node with in-degree zero, add it to the topological ordering,
     * and remove it from the graph. This process repeats until the graph is empty.
     * Make sure to check for cycles before trying to generate a topological sort of the graph!
     * @param G
     */

    public IntuitiveTopological(BetterDiGraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if(!cyclefinder.hasCycle()){
            topo = new Stack<>();
            int degree = 0;
            int largestDegree = G.largestInDegree();
            while(degree <= largestDegree){
                sorting(G, degree);
                degree++;
            }
        }
    }

    private void sorting(BetterDiGraph G, int degree){
        Iterable vertices = G.vertices();
        for(Object v : vertices){
            if(G.getIndegree((int)v) == degree){
                topo.push((int)v);
                G.removeVertex((int)v);
            }
        }

    }

    @Override
    public Iterable<Integer> order(){
        Queue<Integer> sorted = new LinkedList<>();
        while(isDAG() == false){
            Integer top = topo.pop();
            sorted.add(top);
        }
        return sorted;
    }

    /**
     * Returns true if the graph being sorted is a DAG, false otherwise.
     * @return is graph a DAG
     */
    @Override
    public boolean isDAG(){
        return topo.isEmpty();
    }
}
