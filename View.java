import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The View class implements the GUI. It is a subclass of JFrame and implements the ActionListener
 * interface so that we can respond to user-initiated GUI events.
 */
public class View extends JFrame implements ActionListener {

    //The width of the View frame.
    private static final int FRAME_WIDTH = 525;

    //The height of the View frame
    private static final int FRAME_HEIGHT = 225;

    private Main mMain;

    /**
     * When the View() ctor is called from Main.run() to create the View, run() passes a reference
     * to the Main object as the argument to View(). We save that reference into mMain and then
     * later we can use mMain to communicate with the Main class.
     *
     * mMain is made accessible within this class via accessor/mutator methods getMain() and
     * setMain(). It shall not be directly accessed.
     */
    public View() {
    }

    // Declare GUI related instance variables for the buttons and text fields.
    private JTextField mHomeworkText[];
    private JTextField mExamText[];
    private JTextField mStudentName;
    private JButton mSearchButton;
    private JButton mSaveButton;
    private JButton mExitButton;
    private JButton mClearButton;

    /**
     * View()
     *
     * The View constructor creates the GUI interface and makes the frame visible at the end.
     *
     * @param pMain is an instance of the Main class. This links the View to the Main class so
     * they may communicate with each other.
     */
    public View(Main pMain) {

        /**
         * Save a reference to the Main object pMain into instance var mMain by calling setMain().
         */
        setMain(pMain);

        JPanel panelSearch = new JPanel();
        JLabel labelStudent = new JLabel("Student name: ");
        mStudentName = new JTextField(25);
        panelSearch.add(labelStudent);
        panelSearch.add(mStudentName);

        mSearchButton = new JButton("Search");
        mSearchButton.addActionListener(this);
        panelSearch.add(mSearchButton);

        JPanel panelHomework = new JPanel();
        JLabel labelHomework = new JLabel("Homework: ");
        panelHomework.add(labelHomework);
        mHomeworkText = new JTextField[getMain().getNumHomeworks()];
        for(int i = 0; i < getMain().getNumHomeworks(); i++){
            mHomeworkText[i] = new JTextField(5);
            panelHomework.add(mHomeworkText[i]);
        }

        JPanel panelExam = new JPanel();
        JLabel labelExam = new JLabel("Exam: ");
        panelExam.add(labelExam);
        mExamText = new JTextField[getMain().getNumExams()];
        for(int i = 0; i < getMain().getNumExams(); i++){
            mExamText[i] = new JTextField(5);
            panelExam.add(mExamText[i]);
        }

        JPanel panelButtons = new JPanel();
        mClearButton = new JButton("Clear");
        mSaveButton = new JButton("Save");
        mExitButton = new JButton("Exit");
        mClearButton.addActionListener(this);
        mSaveButton.addActionListener(this);
        mExitButton.addActionListener(this);
        panelButtons.add(mClearButton);
        panelButtons.add(mSaveButton);
        panelButtons.add(mExitButton);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.add(panelSearch);
        panelMain.add(javax.swing.Box.createVerticalGlue());
        panelMain.add(panelHomework);
        panelMain.add(javax.swing.Box.createVerticalGlue());
        panelMain.add(panelExam);
        panelMain.add(javax.swing.Box.createVerticalGlue());
        panelMain.add(panelButtons);

        // Set the title of the View to whatever you want by calling setTitle()
        setTitle("Gred :: Gradebook Editor");

        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame();

        frame.add(panelMain);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent pEvent){
        if(pEvent.getSource() == mSearchButton){
            //clear numbers in text fields
            String lastName = mStudentName.getText();
            if (lastName.isEmpty()){
                messageBox("Please enter the student's last name.");
            }
            else {
                //System.out.println("input: " + lastName);
                Student searchStudent = getMain().search(lastName);
                if (searchStudent != null){
                    Student.setCurrStudent(searchStudent);
                    if(Student.getCurrStudent() == null){
                        messageBox("Student not found. Try again.");
                    } else {
                        displayStudent(Student.getCurrStudent());
                    }
                }
            }
        } else if (pEvent.getSource() == mSaveButton){
            if (Student.getCurrStudent() != null) {
                //System.out.println(Student.getCurrStudent());
                saveStudent(Student.getCurrStudent());
            }
        } else if (pEvent.getSource() == mClearButton){
            clear();
        } else if (pEvent.getSource() == mExitButton){
            if (Student.getCurrStudent() != null) {
                saveStudent(Student.getCurrStudent());
            }
            getMain().exit();
        }
    }

    public void clear(){
        //mStudentName.setText("");
        //clearNumber();
        Student.setCurrStudent(null);
    }

    public void clearNumber(){
        for(int i = 0; i < getMain().getNumExams(); i++){
            mExamText[i].setText("");
        }
        for(int i = 0; i < getMain().getNumHomeworks(); i++){
            mHomeworkText[i].setText("");
        }
    }

    public void displayStudent(Student pStudent){
         for (int i = 0; i < getMain().getNumHomeworks(); i++){
            int hw = pStudent.getHomework(i);
            String hwstr = Integer.toString(hw);
            mHomeworkText[i].setText(hwstr);
        }
        for (int i = 0; i < getMain().getNumExams(); i++){
            int ex = pStudent.getExam(i);
            String exstr = Integer.toString(ex);
            mExamText[i].setText(exstr);
        }

    }


    //Accessor method for mMain.
    private Main getMain() {
        return mMain;
    }

    public void messageBox(String pMessage) {
        JOptionPane.showMessageDialog(this, pMessage, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    public void saveStudent(Student pStudent){
        for (int i = 0; i < getMain().getNumHomeworks(); i++){
            String hwstr = mHomeworkText[i].getText();
            int hw = Integer.parseInt(hwstr);
            pStudent.setHomework(i, hw);
        }

        for (int i = 0; i < getMain().getNumExams(); i++){
            String examstr = mExamText[i].getText();
            int ex = Integer.parseInt(examstr);
            pStudent.setExam(i, ex);
        }
    }

    // Mutator method for mMain.
    private void setMain(Main pMain) {
        mMain = pMain;
    }

}
