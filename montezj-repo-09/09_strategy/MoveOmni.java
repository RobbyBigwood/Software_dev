public class MoveOmni implements MoveBehavior{

    Point delta = new Point();
    public Point move(int heading, int distance) {
        System.out.printf("(Omni) Heading %d degrees for %d units.%n",heading, distance);

        // Calculate the new location based on the requested movement
        double radians = heading * Math.PI/180.0;
        delta.x = (int)(distance * Math.cos(radians));
        delta.y = (int)(distance * Math.sin(radians));
        System.out.println("Moved to "+delta);
        return delta;
    }
}
