import java.awt.*;

public class WheelCameraBad extends RobotBad{
    @Override
    public void sense() {
        // Randomly generate a reading of an obstacle in the environment
        // It can be as close as 1 or beyond the max (thus not visible)
        int distance = rand.nextInt(300*2) + 1;
        if (distance > 300) {
            System.out.println("(Camera) No obstacles ahead");
        } else {
            System.out.printf("(Camera) Obstacle %d units ahead.%n",distance);
        }
    }

    @Override
    public void move(int heading, int distance) {
        System.out.printf("(Omni) Heading %d degrees for %d units.%n",heading, distance);

        // Calculate the new location based on the requested movement
        double radians = heading * Math.PI/180.0;
        super.getLocation().x = super.getLocation().x + (int)(distance * Math.cos(radians));
        super.getLocation().y = super.getLocation().y + (int)(distance * Math.sin(radians));
        System.out.println("Moved to "+super.getLocation());

    }
}
