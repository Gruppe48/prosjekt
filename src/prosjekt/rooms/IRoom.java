package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */

public interface IRoom {
  public boolean isBooked();
  public AbstractGuest getGuest();
  public double getPrice();
  public int getRoomNumber();
  public int[] getFacilities();
}
