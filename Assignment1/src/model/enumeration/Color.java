package model.enumeration;

/**
 * <pre>
 *  Provided enum type for Further Programming representing the color of a Slot on the Gaming Wheel
 * <b>NOTE:</b> GREEN0 and GREEN00 are provided as dfferent colors to differentiate the 0 and 00 which
 * are both numeric zero in our implementation
 * </pre>
 * 
 * @author Caspar Ryan
 */
public enum Color {
	RED {
		public String toString() {
			return "Red";
		}
	},

	BLACK {
		public String toString() {
			return "Black";
		}
	},

	GREEN0 {
		public String toString() {
			return "Green0";
		}
	},

	GREEN00 {
		public String toString() {
			return "Green00";
		}
	};
}