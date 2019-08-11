package view.panel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	private JLabel numOfPlayerLabel = new JLabel("Number Of Player: 0");
	private JLabel numOfUnplacedPlayerLabel = new JLabel("Number Of Unplaced Bet Player: 0");
	private JLabel wheelStatusLabel = new JLabel("Wheel Status: Idle");

	public StatusBar() {
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new GridLayout(1, 0));

		add(numOfPlayerLabel);
		add(numOfUnplacedPlayerLabel);
		add(wheelStatusLabel);
	}
	
	public void updateNumOfPlayer(int numOfPlayer) {
		numOfPlayerLabel.setText("Number Of Player: " + numOfPlayer);
	}

	public void updateNumOfUnplacedPlayer(int numOfUnplacedPlayer) {
		numOfUnplacedPlayerLabel.setText("Number Of Unplaced Bet Player: " + numOfUnplacedPlayer);
	}
	
	public void updateWheelStatus(boolean spinStatus) {
		wheelStatusLabel.setText("Wheel Status: " + ((spinStatus == true) ? "Spining..." : "Idle"));
	}
}
