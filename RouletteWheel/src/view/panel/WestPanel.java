package view.panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.PlaceBetListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {
	private GameEngine model;

	private JTextArea summary = new JTextArea();

	private JComboBox<String> playerComboBox = new JComboBox<String>(new String[] { "No Player" });
	private JTextField betAmountTextField = new JTextField(10);
	private JRadioButton redButton = new JRadioButton("RED");
	private JRadioButton blackButton = new JRadioButton("BLACK");
	private JRadioButton zerosButton = new JRadioButton("ZEROS");

	private ButtonGroup group = new ButtonGroup();
	private JButton placeBetButton = new JButton("PLACE");

	public WestPanel(MainFrame mf) {
		model = mf.getModel();
		setLayout(new BorderLayout());
		redButton.setSelected(true);

		// CENTER Container
		summary.setEditable(false);
		summary.setLineWrap(true);
		summary.setFont(new Font("Consolas", Font.PLAIN, 12));

		JPanel summaryPanel = new JPanel();
		summaryPanel.setLayout(new BorderLayout());
		summaryPanel.setBorder(new CompoundBorder(new EtchedBorder(), new TitledBorder("Summary")));

		JScrollPane summaryPane = new JScrollPane(summary, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		summaryPanel.add(summaryPane, BorderLayout.CENTER);

		// NORTH Container
		group.add(blackButton);
		group.add(redButton);
		group.add(zerosButton);

		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.add(new JLabel("Player ID "));
		comboBoxPanel.add(playerComboBox);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

		JPanel firstLine = new JPanel();
		firstLine.add(new JLabel("Bet Amount"));
		firstLine.add(betAmountTextField);
		bodyPanel.add(firstLine);

		JPanel secondLine = new JPanel();
		secondLine.add(new JLabel("Bet Type"));
		secondLine.add(redButton);
		secondLine.add(blackButton);
		secondLine.add(zerosButton);
		bodyPanel.add(secondLine);

		JPanel buttonPanel = new JPanel();
		placeBetButton.addActionListener(new PlaceBetListener(mf));
		buttonPanel.add(placeBetButton);

		JPanel placeBetPanel = new JPanel();
		placeBetPanel.setLayout(new BorderLayout());
		placeBetPanel.setBorder(new CompoundBorder(new EtchedBorder(), new TitledBorder("Place Bet")));

		placeBetPanel.add(comboBoxPanel, BorderLayout.NORTH);
		placeBetPanel.add(bodyPanel, BorderLayout.CENTER);
		placeBetPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add 2 container to the WestPanel
		add(placeBetPanel, BorderLayout.NORTH);
		add(summaryPanel, BorderLayout.CENTER);

	}

	public JComboBox<String> getPlayerComboBox() {
		return playerComboBox;
	}

	public JTextField getBetAmountTextField() {
		return betAmountTextField;
	}

	public JRadioButton getRedButton() {
		return redButton;
	}

	public JRadioButton getBlackButton() {
		return blackButton;
	}

	public JRadioButton getZerosButton() {
		return zerosButton;
	}

	public void updateAddComboBox(String id) {
		playerComboBox.addItem(model.getPlayer(id).getPlayerId());
	}

	public void updateRemoveComboBox(String id) {
		playerComboBox.removeItem(model.getPlayer(id).getPlayerId());
	}

	public void updatePlayerSummary(boolean ignore, GameEngine oldModel) {
		String PlayerDetail = "";
		for (Player player : model.getAllPlayers()) {
			PlayerDetail += String.format("%s%s\n", "Player ID: ", player.getPlayerId())
					+ String.format("%s%s\n", "Player Name: ", player.getPlayerName())
					+ String.format("%s%s\n", "Point: ", player.getPoints())
					+ String.format("%s%s\n", "Bet: ", player.getBet())
					+ String.format("%s%s\n", "Bet Type: ", player.getBetType())
					+ ((ignore) ? String.format("%s%s\n", "Recent W/L: ", status(player, oldModel))
							: String.format("%s%s\n", "Recent W/L: ", "NULL"))
					+ "------------------------------------\n";
		}
		summary.setText(PlayerDetail);
	}

	private String status(Player player, GameEngine oldModel) {
		int lastPoint = oldModel.getPlayer(player.getPlayerId()).getPoints();
		if (player.getPoints() > lastPoint) {
			return "WIN";
		} else if (player.getPoints() < lastPoint) {
			return "LOSS";
		} else {
			return "NOT PLAYED";
		}
	}
}
