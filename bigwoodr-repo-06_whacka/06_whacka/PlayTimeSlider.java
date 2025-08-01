import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayTimeSlider extends JPanel {

    /** Width of the panel for ColorChangeSpeedSlider */
	private final int W_PANEL = 500;
	/** Height of the panel for the slider */
	private final int H_PANEL = 200;

	/** Initializing the playClock for the slider to interact with */
	GameClockControl playClock;
	
	/** Reflects the current value on the slider */
	Integer sliderValue = 300;
	
	/** Slider to control the speed that the colors change */
	JSlider slider;
	
	/** Display of the value on the slider */
	JLabel valueLabel = new JLabel(sliderValue.toString());

	/**
	* Constructor for the slider to control how fast the colors change.
	* @param playClock
	*/
	public PlayTimeSlider(GameClockControl playClock) {
	
		// components are placed within the window
		setLayout(null);
		
		this.playClock = playClock; 
		
		// Create all slider elements to control color changing speed (300ms to 1500ms)
		slider = new JSlider(JSlider.HORIZONTAL,10,60,10);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setFont(new Font("Verdana", Font.PLAIN, 10));

	}
	
	/**
	* Configures the graphics components and places in the panel.
	* @param column (location) within the panel.
	* @param row (location) within the panel.
	*/	
	public void setInFrame(int column, int row) {
	
		// location of this panel within the color controller panel
        setBounds(column, row, W_PANEL, H_PANEL);

		// Relative to this panel column and row
		slider.setBounds(10,10,300,50);
		
		// paired action for when slider is moved
        slider.addChangeListener(sliderListener);
        
        // Add component to current panel
        add(slider);
        
        // display of slider/variable value
        valueLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        valueLabel.setBounds(320, 80, 50, 50);
        add(valueLabel);
        
	} // end setInFrame
	
	/** Action associated with a change in the slider value */
	// this is both defined and instantiated in a single statement
	ChangeListener sliderListener = new ChangeListener() {
	
		@Override
		public void stateChanged(ChangeEvent ce) {

			sliderValue = slider.getValue();
			valueLabel.setText(sliderValue.toString());
			System.out.println("Hello");
			// communicate to play clock
			playClock.playTimeSeconds(sliderValue);
		}
	}; // end sliderListener
	
	/*public int sliderValue() {
		return sliderValue;
	}*/
}