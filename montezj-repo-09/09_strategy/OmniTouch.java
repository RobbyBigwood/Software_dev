public class OmniTouch extends Robot {
    /** Constructor */
    public OmniTouch(String name) {
        super(name);
        moveBehavior = new MoveOmni();
        sensingBehavior = new SenseTouch();
    }


}
