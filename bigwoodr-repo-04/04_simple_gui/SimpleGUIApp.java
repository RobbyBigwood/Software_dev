import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Primary display for the Game
* Components for the display are created within the JFrame.
* Some of these components are shared with the Game
* @author Amy Larson
*/
public class SimpleGUIApp extends JFrame {

	// Define the size of the graphics window
	final private int W_WIDTH = 1000;
	final private int W_HEIGHT = 800;
	
	// Define the locations of the components within the frame
	final private int leftColumn = 50;
	final private int rightColumn = 600;
	final private int topRow = 100;
	final private int bottomRow = 400;
	
	SliderController slider;
	PlayController playController;
	ButtonsController buttons;
	TextboxController textboxController;

	/**
	* Creates a Simulation GUI application.
	* Sets the components and their positions in the gui.
	*/
	public SimpleGUIApp() {

		// Initialize the graphics window
		super("Simple GUI Components");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(W_WIDTH,W_HEIGHT);
		// You control the layout, not the graphics app
		getContentPane().setLayout(null);
		
		slider = new SliderController(this);
		slider.setInFrame(leftColumn, topRow);
		
		buttons = new ButtonsController(this);
		buttons.setInFrame(rightColumn, topRow);
		
		// Set up play/pause button
  		
  		playController = new PlayController(this);
		playController.setInFrame(rightColumn, bottomRow);


		textboxController = new TextboxController(this);
		textboxController.setInFrame(leftColumn,bottomRow);


		// make it all appear
		setVisible(true);
	}
} // end class SimulationGUI
