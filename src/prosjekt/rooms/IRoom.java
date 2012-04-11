package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.RoomMisc.facilities;


/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */

public interface IRoom {
  public boolean isOccupied();
  public AbstractGuest getGuest();
  public float getPrice();
  public int getID();
  public facilities getFacilities();
  public void rent(AbstractGuest guest);
  public void empty();
}
