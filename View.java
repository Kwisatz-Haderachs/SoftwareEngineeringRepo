import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// tester: (-1 - -2) * -(3 / 5)
/**
 * The View class implements the GUI.
 */
public class View extends JFrame implements ActionListener {

    public static final int FRAME_WIDTH  = 500;
    public static final int FRAME_HEIGHT = 180;

    // Declare instance variables
    private JButton         mClearButton;
    private JButton         mEvalButton;
    private JTextField      mInputText;
    private JButton         mExitButton;
    private Main            mMain;
    private JLabel          mResultLabel;

    /**
     * View()
     *
     * The View constructor creates the GUI interface and makes the frame visible at the end.
     */
    public View(Main pMain) {
        // Save a reference to the Main object pMain in mMain.
        mMain = pMain;

        JPanel panelLabel = new JPanel();
        mResultLabel = new JLabel("");
        panelLabel.add(mResultLabel);

        JPanel panelInput = new JPanel();
        mInputText = new JTextField(40);
        panelInput.add(mInputText);

        JPanel panelButtons = new JPanel();
        mClearButton = new JButton("Clear");
        mClearButton.addActionListener(this);
        mEvalButton = new JButton("Evaluate");
        mEvalButton.addActionListener(this);
        mExitButton = new JButton("Exit");
        mExitButton.addActionListener(this);
        panelButtons.add(mClearButton);
        panelButtons.add(mEvalButton);
        panelButtons.add(mExitButton);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.add(javax.swing.Box.createVerticalGlue());
        panelMain.add(panelLabel);
        panelMain.add(javax.swing.Box.createVerticalGlue());
        panelMain.add(panelInput);
        panelMain.add(javax.swing.Box.createVerticalGlue());
        panelMain.add(panelButtons);
        panelMain.add(javax.swing.Box.createVerticalGlue());

        setTitle("Kalkutron-9001");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panelMain);
        setVisible(true);
    }

    /**
     * actionPerformed()
     *
     * Called when one of the JButtons is clicked. Detects which button was clicked and handles it.
     */
    @Override
    public void actionPerformed(ActionEvent pEvent){
        if(pEvent.getSource() == mClearButton){
            clear();
        } else if (pEvent.getSource() == mEvalButton){
            evaluate();
        } else if(pEvent.getSource() == mExitButton){
            mMain.exit();
        }
    }

    /**
     * clear() is called when the Clear button is clicked. Set the text in mInputText and mResultLabel to the
     * empty strings "".
     */
    public void clear(){
        mInputText.setText("");
        mResultLabel.setText("");
    }

    /**
     * evaluate() is called when the Evaluate button is clicked.
     */
    public void evaluate(){
        String text = mInputText.getText();
        Expression expr = new Expression(text);
        Double result = expr.evaluate();
        mResultLabel.setText(result.toString());
    }

    /**
     * messageBox()
     */
    public void messageBox(String pMessage) {
        JOptionPane.showMessageDialog(this, pMessage, "Message", JOptionPane.PLAIN_MESSAGE);
    }

}
