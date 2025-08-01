public class Main {
    private SenseBehavior sense = new SenseIR();
  public static void main(String[] args) {
      System.out.println("^^^^^^^^  ROBOTS  ^^^^^^^");

    MoveFixed moveFixed = new MoveFixed();

    RobotBad rosie = new RobotBad("Rosie");
    OmniCameraBad eva = new OmniCameraBad("Eva");
    RobotBad hoover = new RobotBad("Hoover");
    RobotBad walle = new RobotBad("Wall-E");
    OmniTouch roboto = new OmniTouch("Mr. Roboto");
    WheelCamera wheely = new WheelCamera("Mr. Wheely");
    Reconfigurable reconfigurable = new Reconfigurable("Reconfig",new SenseIR(), new MoveWheeled());
    // OmniTouchBad roomba = new OmniTouchBad("Hoover");

    // WheelCameraBad walle  = new WheelCameraBad("Wall-E");

    RobotBad[] robots = {rosie, eva, hoover, walle};
    for (RobotBad robot : robots) {
      System.out.println("----------------------------");
      robot.describe();
      robot.move(45,10);
      robot.sense();
    }

    Robot[] robots1 = {roboto, wheely, reconfigurable};
    for (Robot robot : robots1) {
      System.out.println("----------------------------");
      robot.describe();
      robot.move(45,10);
      robot.sense();
    }
  }
}

