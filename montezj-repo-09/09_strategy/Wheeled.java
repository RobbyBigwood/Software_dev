public class Wheeled extends RobotBad {
    private MovementStrategy movementStrategy;

    public Wheeled(String name, SenseBehavior strategy) {
        super(name);
        this.movementStrategy = new WheeledMovement(); // Uses wheeled movement
    }

    @Override
    public void move(int heading, int distance) {
        movementStrategy.move(heading, distance); // Uses WheeledMovement behavior
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("I am a wheeled robot.");
    }
}
