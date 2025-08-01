import javax.swing.*;

public class DisplayTask extends JComponent implements  Runnable {
    private int width;
    private int height;
    int sleep = 1000;

    String userText = "";

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(sleep);
            } catch(Exception e) {

            }
            toDraw = "running" + counter;
        }
    }
}

