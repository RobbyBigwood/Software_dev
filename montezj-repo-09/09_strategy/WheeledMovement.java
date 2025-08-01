public class WheeledMovement implements MovementStrategy {
    @Override
    public void move(int heading, int distance) {
        System.out.printf("(Wheeled) Moving %d degrees for %d units smoothly.%n", heading, distance);
    }
}
