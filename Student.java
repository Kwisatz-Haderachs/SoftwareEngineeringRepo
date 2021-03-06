import java.util.ArrayList;

/**
 * The Student class stores the gradebook information for one Student.
 *
 * Note that Student implements the java.lang.Comparable<Student> interface because we are going
 * to be sorting the roster of students by last name so we need to be able to compare the last
 * names of two students A and B to determine if A < B, or if A = B, or if A > B. See the
 * compareTo() method.
 */
public class Student implements Comparable<Student> {

    /**
     * mCurrStudent is a reference to the Student object which is currently being displayed and
     * edited in the View. It should only be accessed via accessor/mutator methods.
     */
    private static Student mCurrStudent;

    /**
     * mExamList is an ArrayList of Integers storing the student's exam scores.
     */
    private ArrayList<Integer> mExamList;

    /**
     * The student's first name.
     */
    private String mFirstName;

    /**
     * mHomeworkList is an ArrayList of Integers storing the student's homework scores.
     */
    private ArrayList<Integer> mHomeworkList;

    /**
     * The student's last name.
     */
    private String mLastName;

    /**
     * Student()
     *
     * PSEUDOCODE:
     * method Student(pFirstName : String, pLastName : String)
     *     save parameters pFirstName and pLastName to instance variables by calling mutators
     *     -- Note that we only create the exam list here, it will be populated later
     *     create an ArrayList<Integer> and pass it off to setExamList()
     *     -- Note that we only create the homework list here, it will be populated later
     *     create an ArrayList<Integer> and pass it off to setHomeworkList()
     * end Student()
     */
    public Student(String pFirstName, String pLastName){
        setFirstName(pFirstName);
        setLastName(pLastName);
        ArrayList <Integer> mExamList = new ArrayList<>();
        setExamList(mExamList);
        ArrayList <Integer> mHomeworkList = new ArrayList<>();
        setHomeworkList(mHomeworkList);
    }

    /**
     * addExam()
     *
     * Adds an exam score pScore to the exam list
     *
     * @param pScore
     *
     * PSEUDOCODE:
     * method addExam(pScore : int) : void
     *     call add(pScore) on getExamList() to add a new exam score to the list of exam scores.
     * end addExam
     */
    public void addExam(int pScore){
        getExamList().add(pScore);
    }

    /**
     * addHomework()
     *
     * Adds a homework score pScore to the homework list
     *
     * @param pScore
     *
     * PSEUDOCODE:
     * method addHomework(pScore : int) : void
     *     call add(pScore) on getHomeworkList() to add a new homework score to the list of
     *     homework scores
     * end addHomework
     */
    public void addHomework(int pScore){
        getHomeworkList().add(pScore);
    }

    /**
     * compareTo()
     *
     * @param pStudent is a Student
     *
     * This method compares the last name of 'this' Student to the last name of pStudent to
     * determine if the last name of 'this' Student is <, =, or > the last name of pStudent.
     * It is called during the quick sort routine in Sorter.partition().
     *
     * Provide the annotation that prevents accidental overloading since we are overriding the
     * String.compareTo() method.
     *
     * PSEUDOCODE:
     * method compareTo(pStudent : Student) : int
     *     return: negative int if the last name of this Student is < the last name of pStudent
     *     return: zero if the last name of this Student is = the last name of pStudent
     *     return: positive int if the last name of this Student is > the last name of pStudent
     *     hint: the last names are Strings and String already implements compareTo().
     * end compareTo
     */
    public int compareTo(Student pStudent){

        if(this.getLastName().compareTo(pStudent.getLastName()) == -1){
            return -1;
        } else if (this.getLastName().equals(pStudent.getLastName())){
            return 0;
        } else if (this.getLastName().compareTo(pStudent.getLastName()) == 1) {
            return 1;
        } else {
            return -2;
        }
    }

    /**
     * Accessor method for mCurrStudent.
     */
    public static Student getCurrStudent() {
        return mCurrStudent;
    }

    /**
     * getExam()
     *
     * Accessor method to retrieve an exam score from the list of exams.
     *
     * @param pNum The exam number for which we wish to retrieve the score.
     *
     * @return The exam score.
     *
     * Hint: Call getExamList() to get the ArrayList<Integer> object storing the exam scores.
     * Since that object is an ArrayList<Integer>, we next call the get(index) method to retrieve
     * the correct exam score.
     */
    public int getExam(int pNum){
        return getExamList().get(pNum);
    }

    /**
     * getExamList()
     * Accessor method for mExamList.
     */
    private ArrayList<Integer> getExamList() {
        return mExamList;
    }

    /**
     * getFirstName()
     * Accessor method for mFirstName.
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * getFullName()
     *
     * Returns the student's full name in the format: "lastname, firstname".
     */
    public String getFullName(){
        String result = getLastName() + " " + getFirstName() + " ";
        return result;
    }

    /**
     * getHomework()
     *
     * Accessor method to retrieve a homework score from the list of homeworks.
     *
     * @param pNum The homework number for which we wish to retrieve the score.
     *
     * @return The homework score.
     *
     * Hint: Call getHomeworkList() to get the ArrayList<Integer> object storing the hw scores.
     * Since that object is an ArrayList<Integer>, we next call the get(index) method to retrieve
     * the correct hw score.
     */
    public int getHomework(int pNum){
        return getHomeworkList().get(pNum);
    }

    /**
     * getHomeworkList()
     * Accessor method for mHomeworkList.
     */
    public ArrayList<Integer> getHomeworkList(){
        return mHomeworkList;
    }

    /**
     * getLastname()
     * Accessor method for mLastName.
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Mutator method for mCurrStudent.
     */
    public static void setCurrStudent(Student pCurrStudent) {
        mCurrStudent = pCurrStudent;
    }

    /**
     * setExam()
     *
     * Mutator method to store an exam score into the list of exam scores.
     *
     * @pNum is the index into the list of exams, where 0 is the index of the first exam score.
     *
     * See the hint for getExam(). This method will be similar, but rather than calling get()
     * on the ArrayList<Integer> object to get a score, we need to call set(index, value) method
     * to set the value in the ArrayList<Integer> at index 'index' to 'value'.
     */
    public void setExam(int index, int pNum){
        getExamList().set(index, pNum);
    }

    /**
     * setExamList()
     * Mutator method for mExamList.
     */
    private void setExamList(ArrayList<Integer> pExamList) {
        mExamList = pExamList;
    }

    /**
     * setFirstName()
     * Mutator method for mFirstName.
     */
    private void setFirstName(String pFirstName) {
        mFirstName = pFirstName;
    }

    /**
     * setHomework()
     *
     * Mutator method to store a homework score into the list of homework scores.
     * @pNum is the index into the list of homeworks, where 0 is the index of the first hw score.
     *
     * See the hint for getHomework(). This method will be similar, but rather than calling get()
     * on the ArrayList<Integer> object to get a score, we need to call set(index, value) method
     * to set the value in the ArrayList<Integer> at index 'index' to 'value'.
     */
    public void setHomework(int index, int pNum){
        //System.out.println(index + " " + pNum);
        getHomeworkList().set(index, pNum);
    }

    /**
     * setHomeworkList()
     * Mutator method for mHomeworkList.
     */
    private void setHomeworkList(ArrayList<Integer> pHomeworkList) {
        mHomeworkList = pHomeworkList;
    }

    /**
     * setLastname()
     * Mutator method for mLastName.
     */
    public void setLastName(String pLastName) {
        mLastName = pLastName;
    }

    /**
     * toString()
     *
     * Returns a String representation of this Student. The format of the returned string shall be
     * such that the Student information can be printed to the output file in this format:
     *
     *     lastname firstname exam1 exam2 exam2 hw1 hw2 hw3 hw4 hw5
     *
     * where the fields are separated by spaces, except there is not space following hw5.
     *
     * Hint: The String class has a method named trim() that removes leading and trailing white-
     * space from a string.
     *
     * Hint: use enhanced for loops
     */
    @Override
    public String toString() {
        String result = getLastName() + " " + getFirstName() + " ";
        for (Integer exam : getExamList()) {
            result += exam + " ";
        }
        for (Integer hw : getHomeworkList()) {
            result += hw + " ";
        }
        return result.trim();
    }
}
