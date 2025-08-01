
public class SliderTransmission extends SliderPanel {

    public SliderTransmission(MessageBoard messageBoard, int x, int y) {
        // Pass the positions to the superclass
        super(x, y);

        // Set a clear title for the slider
        title.setText("Transmission Probability");

        // If needed, explicitly set the slider's range
        // (in case your SliderPanel doesn't do so by default)
        slider.setMinimum(0);
        slider.setMaximum(100);
        
        // Optionally set an initial slider value
        slider.setValue(50);

        // Listen for changes: update the MessageBoard each time it moves
        slider.addChangeListener(e -> {
            int currentValue = slider.getValue();
            MessageBoard.update(Message.TRANSMISSION, currentValue);
        });
        
    }
}
