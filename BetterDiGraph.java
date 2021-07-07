import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Queue;
/**
 * implements DiGraph
 */

class BetterDiGraph implements EditableDiGraph{
    private int V;
    private int E;
    private HashMap<Integer, LinkedList<Integer>> adj;
    private HashMap<Integer, Integer> degree;
    private int edge;

    public BetterDiGraph(){
        this.E = 0;
        this.V = 0;
        adj = new HashMap<>();
        degree = new HashMap<>();
    }

    @Override
    public void addEdge(int v, int w){
        edge = 1;
        adj.get(v).add((Integer)w);
        if(degree.containsKey((Integer)v) == true){
            int edges = degree.get((Integer)v);
            degree.replace((Integer)v, (Integer)edges+1);
        } else {
            degree.put((Integer)v, (Integer)edge);
        }
        E++;
    }

    /**
     * Adds a vertex to the graph. Does not allow duplicate vertices.
     * @param v vertex number
     */
    @Override
    public void addVertex(int v){
        if(adj.containsKey(v)==false){
            adj.put(v, new LinkedList<>());
            V++;
        }
        //Assumes we will not be updating any vertices with new values as
        //that wouldn't be consistent with the hashtable
    }

    /**
     * Returns the direct successors of a vertex v.
     * @param v vertex
     * @return successors of v
     */
    @Override
    public Iterable<Integer> getAdj(int v){
        if(adj.get(v)== null) return null;
        return adj.get(v);
    }

    /**
     * Number of edges.
     * @return edge count
     */
    @Override
    public int getEdgeCount(){
        return E;
    }

    /**
     * Returns the in-degree of a vertex.
     * @param v vertex
     * @return in-degree.
     * @throws NoSuchElementException exception thrown if vertex does not exist.
     */
    @Override
    public int getIndegree(int v) throws NoSuchElementException{
        if(degree.get(v) == null) return 0;
        return degree.get(v);
    }

    /**
     * Returns number of vertices.
     * @return vertex count
     */
    @Override
    public int getVertexCount(){
        return V;
    }

    /**
     * Removes edge from graph. If vertices do not exist, does not remove edge.
     * @param v source vertex
     * @param w destination vertex
     */
    @Override
    public void removeEdge(int v, int w){
        if(degree.get(v) == null) return;
        adj.get(v).remove(w);
        E--;
    }

    /**
     * Removes vertex from graph. If vertex does not exist, does not try to
     * remove it.
     * @param v vertex
     */
    @Override
    public void removeVertex(int v){
        //adj.get(v);
        if(degree.containsKey((Integer)v) == true){
            adj.remove(v);
            V--;
        }
    }

    public int largestInDegree(){
        int max = 0;
        Iterable vertices = vertices();
        for(Object v : vertices){
            if(getIndegree((int)v) > max){
                max = getIndegree((int)v);
            }
        }
        return max;
    }

    //simple printer functon used for debugging/testing
    public void printer(){
        Iterable vertices = vertices();
        for(Object v : vertices){
            System.out.println("Vertex " + v + " in degree: " + getIndegree((int)v));
        }
        System.out.println("Total: " + getVertexCount());
    }

    /**
     * Returns iterable object containing all vertices in graph.
     * @return iterable object of vertices
     */
    @Override
    public Iterable<Integer> vertices(){
        Iterator hm = adj.entrySet().iterator();
        Queue<Integer> vertices = new LinkedList<>();

        while(hm.hasNext()){
            Map.Entry next = (Map.Entry) hm.next();
            Integer vertex = (Integer) next.getKey();
            vertices.add(vertex);
        }
        return vertices;
    }
}
