import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * GradebookWriter inherits from PrintWriter and writes the gradebook info to the file whose name
 * is passed to the ctor.
 */
public class GradebookWriter extends PrintWriter {

    private PrintWriter mOut;
    /**
     * Call the super class ctor that takes a String as the argument, i.e, PrintWriter(String).
     * The PrintWriter ctor opens the file named by pFname for writing. It will throw a
     * FileNotFoundException if the file could not be opened for writing. We throw the exception
     * here as well where it will eventually be caught in Main.exit() -- see SR 7.
     *
     * @param pFname The name of the output file to be opened for writing.
     */

    public GradebookWriter(String pFname) throws FileNotFoundException{
        super(pFname);
        try{
            mOut = new PrintWriter(new File(pFname));

        } catch (FileNotFoundException eException) {
            System.out.println(eException);
        }
    }

    /**
     * Writes the gradebook info to the output file which was opened in the ctor.
     *
     * @param pRoster The roster of students.
     *
     * PSEUDOCODE:
     * method writeGradebook(pRoster : Roster() : void
     *     EnhancedFor each student in pRoster.getStudentList() Do
     *         Call println(student)
     *     End For
     *     Call close()
     * end writeGradebook
     */
    public void writeGradebook(Roster pRoster){
        //System.out.println(pRoster.getStudentList().get(0));
        for(Student student: pRoster.getStudentList()){
            mOut.println(student);
        }
        mOut.close();
    }
}
