package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.dialog.AddPlayerDialog;
import view.panel.StatusBar;
import view.panel.WestPanel;

public class AddPlayerListener implements ActionListener {
	private final BetType DEFAULT_BET_TYPE = BetType.RED;

	private GameEngine model;
	private AddPlayerDialog apd;
	private WestPanel wp;
	private StatusBar stp;

	public AddPlayerListener(MainFrame mf, AddPlayerDialog apd) {
		this.apd = apd;
		model = mf.getModel();
		wp = mf.getWestPanel();
		stp = mf.getStatusBar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = apd.getPlayerNamePromptField().getTextField().getText();
			int playerId = Integer.parseInt(apd.getPlayerIdPromtField().getTextField().getText());
			int initialMoney = Integer.parseInt(apd.getInitialMoneyPromtField().getTextField().getText());

			if (playerId <= 0) {
				JOptionPane.showMessageDialog(null, "Player ID is a positve integer.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			} else if (initialMoney <= 0) {
				JOptionPane.showMessageDialog(null, "Initial money is a positive integer", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (hasPlayer(playerId)) {
					wp.updateRemoveComboBox(Integer.toString(playerId));
				}

				Player newPlayer = new SimplePlayer(Integer.toString(playerId), playerName, initialMoney);
				model.addPlayer(newPlayer);
				model.getPlayer(Integer.toString(playerId)).setBetType(DEFAULT_BET_TYPE);

				wp.updatePlayerSummary(false, model);
				wp.updateAddComboBox(Integer.toString(playerId));
				stp.updateNumOfPlayer(model.getAllPlayers().size());
				stp.updateNumOfUnplacedPlayer(numOfUnplacedPlayer());

				apd.dispose();
			}
		} catch (NumberFormatException er) {
			JOptionPane.showMessageDialog(null, "Please check your input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}

	private int numOfUnplacedPlayer() {
		int count = 0;
		for (Player player : model.getAllPlayers()) {
			if (player.getBet() == 0) {
				count++;
			}
		}
		return count;
	}

	public boolean hasPlayer(int playerId) {
		for (Player player : model.getAllPlayers()) {
			if (player.getPlayerId().equals(Integer.toString(playerId))) {
				return true;
			}
		}
		return false;
	}
}
