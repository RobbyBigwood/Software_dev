import javax.swing.JLabel;

public class SliderIncubation extends SliderPanel {

    private final MessageBoard messageBoard;

    // constructor
    public SliderIncubation(MessageBoard messageBoard, int row, int column) {
        super(row, column);
        
        // setting the message board
        this.messageBoard = messageBoard;

        // setting the title as requested
        title.setText("Incubation Period");

        // setting the range, no range function so need to set a min and max
        slider.setMinimum(1);
        slider.setMaximum(7);
        // setting the initial value
        slider.setValue(1);

        // setting up the labels for message board
        leftLabel = new JLabel(slider.getMinimum() + " day");
        rightLabel = new JLabel(slider.getMaximum() + " days");


       

        // updating the message board using the action listener
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            messageBoard.update(Message.INCUBATION, value);
        });
    }
}
