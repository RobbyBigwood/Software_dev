import com.sun.nio.file.SensitivityWatchEventModifier;

public class WheelCamera extends Robot{
    public WheelCamera(String name) {
        super(name);
        sensingBehavior = new SenseCamera();
        moveBehavior = new MoveWheeled();
    }
}
