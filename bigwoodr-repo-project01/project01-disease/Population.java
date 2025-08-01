/*
 * Population 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Models a collection of circles roaming about impacting other circles.
 * @author Amy Larson (with Erik Steinmetz)
 */
public class Population {

	/** Population consists of collection of Person(s) */
    private ArrayList<Person> persons = new ArrayList<>();
    
    /** Size of population of all status */
    private int populationSize = 500;

    /** Pauses simulation then people do not move */
    private boolean paused = false;
    
    /** Count of population in each status (in enum Status) */
    HashMap<Status,Integer> statusCounts = new HashMap<>();
    
    Random random = new Random();

    /** Default constructor. */
    public Population() {
    
    	/** Start with 0 persons with each health status */
		for (Status s : Status.values()) {
			System.out.println(s);
			statusCounts.put(s,0);
		}
    }
    
    /** Create the population
    * @param panel Place all in this JPanel
    */
    public void populate(PopulationPanel panel) {
        
        Random rand = new Random();
        int movementLevel = MessageBoard.getMovement();
        double[] probabilities = {0.75, 0.50, 0.25, 0.10};
        double probabilityNoMove = (movementLevel >= 1 && movementLevel <= 4) ? probabilities[movementLevel - 1] : 0.50;

        /*double probabilityNoMove = switch (movementLevel){
        case 1 -> 0.75;
        case 2 -> 0.50;
        case 3 -> 0.25;
        case 4 -> 0.10;
        default -> 0.50;
        };*/

        Person.setDuration(MessageBoard.duration());


		// populate each of the four areas equally
    	for (int area=0; area<4; area++) {
    		// make all the new persons
			for (int i=0; i<populationSize/4; i++) {
				// Boundaries define the area in which they can move
				Person person = new Person(Layout.BOUNDARIES[area],this);
				int prevention = MessageBoard.preventionlevel();


				// prevention level
				if (prevention == 0) {
					person.status(Status.HEALTHY_NOT_VACCINATED);
				} else {
					double probability = 0.0;
					switch (prevention) {
						case 1:
							probability = 0.3;
							break;
						case 2:
							probability = 0.6;
							break;
						case 3:
							probability = 0.8;
							break;
						default:
							probability = 0.0;
							break;
					}
					if (random.nextDouble() < probability) {
						person.status(Status.HEALTHY_VACCINATED);
					} else {
						person.status(Status.HEALTHY_NOT_VACCINATED);
					}
				}
                // Insert probability logic HERE
                if (rand.nextDouble() < probabilityNoMove) {
                    person.deltaX(0);
                    person.deltaY(0);
                } else {
                    person.deltaX(rand.nextInt(3) - 1); // -1, 0, or 1
                    person.deltaY(rand.nextInt(3) - 1); // -1, 0, or 1
                }

				// add to the list (data structure)
				persons.add(person);
				// add to the Graphics panel
				panel.add(person);
				// adjust the count in the HashMap
				statusCounts.put(person.status(), 
					statusCounts.get(person.status()) + 1);
			}
		} // end for area
		this.shareStats();
		//random person
		int ran = random.nextInt(persons.size());
		//copy them and get their original status
		Person changed = persons.get(ran);
		Status c = changed.status();
		//change their status, put them into the array and then update the stats
		changed.status(Status.SYMPTOMATIC);
		persons.set(ran,changed);
		this.statusChanged(c, Status.SYMPTOMATIC);
    } // end populate()

	/** Execute a time step so that everyone moves accordingly */
	public void update() {
        Random rand = new Random();
        int movementLevel = MessageBoard.movement();
        //Determine how many people to move based on movementLevel
        int[] movementCounts={4, 8, 12, 20};
        int numToMove = (movementLevel >= 1 && movementLevel <= 4) ? movementCounts[movementLevel - 1] : 4;

        Person.setDays(MessageBoard.days());

		// update preventionLevel 
		Person.setPreventionLevel(MessageBoard.preventionlevel());

		//Transmission probability
		Person.setTransmissionProbability(MessageBoard.transmissionProbability());
	
        //Move set number of people to a different area
        for(int i = 0; i < movementCounts[movementLevel - 1]; i++) {

            if (persons.isEmpty()) break; //prevent errors
            
            //Select a random person
            int index = rand.nextInt(persons.size());
            //Person person = persons.get(index);

            // Select a new random area that is different from their current one
            int newArea;
            do{
                newArea = rand.nextInt(4); //There are 4 distinct areas areas                
            }while(Layout.BOUNDARIES[newArea] == persons.get(index).getBorders());

            //Move the person b updating their borders
            persons.get(index).setBorders(Layout.BOUNDARIES[newArea]);
        }
		for (Person person : persons) {
			person.move();
		}
		// Determine if anyone has crossed paths with another
		// potentially changing their status from healthy to asymptomatic
		checkForEncounters();
		this.shareStats();
	}
	
	/** Determine if any of the persons are crossing paths. */
	public void checkForEncounters() {
		for (Person person : persons) {
			// determine if person might change status of other
			// this can occur only if person is contagious and other is healthy
			if ( Status.HEALTHY_VACCINATED==person.status() || 
				 Status.HEALTHY_NOT_VACCINATED==person.status() ||
				 Status.HEALTHY_RECOVERED==person.status()) {
				continue;
			}
			for (Person other: persons) {
				// do not compare anyone to themselves
				if (person.equals(other)) {
					continue;
				}
				// if already infected, status cannot change
				if (Status.SYMPTOMATIC==other.status() ||
					Status.ASYMPTOMATIC==other.status()) {
					continue;
				}
				// calculate the distance to the center of each person.
				// if closer than 2*radius, they are overlapping
				// use Pythagoreans theorem
				int deltaX = person.getCenterX() - other.getCenterX();
				int deltaY = person.getCenterY() - other.getCenterY();
				double distance = Math.pow((deltaX*deltaX + deltaY*deltaY),0.5);
				if (distance < Person.SIZE()) {
					other.exposed(person);
				}
			}
		}
	
	} // end checkforEncounters
	
	/** 
     * 
     * @param remove The old status of the person.
     * @param add The new status of the person.
     */
    public void statusChanged(Status remove, Status add) {
        // Decrease count for the old status
        statusCounts.put(remove, statusCounts.get(remove) - 1);

        // Increase count for the new status
        statusCounts.put(add, statusCounts.get(add) + 1);

        // the MessageBoard with new stats
        MessageBoard.update(Message.STATS, new HashMap<>(statusCounts));
    }

	/*
	*	A method that shares a copy of the total population's stats
	 */
	public void shareStats() {
		HashMap<Status, Integer> copyStatusCount = (HashMap<Status, Integer>) statusCounts.clone();
		MessageBoard.update(Message.STATS, copyStatusCount);
	}

	

    /** Pause the simulation - people no longer move. */
    public void pause() {
        paused = true;
    }

    /** Continue the simulation */
    public void play() {
        System.out.println("Playing now");
        paused = false;
    }

    public ArrayList<Person> getPeople() {
        return persons;
    }


}

