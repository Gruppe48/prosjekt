package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */

public interface IRoom {
  public boolean isOccupied();
  public AbstractGuest getGuest();
  public double getPrice();
  public int getID();
  public int[] getFacilities();
}
