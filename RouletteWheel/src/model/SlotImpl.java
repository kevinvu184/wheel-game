/**
 * 
 */
package model;

import model.enumeration.Color;
import model.interfaces.Slot;

/**
 * @author Khoa Vu Duy Anh
 *
 */
public class SlotImpl implements Slot {
	private int position = 0;
	private Color color = null;
	private int number = 0;

	public SlotImpl(int position, Color color, int number) {
		this.position = position;
		this.color = color;
		this.number = number;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean equals(Slot slot) {
		return slot.getColor().equals(this.color) && slot.getNumber() == this.number;
	}

	@Override
	public String toString() {
		return String.format("Position: %d, Color: %s, Number: %d", position, color.toString(), number);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
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
		if (!(obj instanceof SlotImpl)) {
			return false;
		}
		SlotImpl other = (SlotImpl) obj;
		if (color != other.color) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		return true;
	}
}
