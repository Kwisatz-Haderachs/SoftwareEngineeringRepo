/*
This class shall implement one public class method int search(ArrayList<Student> pList, String pKey)
which searches the Roster for a student with the specified last name stored in pKey.
Since the roster is sorted into ascending order by last name,
you shall implement either the iterative or recursive binary search algorithm.
The method returns the index of the student in the list or -1 if the student is not found.
Template not provided; use the UML class diagram as a guide and the binary search lecture notes.

*/
public class Searcher {
    private static int pBegin = 0;

    public static int search(ArrayList<Student> pList, String pKey) {
        int pEnd = pList.size();

        //System.out.println("Search started");
        //System.out.println(pEnd);
        if(pList.isEmpty()){
            //System.out.println("Empty list");
            return -1;
        } else if(pBegin > pEnd){
            return -1;
        } else{
            //System.out.println(pList.get(pBegin).getLastName());
            //System.out.println(pKey);
            if(pList.get(pBegin).getLastName().equals(pKey)){
                //System.out.println("target found");
                return pBegin;
            } else{
                pBegin += 1;
                return search(pList, pKey);
            }
        }
    }

}
