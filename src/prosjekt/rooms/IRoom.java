package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.RoomMisc.facilities;


/**
 * This is the IRoom interface, responsible for defining methods common
 * to all rooms in the hotel.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */

public interface IRoom {
  /**
   * This method returns true or false based on room occupancy.
   * @return true/false status of room occupancy.
   */
  public boolean isOccupied();
  /**
   * Getter for guest.
   * @return The guest of the room, or null if empty.
   */
  public AbstractGuest getGuest();
  /**
   * Getter for price.
   * @return room price
   */
  public float getPrice();
  /**
   * Getter for roomID
   * @return roomID (used as roomNumbers)
   */
  public int getID();
  /**
   * Getter for room facilities.
   * @return The facilities enum the room has.
   */
  public facilities getFacilities();
  /**
   * This method rents out a room to a specific guest (i.e assigns it to a guest).
   * @param guest , the guest to rent the room to.
   */
  public void rent(AbstractGuest guest);
  /**
   * This method clears out a room.
   * i.e it sets the guest to null.
   */
  public void empty();
   /**
   * This method returns the type of room type this is.
   * The string returned in in Norwegian to fit with the interface.
   * A future enhancement would be to use I18N internationalization on all strings.
   * @return  The type of room this is.
   */
  public String getRoomType();
  
}
