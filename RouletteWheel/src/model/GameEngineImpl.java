/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * @author Khoa Vu Duy Anh
 *
 */
public class GameEngineImpl implements GameEngine {
	private List<Player> playerCollection = new LinkedList<Player>();
	private List<Slot> slotCollection = new ArrayList<>(Arrays.asList(slot));
	private List<GameEngineCallback> gameEngineCallBackCollection = new ArrayList<>();
	private static Slot[] slot = new Slot[38];

	static {
		slot[0] = new SlotImpl(0, Color.GREEN00, 0);
		slot[1] = new SlotImpl(1, Color.RED, 27);
		slot[2] = new SlotImpl(2, Color.BLACK, 10);
		slot[3] = new SlotImpl(3, Color.RED, 25);
		slot[4] = new SlotImpl(4, Color.BLACK, 29);
		slot[5] = new SlotImpl(5, Color.RED, 12);
		slot[6] = new SlotImpl(6, Color.BLACK, 8);
		slot[7] = new SlotImpl(7, Color.RED, 19);
		slot[8] = new SlotImpl(8, Color.BLACK, 31);
		slot[9] = new SlotImpl(9, Color.RED, 18);
		slot[10] = new SlotImpl(10, Color.BLACK, 6);
		slot[11] = new SlotImpl(11, Color.RED, 21);
		slot[12] = new SlotImpl(12, Color.BLACK, 33);
		slot[13] = new SlotImpl(13, Color.RED, 16);
		slot[14] = new SlotImpl(14, Color.BLACK, 4);
		slot[15] = new SlotImpl(15, Color.RED, 23);
		slot[16] = new SlotImpl(16, Color.BLACK, 35);
		slot[17] = new SlotImpl(17, Color.RED, 14);
		slot[18] = new SlotImpl(18, Color.BLACK, 2);
		slot[19] = new SlotImpl(19, Color.GREEN0, 0);
		slot[20] = new SlotImpl(20, Color.BLACK, 28);
		slot[21] = new SlotImpl(21, Color.RED, 9);
		slot[22] = new SlotImpl(22, Color.BLACK, 26);
		slot[23] = new SlotImpl(23, Color.RED, 30);
		slot[24] = new SlotImpl(24, Color.BLACK, 11);
		slot[25] = new SlotImpl(25, Color.RED, 7);
		slot[26] = new SlotImpl(26, Color.BLACK, 20);
		slot[27] = new SlotImpl(27, Color.RED, 32);
		slot[28] = new SlotImpl(28, Color.BLACK, 17);
		slot[29] = new SlotImpl(29, Color.RED, 5);
		slot[30] = new SlotImpl(30, Color.BLACK, 22);
		slot[31] = new SlotImpl(31, Color.RED, 34);
		slot[32] = new SlotImpl(32, Color.BLACK, 15);
		slot[33] = new SlotImpl(33, Color.RED, 3);
		slot[34] = new SlotImpl(34, Color.BLACK, 24);
		slot[35] = new SlotImpl(35, Color.RED, 36);
		slot[36] = new SlotImpl(36, Color.BLACK, 13);
		slot[37] = new SlotImpl(37, Color.RED, 1);
	}

	public GameEngineImpl(GameEngine ge) {
		for (Player player : ge.getAllPlayers()) {
			this.playerCollection.add(new SimplePlayer(player));
		}
	}

	public GameEngineImpl() {
	}

	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		int randomNumberFrom0To37 = (int) (Math.random() * slotCollection.size());
		Slot current = slotCollection.get(randomNumberFrom0To37);
		Slot next = null;
		Iterator<Slot> ite = slotCollection.iterator();
		currentSlot(current, ite);
		for (; initialDelay < finalDelay; initialDelay += delayIncrement) {
			try {
				Thread.sleep(initialDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (GameEngineCallback gecb : gameEngineCallBackCollection) {
				gecb.nextSlot(current, this);
			}
			ite = checkLast(current, ite);
			next = ite.next();
			current = next;
		}
		for (GameEngineCallback gameEngineCallbackLoop : gameEngineCallBackCollection) {
			gameEngineCallbackLoop.result(current, this);
		}
	}

	private void currentSlot(Slot slot, Iterator<Slot> ite) {
		while (ite.hasNext()) {
			if (slot.equals(ite.next())) {
				return;
			}
		}
	}

	private Iterator<Slot> checkLast(Slot slot, Iterator<Slot> ite) {
		if (slot.equals(new SlotImpl(37, Color.RED, 1))) {
			ite = slotCollection.iterator();
		}
		return ite;
	}

	@Override
	public void calculateResult(Slot winningSlot) {
		for (Player player : playerCollection) {
			player.getBetType().applyWinLoss(player, winningSlot);
		}
	}

	@Override
	public void addPlayer(Player player) {
		for (Player temp : playerCollection) {
			if (temp.equals(player)) {
				playerCollection.remove(temp);
				playerCollection.add(player);
				return;
			}
		}
		playerCollection.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		for (Player player : playerCollection) {
			if (player.getPlayerId().equals(id))
				return player;
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		for (Player temp : playerCollection) {
			if (temp.equals(player)) {
				playerCollection.remove(temp);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallBackCollection.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		for (GameEngineCallback temp : gameEngineCallBackCollection) {
			if (temp.equals(gameEngineCallback)) {
				gameEngineCallBackCollection.remove(temp);
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(playerCollection);
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		for (Player temp : playerCollection) {
			if (temp.getPlayerId().equals(player.getPlayerId())) {
				if (temp.setBet(bet)) {
					temp.setBetType(betType);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Collection<Slot> getWheelSlots() {
		return slotCollection;
	}
}
