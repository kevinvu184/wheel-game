package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.panel.StatusBar;
import view.panel.ToolBar;
import view.panel.WestPanel;

public class PlaceBetListener implements ActionListener {
	private GameEngine model;
	private WestPanel wp;
	private StatusBar stp;
	private MainFrame mf;
	private ToolBar t;

	public PlaceBetListener(MainFrame mf) {
		this.mf = mf;
		model = mf.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		wp = mf.getWestPanel();
		stp = mf.getStatusBar();
		t = mf.getToolBarPanel();

		try {
			int bet = Integer.parseInt(wp.getBetAmountTextField().getText());
			int playerId = Integer.parseInt(wp.getPlayerComboBox().getSelectedItem().toString());
			String betType = getSelectedButton();

			if (bet <= 0) {
				JOptionPane.showMessageDialog(null, "Bet is a positive amount.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			} else if (!hasPlayer(playerId)) {
				JOptionPane.showMessageDialog(null, "Player not found.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			} else if (model.getPlayer(Integer.toString(playerId)).setBet(bet)) {
				if (betType.equals("BLACK")) {
					model.getPlayer(Integer.toString(playerId)).setBetType(BetType.BLACK);
				} else if (betType.equals("ZEROS")) {
					model.getPlayer(Integer.toString(playerId)).setBetType(BetType.ZEROS);
				} else {
					model.getPlayer(Integer.toString(playerId)).setBetType(BetType.RED);
				}
				wp.updatePlayerSummary(false, model);
				stp.updateNumOfUnplacedPlayer(numOfUnplacedPlayer());
				if (numOfUnplacedPlayer() == 0) {
					t.getSpinButton().doClick();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bet amount is not invalid.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Please check your input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getSelectedButton() {
		if (wp.getZerosButton().isSelected()) {
			return "ZEROS";
		} else if (wp.getBlackButton().isSelected()) {
			return "BLACK";
		} else {
			return "RED";
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
