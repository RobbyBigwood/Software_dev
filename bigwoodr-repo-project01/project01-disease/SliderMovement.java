import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class SliderMovement extends SliderPanel {

    private final MessageBoard messageBoard;

    public SliderMovement(MessageBoard messageBoard, int row, int column) {
        
        super(row, column);

        this.messageBoard = messageBoard;

        title.setText("Movement Period");
        
        slider.setMinimum(1);
        slider.setMaximum(4);
        slider.setValue(1);

        leftLabel = new JLabel(slider.getMinimum() + " day");
        rightLabel = new JLabel(slider.getMaximum() + " days");


       

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            messageBoard.update(Message.MOVEMENT, value);
        });
    }
}

