import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.*;
import java.util.*;


public class StatsPanel extends JPanel {

	JLabel heading;

	JLabel currentlyInfected;
	JLabel days;
	JLabel percentNotAlive;
	JLabel percentRecovered;

	Double infected = (MessageBoard.stats().get(Status.SYMPTOMATIC) + MessageBoard.stats().get(Status.ASYMPTOMATIC))/5.0;
	Double recovered = MessageBoard.stats().get(Status.HEALTHY_RECOVERED)/5.0;
	Double dead = MessageBoard.stats().get(Status.NOT_ALIVE)/5.0;

	public StatsPanel() {
		setLayout(null);
		setBounds( //0,50,100,100);
			Layout.STATS_PANEL_X, Layout.STATS_PANEL_Y,
			Layout.STATS_PANEL_W, Layout.STATS_PANEL_H
		);
		setBorder(BorderFactory.createLineBorder(Color.black));

		heading = new JLabel("Heading");
		heading.setText("Health Statistics of Population");
		heading.setBounds(Layout.STATS_PANEL_W/2-100,0,300,50);
		add(heading);

		currentlyInfected = new JLabel("Percent Infected");
		currentlyInfected.setText("Percent Infected: ");
		currentlyInfected.setBounds(Layout.STATS_LABEL_X,30,200,50);
		add(currentlyInfected);

		percentRecovered = new JLabel("Percent Recovered");
		percentRecovered.setText("Percent Recovered: ");
		percentRecovered.setBounds(Layout.STATS_LABEL_X,80,200,50);
		add(percentRecovered);

		percentNotAlive = new JLabel("Percent Not Alive");
		percentNotAlive.setText("Percent Not Alive: ");
		percentNotAlive.setBounds(Layout.STATS_LABEL_X,130,200,50);
		add(percentNotAlive);

		days = new JLabel("Days");
		days.setText("Days: " );
		days.setBounds(Layout.STATS_LABEL_X,180,200,50);
		add(days);
		
		setVisible(true);
	}

	public void update() {
		recovered = MessageBoard.stats().get(Status.HEALTHY_RECOVERED)/5.0;
		infected = (MessageBoard.stats().get(Status.SYMPTOMATIC) + MessageBoard.stats().get(Status.ASYMPTOMATIC))/5.0;
		dead = MessageBoard.stats().get(Status.NOT_ALIVE)/5.0;
		currentlyInfected.setText("Percent Infected: "+infected.toString()+"%");
		percentRecovered.setText("Percent Recovered: "+recovered.toString()+"%");
		percentNotAlive.setText("Percent Not Alive: "+dead.toString()+"%");
		days.setText("Days: " +MessageBoard.days());
	}
}