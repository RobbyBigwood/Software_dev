public class Reconfigurable extends Robot{
    private MoveBehavior moveBehavior;
    private SenseBehavior senseBehavior;
    private String name;

    public Reconfigurable(String name, SenseBehavior sense, MoveBehavior move) {
        moveBehavior = move;
        this.name = name;
        senseBehavior = sense;
    }

    public void sense() {
        senseBehavior.sense();
    }
    public void move(int heading, int distance) {
        Point point = moveBehavior.move(heading, distance);
        location.x += point.x;
        location.y += point.y;
        System.out.println("Moved to "+location);
    }

    public void describe() {
        System.out.println("I am "+this.name+" located at "+location);
    }
    public void setSensingBehavior(SenseBehavior behavior) {
        this.sensingBehavior = behavior;
    }
    public void setMoveBehavior(MoveBehavior behavior) {
        this.moveBehavior = behavior;
    }
}
