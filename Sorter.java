import java.util.ArrayList;

/*
A class which implements the quicksort algorithm.
All of the method are static and sort(ArrayList <Student> pList) is the only public method
and calls private quickSort(pList, 0, pList.size() - 1) to sort the list.
Template not provided; use the UML class diagram and the quicksort lecture notes.
*/

public class Sorter {

    public static void sort(ArrayList<Student>pList){
        quickSort(pList, 0, pList.size()-1);
    }

    private static void quickSort(ArrayList<Student> pList, int pLow, int pHigh) {

    }

}
