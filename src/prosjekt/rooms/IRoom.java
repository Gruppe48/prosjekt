package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.RoomMisc.facilities;


/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */

public interface IRoom {
  /*
   * @return true/false status of room occupancy.
   */
  public boolean isOccupied();
  /*
   * @return AbstractGuest guest of the room, or null if empty.
   */
  public AbstractGuest getGuest();
  /*
   * @return room price
   */
  public float getPrice();
  /*
   * @return roomID (used as roomNumbers)
   */
  public int getID();
  /*
   * @return facilities the room has
   */
  public facilities getFacilities();
  /*
   * @param AbstractGuest guest, the guest to rent the room to.
   */
  public void rent(AbstractGuest guest);
  /*
   * @param unrent/clear the room, makes it vacant.
   */
  public void empty();
  
  
  
}
