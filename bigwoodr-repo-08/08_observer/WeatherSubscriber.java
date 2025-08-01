import javax.swing.*;
import java.util.*;
import java.awt.*;


public class WeatherSubscriber extends JPanel implements Observer {

    private boolean subscribed = false;


	private final int W_PANEL = 300;
	private final int H_PANEL = 200;
	
	private JLabel title = new JLabel("Weather Info");

    private JLabel temperatureLabel = new JLabel("Temperature: ");
    private JLabel temperatureValue = new JLabel(" - ");

    private JLabel precipitationLabel = new JLabel("Precipitation: ");
    private JLabel precipitationValue = new JLabel(" - ");

    private JLabel qualityLabel = new JLabel("Air Quality: ");
    private JLabel qualityValue = new JLabel(" - ");


    WeatherFeed feed = null;

    WeatherData theWeather;

    public WeatherSubscriber(WeatherFeed feed, int row, int col) {
    
        this.feed = feed;
        //feed.subscribe(this);
        
        setLayout(null);
        
        setBounds(row,col,W_PANEL,H_PANEL);
        setBorder(BorderFactory.createLineBorder(Color.black));

        title.setBounds(10,0,W_PANEL,20);
        add(title);

        temperatureLabel.setBounds(10,30,150,20);
        temperatureValue.setBounds(155,30,100,20);
        temperatureValue.setHorizontalAlignment(SwingConstants.RIGHT);
        add(temperatureLabel);
        add(temperatureValue);

        precipitationLabel.setBounds(10,60,150,20);
        precipitationValue.setBounds(155,60,100,20);
        precipitationValue.setHorizontalAlignment(SwingConstants.RIGHT);
        add(precipitationLabel);
        add(precipitationValue);

        qualityLabel.setBounds(-70,90,150,20);
        qualityValue.setBounds(245,90,100,20);
        qualityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(qualityLabel);
        add(qualityValue);

        setVisible(true);

    }


    // Adding method subscribe(boolean checked)
	public void subscribe(boolean checked){
		if (checked) {
            feed.subscribe(this);
            subscribed = true;
        
        }
	}


    /* Receiving an update from the subject. */
    public void update(Object data) {

        if (data instanceof WeatherData) {
            theWeather = (WeatherData) data;
            theWeather.weatherGenerator();
            temperatureValue.setText(String.format("%d",theWeather.temperature));
            precipitationValue.setText(String.format("%d",theWeather.precipitation));
            qualityValue.setText(String.format("%d",theWeather.airQuality));

        }


    }

} // end class Newsfeed