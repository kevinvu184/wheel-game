/**
 * 
 */
package model;

import model.enumeration.BetType;
import model.interfaces.Player;

/**
 * @author Khoa Vu Duy Anh
 *
 */
public class SimplePlayer implements Player {
	private String playerId = null;
	private String playerName = null;
	private int point = 0;
	private int bet = 0;
	private BetType betType = null;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.point = initialPoints;
	}

	public SimplePlayer(Player player) {
		this.playerId = player.getPlayerId();
		this.point = player.getPoints();
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return this.point;
	}

	@Override
	public void setPoints(int points) {
		this.point = points;
	}

	@Override
	public String getPlayerId() {
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) {
		if (bet > 0 && this.point >= bet) {
			this.bet = bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	@Override
	public BetType getBetType() {
		return this.betType;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, betType=%s, points=%d", playerId, playerName, bet,
				betType.toString(), point);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SimplePlayer)) {
			return false;
		}
		SimplePlayer other = (SimplePlayer) obj;
		if (playerId == null) {
			if (other.playerId != null) {
				return false;
			}
		} else if (!playerId.equals(other.playerId)) {
			return false;
		}
		return true;
	}
}
