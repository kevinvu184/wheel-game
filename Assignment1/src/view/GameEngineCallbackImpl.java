package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

	public GameEngineCallbackImpl() {
		logger.setLevel(Level.FINE);
	}

	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		logger.log(Level.INFO, String.format("Next slot: %s", slot.toString()), engine);
	}

	@Override
	public void result(Slot result, GameEngine engine) {
		logger.log(Level.INFO, String.format("RESULT=%s\n", result.toString()));
		logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");
		engine.calculateResult(result);
		logger.log(Level.INFO, String.format("\n%s", logging(engine)));
	}

	private String logging(GameEngine engine) {
		String summary = "";
		for (Player player : engine.getAllPlayers()) {
			summary += player.toString() + "\n";
		}
		return summary;
	}
}
