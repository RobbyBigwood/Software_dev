// this class is basically the same as the slider incubation class but I just needed to tweak a few small things

import javax.swing.JLabel;
import javax.swing.JSlider;

public class SliderMorbidity extends SliderPanel {
    

    private final MessageBoard messageBoard;

    // constructor
    public SliderMorbidity(MessageBoard messageBoard, int row, int column) {
        super(row, column);
        
        // setting the message board
        this.messageBoard = messageBoard;

        // setting the title as requested
        title.setText("Morbidity Rate");
        

        // setting the range, no range function so need to set a min and max
        slider.setMinimum(0);
        slider.setMaximum(100);
        // setting the initial value
        slider.setValue(1);

        // setting up the labels for message board
        leftLabel = new JLabel(slider.getMinimum() + " day");
        rightLabel = new JLabel(slider.getMaximum() + " days");


       

        // updating the message board using the action listener
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            messageBoard.update(Message.MORBIDITY, value);
        });
        

        
        }





}
