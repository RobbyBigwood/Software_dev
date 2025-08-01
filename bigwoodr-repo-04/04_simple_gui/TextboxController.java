import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextboxController {

    JFrame frame;

    Integer textboxValue = 0;
    protected final JTextField textbox = new JTextField(textboxValue);
    protected final JButton textSubmitButton = new JButton("submit");
    protected final JLabel textboxLabel = new JLabel(textboxValue.toString());

    public TextboxController(JFrame frame) {
        this.frame = frame;
    }
    public void setInFrame(int column, int row) {

        // Set up text box for user to enter value Lower Left
        textbox.setBounds(column, row, 100, 100);
        textbox.setFont(new Font("Verdana", Font.PLAIN, 36));
        frame.getContentPane().add(textbox);

        JLabel inputLabel = new JLabel ("Enter value between 0 and 100");
        inputLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        inputLabel.setBounds(column, row - 50, 300, 30);
        frame.getContentPane().add(inputLabel);

        //set submit button position
        textSubmitButton.setBounds(column+150,row,100,100);
        textSubmitButton.setFont(new Font("Verdana", Font.PLAIN, 16));

        // Control the display of the button (so it is a solid filled color)
        textSubmitButton.setOpaque(true);
        textSubmitButton.setContentAreaFilled(true);
        textSubmitButton.setBorderPainted(false);
        textSubmitButton.setFocusPainted(false);
        textSubmitButton.setBackground(new Color(187, 133, 136));
        textSubmitButton.addActionListener(submitListener);
        frame.getContentPane().add(textSubmitButton);

        textboxLabel.setFont(new Font("Verdana", Font.PLAIN, 36));
        textboxLabel.setBounds(column, row + 110, 100, 100);
        frame.getContentPane().add(textboxLabel);
    }
    //Getter and setters
    public JButton getTextSubmitButton() {
        return textSubmitButton;
    }

    ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked on submit");

            if(textbox.getText().isEmpty()) {
                return;
            }
            else if(Integer.parseInt(textbox.getText()) < 0 || Integer.parseInt(textbox.getText()) > 100) {
                return;
            }
            else {
                textboxLabel.setText(textbox.getText());
                textbox.setText("");
            }
        }
    };
}
