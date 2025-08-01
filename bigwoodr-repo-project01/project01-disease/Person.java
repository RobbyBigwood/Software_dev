import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/** Circle for drawing in a JFrame
 *
 * @author Amy Larson
 */
public class Person extends JPanel {

	private static Integer days = 0;
	// creating static variable morbidityRate and initializing it to 0
	private static int morbidityRate=0;
	private static int duration = 1;
	// creating a static variable incubationPeriod
	private static int incubationPeriod=0;

    private int dayInfected = 0; //Tracks the number of days person is infected
    
	private static Integer preventionLevel = 0; //preventionLevel variable

	public static void setPreventionLevel(Integer prevention) { //Setter for preventionLevel
        preventionLevel = prevention;
    }

	//creating a setter for incubation period
	public static void setIncubationPeriod(int incubationPeriod) {
		Person.incubationPeriod = incubationPeriod; // need to use Person here due to the static variable 
	}

	// creating a setter for morbidity
	public static void setMorbidity(int morbidity) {
		Person.morbidityRate = morbidity; // need to use Person here due to the static variable 
	}

	/** diameter of the circle */
	private static final int SIZE = 5;

	//Static variable for the probability
	private static int transmissionProbability = 10;
	
	/** Population of which this person is part */
	private Population population;
	
	private static Random random = new Random();
	
	/** Box inside of which this person can move */
	private BoundingBox borders;
	
	/** Health status of the person. From enum Status */
	private Status status = Status.HEALTHY_VACCINATED;
	
	/** Position (column) relative to the origin of the population panel */
	private int locationX = 0;
	
	/** Position (row) relative to the origin of the population panel */
	private int locationY = 0;
	
	/** Movement along the x-axis at each time step */
	private int deltaX = 0;
	
	/** Movement along the y-axis at each time step */
	private int deltaY = 0;

	private void populationStatusChanged(Status remove, Status add) {
        // Implement the logic to handle population status change
    }

	/** Default constructor */
    public Person(BoundingBox borders, Population pop) {

        setLayout(null);
        
        this.borders = borders;
        this.population = pop;
        
        // get a random location from within their border
        locationX = borders.getRandomX();
        locationY = borders.getRandomY();

		// randomly place each person
    	setBounds(locationX, locationY, SIZE, SIZE);
		//System.out.println("created with bounds: " + getBounds()); 
		
		// randomly select rate of movement per time step
		deltaX = random.nextInt(4) - 2;		// range -2 to 2
		deltaY = random.nextInt(4) - 2;
		
		setVisible(true);
    }

    /** Move person based on their deltas along X and Y. */
    public void move() {
		// if days infected is greater than incubation period, then change status to SYMPTOMATIC
		if (status == Status.ASYMPTOMATIC) {
			if (days - dayInfected >= incubationPeriod) {
				status = Status.SYMPTOMATIC;
				// calling populationStatusChanged but the directions don't say to mkae populationStatusChanged and it is not in this class
				population.statusChanged(Status.ASYMPTOMATIC, Status.SYMPTOMATIC);  // calling populationStatusChanged

				 // calling populationStatusChanged
			} 
		}
		// if a person is infected and has reached the end of their illness, change their status to NOT_ALIVE with the morbidityRate probability.
		if (status == Status.SYMPTOMATIC) {
			if (days - dayInfected >= duration) {
				if (random.nextInt(100) < morbidityRate) {
					status = Status.NOT_ALIVE;
					deltaX(0);
					deltaY(0);
					population.statusChanged(Status.SYMPTOMATIC, Status.NOT_ALIVE); // calling populationStatusChanged
				} else if (status == Status.NOT_ALIVE) {
					status = Status.HEALTHY_RECOVERED;
					population.statusChanged(Status.NOT_ALIVE, Status.HEALTHY_RECOVERED);
				}
				else {
					status = Status.HEALTHY_RECOVERED;
		            population.statusChanged(Status.SYMPTOMATIC,Status.HEALTHY_RECOVERED); // calling populationStatusChanged
                    // ^ THIS LINE IS ALSO A PART OF ISSUE #15
				}
			}
		}

		// ERROR: error message in the above code since issue 14-15 are not completed.
    	// change in each direction
    	locationX += deltaX;
    	locationY += deltaY;
    	
    	// determine if up against a border and need to turn around
    	if (locationX < borders.left) {
    		locationX = borders.left + SIZE;
    		deltaX = -deltaX;
    	} else if (locationX > borders.right) {
    		locationX = borders.right - SIZE;
    		deltaX = -deltaX;
    	}
    	// check if y (row) is out of bounds
    	if (locationY < borders.top) {
    		locationY = borders.top + SIZE;
    		deltaY = -deltaY;
    	} else if (locationY > borders.bottom) {
    		locationY = borders.bottom - SIZE;
    		deltaY = -deltaY;
    	}
    	
    	setLocation(locationX,locationY);
    }
    
    /** 
    * This person has been exposed to an infected individual. Determine if infection has been transmitted.
    * @param other Person encountered that might infect this person.
    */
    public void exposed(Person other) {

		//Check if 'other' is contagious
		if (other.status == Status.ASYMPTOMATIC || other.status == Status.SYMPTOMATIC) {
			if (this.status == Status.HEALTHY_VACCINATED) {
				int roll = random.nextInt(100); 
				if (roll < transmissionProbability) {
					//Infect this person: set status to SYMPTOMATIc
					this.status = Status.SYMPTOMATIC;
                    dayInfected = MessageBoard.days();//Record day infected occured
					population.statusChanged(Status.HEALTHY_VACCINATED, Status.SYMPTOMATIC);
				}
			} else if (this.status == Status.HEALTHY_NOT_VACCINATED) {
				int roll = random.nextInt(100);
				if (roll < transmissionProbability) {
					this.status = Status.SYMPTOMATIC;
					dayInfected = MessageBoard.days();//Record day infected occured
					population.statusChanged(Status.HEALTHY_NOT_VACCINATED, Status.SYMPTOMATIC);
				}
			}
		}

    }

    // part of Java Graphics. Called when repaint() is called.
    // this draws the circle inside the Person panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(status.color);
        g.fillOval(0, 0, SIZE, SIZE);
    }

    // ______________________ SETTERS and GETTERS
    
    public void borders(BoundingBox box) {
    	borders = box;
    }

    public static void setDays(Integer days){
        Person.days = days;
    }
    
    public static void setDuration(int duration){
        Person.duration = duration;
    }

	//public void setDays(int days) {this.days = days;}
	//public void setDuration(int duration) {this.duration = duration;}
    public int getCenterX() {
    	return (locationX + SIZE)/2;
    }
    public BoundingBox getBorders(){
        return borders;
    }
    public void setBorders(BoundingBox newBorders){
        this.borders = newBorders;
    }
    public int getCenterY() {
    	return (locationY + SIZE)/2;
    }
    public static int SIZE() {
    	return SIZE;
    }
    
    public Status status() {
    	return status;
    }
    
    public void status(Status s) {
    	status = s;
    }
    
    public void deltaX(int dX) {
    	deltaX = dX;
    }
    
    public void deltaY(int dY) {
    	deltaY = dY;
    }

	public static void setTransmissionProbability(int probability) {
		transmissionProbability = probability;
	}
    

}
