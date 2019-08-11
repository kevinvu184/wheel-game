package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.dialog.RemovePlayerDialog;
import view.panel.StatusBar;
import view.panel.WestPanel;

public class RemovePlayerListener implements ActionListener {
	private GameEngine model;
	private RemovePlayerDialog rpd;
	private WestPanel wp;
	private StatusBar stp;

	public RemovePlayerListener(MainFrame mf, RemovePlayerDialog rpd) {
		this.rpd = rpd;
		model = mf.getModel();
		wp = mf.getWestPanel();
		stp = mf.getStatusBar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int playerId = Integer.parseInt(rpd.getPlayerIdPromptField().getTextField().getText());

			if (playerId <= 0) {
				JOptionPane.showMessageDialog(null, "Player ID is a positve integer.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			} else if (!hasPlayer(playerId)) {
				JOptionPane.showMessageDialog(null, "Player not found.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			} else {
				wp.updateRemoveComboBox(Integer.toString(playerId));

				model.removePlayer(model.getPlayer(Integer.toString(playerId)));
				
				wp.updatePlayerSummary(false, model);
				stp.updateNumOfPlayer(model.getAllPlayers().size());
				stp.updateNumOfUnplacedPlayer(numOfUnplacedPlayer());
				
				rpd.dispose();
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
