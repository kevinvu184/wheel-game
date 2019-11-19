// 
// Decompiled by Procyon v0.5.36
// 

package validate;

import java.util.Collection;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GEImpl implements GameEngine {
	public void spin(final int initialDelay, final int finalDelay, final int delayIncrement) {
	}

	public void calculateResult(final Slot winningSlot) {
	}

	public void addPlayer(final Player player) {
	}

	public Player getPlayer(final String id) {
		return null;
	}

	public boolean removePlayer(final Player player) {
		return false;
	}

	public void addGameEngineCallback(final GameEngineCallback gameEngineCallback) {
	}

	public boolean removeGameEngineCallback(final GameEngineCallback gameEngineCallback) {
		return false;
	}

	public Collection<Player> getAllPlayers() {
		return null;
	}

	public boolean placeBet(final Player player, final int bet, final BetType betType) {
		return false;
	}

	public Collection<Slot> getWheelSlots() {
		return null;
	}
}
