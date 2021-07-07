import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * implements a topological sort algorithim
 */
public class TopologicalSort {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args){
        SymbolTable<Integer, String> st = new TwoProbeChainHT<>();

        String input[];
        int count = 0;
        Integer ID;
        String kanji;
        Integer kanji_ID;
        Integer kanji_DST;

        //Load data-kanji.txt, use it to populate a hashtable that maps IDs to characters,
        //and add the IDs as nodes in the graph.
        try{
            BufferedReader indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-kanji.txt")), "UTF8"));
            for (String line = indexReader.readLine(); line != null; line = indexReader.readLine()){
                line = line.replaceAll("#", "");
                input = line.split("\\s+");

                if((count != 0)&&(line.length() > 1)&&(input[0].length()<4)){
                    ID = Integer.parseInt(input[0]);
                    kanji = input[1];
                    st.put(ID, kanji);
                }
                count++;
            }
            indexReader.close();
        } catch (IOException e){
            throw new IllegalStateException(e.getMessage(), e);
        }

        BetterDiGraph G = new BetterDiGraph();
        Iterable keys = st.keys();
        for(Object key : keys){
            G.addVertex((int) key);
        }

        /**
         * Load data-components.txt, and use it to add edges to the graph being built.
         * Create an IntuitiveTopological object, and use it to sort the graph.
         * Display the characters in the ordering.
         * Note that topological sort will produce a list of a IDs
         * - you'll need to take the IDs and uses them to look up the
         * correct character in the hashtable you populated earlier.
         */

        count = 0;
        try{
            BufferedReader compReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-components.txt")), "UTF8"));
            for (String line = compReader.readLine(); line != null; line = compReader.readLine()){
                line = line.replaceAll("#", "");
                input = line.split("\\s+");

                if(count != 0){
                    kanji_ID = Integer.parseInt(input[0]);
                    kanji_DST = Integer.parseInt(input[1]);
                    G.addEdge(kanji_ID, kanji_DST);
                }
                count++;
            }
            compReader.close();
        } catch (IOException e){
            throw new IllegalStateException(e.getMessage(), e);
        }

        System.out.println("After input");
        Iterable vertex = G.vertices();
        for(Object v : vertex){
            System.out.print(st.get((int)v));
        }

        //print a list representation of descendants for each kanji
        System.out.print("\nDescendants");
        for(Object v : vertex){
            System.out.print("\n"+st.get((Integer)v) + ": ");
            Iterable desc = G.getAdj((int)v);
            for(Object d: desc){
                System.out.print(" "+st.get((Integer)d));
            }
        }
        //show the results of the toplogical sorting
        System.out.println("\nAfter topological sort");
        IntuitiveTopological I = new IntuitiveTopological(G);
        Iterable sort = I.order();
        for(Object v : sort){
            System.out.print(st.get((Integer)v));
        }

        /**
         * The IDE that I am using doesn't appear to have good support for GraphViz,
         * as such I was unable to get graphviz working and generate a schematic.
        */
    }
}

