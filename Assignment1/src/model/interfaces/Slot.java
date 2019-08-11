package model.interfaces;

import model.enumeration.Color;

/**
 * <pre>
 * Assignment interface for Further Programming representing a colored/numbered Slot on the Gaming Wheel
 * 
 * (setting values is handled by the implementing class constructor(s))
 * 
 * <b>IMPORTANT:</b> To facilitate testing, your implementation of this class should be <b>model.SlotImpl</b>
 * i.e. a class named SlotImpl residing in the model package
 * 
 * it should also provide the following constructor:
 *      <b>public SlotImpl(int position, Color color, int number)</b>
 * </pre>
 * @author Caspar Ryan
 */
public interface Slot
{
   /**
    * the size of the gaming wheel configured according to the assignment specification
    */
   public static final int WHEEL_SIZE = 38;

   /**
    * @return - the position of this slot (clockwise starting from position 0 for Slot GREEN00)
    */
   public int getPosition();

   /**
    * @return - the numeric value of this slot as displayed on the gaming wheel
    */
   public int getNumber();

   /**
    * @return - the color of this slot based on {@link Color}
    */
   public Color getColor();

   /**
   * @return <pre> A human readable String that lists the values of this WheelSlot instance (see OutputTrace.txt) 
   * 
   * <b>NOTE:</b> Must use "proper naming" case i.e. First letter capitalised       
   * e.g. "Position: 0, Color: Green00, Number: 0" for "00" slot at top of wheel</pre>
   */
   @Override
   public abstract String toString();

   /**
    * @param slot - another Slot to compare with
    * @return - true if the color and number fields are equal
    */
   public abstract boolean equals(Slot slot);

   /**
    * <pre> <b>NOTE:</b> this is the java.lang.Object @Override
    * 
    * its implementation should cast and call through to the type checked method above</pre>
    * 
    * @param slot - another Slot to compare with
    * @return - true if the slot values are equal according to above equals method
    */
   @Override
   public abstract boolean equals(Object slot);

   /**
    * <b>NOTE:</b> if equals() is true then generated hashCode should also be equal
    * 
    * @return - generated hash code (used by various JCF Collections)
    * 
    */
   @Override
   public abstract int hashCode();
}