import java.util.Random;

public abstract class Robot {

        Random rand = new Random();

        private String name;

        /** Current location in a fixed grid covering 1000x1000 pixels */
        protected Point location = new Point();

        protected SenseBehavior sensingBehavior;

        protected MoveBehavior moveBehavior;
        public Robot() {
                this("Anonymous");
        }


        public Robot(String name) {
                this.name = name;
                // Place the robot at a random location within the fixed grid
                location.x = rand.nextInt(1000) - 500;
                location.y = rand.nextInt(1000) - 500;
        }

        public void move(int heading, int distance) {
                Point point = moveBehavior.move(heading, distance);
                location.x += point.x;
                location.y += point.y;
                System.out.println("Moved to "+location);
        }

        public void sense() {
                sensingBehavior.sense();
        }
        /** Prints general information */
        public void describe() {
                System.out.println("I am "+this.name+" located at "+location);
        }

        //______________________________________________________________
        // All below are getters and setters for class member variables

        public void setSensingBehavior(SenseBehavior behavior) {
                this.sensingBehavior = behavior;
        }
        public void setMoveBehavior(MoveBehavior behavior) {
                this.moveBehavior = behavior;
        }

        public SenseBehavior getSensingBehavior() {
                return sensingBehavior;
        }
        public MoveBehavior getMoveBehavior() {
                return moveBehavior;
        }

        public void setName(String value) {
                name = value;
        }
        public String getName() {
                return name;
        }

        public void setLocation(Point point) {
                location = point;
        }
        public Point getLocation() {
                return location;
        }
}
