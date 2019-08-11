package view.interfaces;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;

/**
 * Assignment interface for Further Programming to notify client of GameEngine events<br>
 * i.e. the slot that the ball has entered as the wheel spins and the final result when it stops
 * 
 * @author Caspar Ryan
 * 
 */
public interface GameEngineCallback
{
   /**
    * called as the wheel spins<br>
    * use this to update your GUI or log to console
    * 
    * @param slot - the next slot that the rolling ball entered
    * @param engine - a convenience reference to the engine so the receiver can call methods if necessary
    * @see model.interfaces.GameEngine
    */
   public void nextSlot(Slot slot, GameEngine engine);

   /**
    * called when the wheel has stopped spinning<br>
    * this is a convenient place to call {@link GameEngine#calculateResult(Slot winningSlot)}<br>
    * and {@link Player#resetBet()}
    * 
    * @param winningSlot - the slot that the ball landed in
    * @param engine - a convenience reference to the engine so the receiver can call methods if necessary
    * @see model.interfaces.GameEngine
    */
   public void result(Slot winningSlot, GameEngine engine);
}
