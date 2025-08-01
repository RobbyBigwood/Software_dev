import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderDuration extends SliderPanel {

    public SliderDuration(MessageBoard messageBoard,int x, int y) {
        // Call the parent constructor to set the layout/bounds, etc.
        super(x, y);

        // Adjust the slider for a 1 to 10 day range:
        slider.setMinimum(1);
        slider.setMaximum(10);
        slider.setValue(5);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(1));


        // Customize the title and labels
        title.setText("Duration of Infection");
        leftLabel.setText("1 day");
        rightLabel.setText("10 days");
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slider.getValue();
                // Update the MessageBoard whenever this slider changes
                MessageBoard.update(Message.DURATION, currentValue);
                // Update the local "valueLabel" so the user sees the new value
                valueLabel.setText(String.valueOf(currentValue));
            }
        });
    }
}