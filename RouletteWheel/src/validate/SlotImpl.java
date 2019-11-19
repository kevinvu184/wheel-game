// 
// Decompiled by Procyon v0.5.36
// 

package validate;

import java.io.Serializable;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot, Serializable {
	public SlotImpl(final int position, final Color color, final int number) {
	}

	public int getPosition() {
		return 0;
	}

	public Color getColor() {
		return null;
	}

	public int getNumber() {
		return 0;
	}

	public boolean equals(final Slot slot) {
		return false;
	}

	@Override
	public boolean equals(final Object obj) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}
}
