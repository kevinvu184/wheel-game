package view;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;
import view.panel.StatusBar;
import view.panel.WestPanel;
import view.panel.WheelPanel;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private GameEngine oldModel;
	private WheelPanel wheel;
	private StatusBar stp;
	private WestPanel wp;

	public GameEngineCallbackGUI(MainFrame mf) {
		wheel = mf.getWheelPanel();
		stp = mf.getStatusBar();
		wp = mf.getWestPanel();
		oldModel = new GameEngineImpl(mf.getModel());
	}

	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				wheel.repaintWheel(slot.getPosition());
			}
		});
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Player player : engine.getAllPlayers()) {
					player.resetBet();
				}
				wheel.repaintWheel(winningSlot.getPosition());
				wp.updatePlayerSummary(true, oldModel);
				stp.updateWheelStatus(false);
				stp.updateNumOfUnplacedPlayer(numOfUnplacedPlayer(engine));
			}
		});
	}

	private int numOfUnplacedPlayer(GameEngine engine) {
		int count = 0;
		for (Player player : engine.getAllPlayers()) {
			if (player.getBet() == 0) {
				count++;
			}
		}
		return count;
	}
}
