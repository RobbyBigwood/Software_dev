import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Font;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderPrevention extends SliderPanel{
    public SliderPrevention(MessageBoard messageBoard, int x, int y) {
        // Pass the positions to the superclass
        super(x, y);

        // Set a clear title for the slider
        title.setText("Prevention");

        // If needed, explicitly set the slider's range
        // (in case your SliderPanel doesn't do so by default)
        slider.setMinimum(0);
        slider.setMaximum(3);
        slider.setMajorTickSpacing(1);
        
        
        // Optionally set an initial slider value
        slider.setValue(0);

        // Listen for changes: update the MessageBoard each time it moves
        slider.addChangeListener(e -> {
            int currentValue = slider.getValue();
            MessageBoard.update(Message.PREVENTION, currentValue);
        });
    }
}
