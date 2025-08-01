public class MoveWheeled implements MoveBehavior{
    Point delta = new Point();
    public Point move(int heading, int distance) {
        System.out.printf("(Omni) Heading %d degrees for %d units.%n",heading, distance);

        // Calculate the new location based on the requested movement
        double radians = heading * Math.PI/180.0;
        delta.x = delta.x + (int)(distance * Math.cos(radians));
        delta.y = delta.y + (int)(distance * Math.sin(radians));

        return delta;
    }
}
