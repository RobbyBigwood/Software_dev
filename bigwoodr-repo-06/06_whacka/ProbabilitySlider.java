import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProbabilitySlider extends JSlider {
    private GameViewingPanel viewingPanel;

    public ProbabilitySlider(GameViewingPanel viewingPanel) {
        super(JSlider.HORIZONTAL, 20, 100, 80); // Min = 20%, Max = 100%, Default = 80%
        this.viewingPanel = viewingPanel;
        
        setMajorTickSpacing(20);
        setMinorTickSpacing(10);
        setPaintTicks(true);
        setPaintLabels(true);
        
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double probability = getValue() / 100.0;
                viewingPanel.cueProbability(probability);
            }
        });
    }
}