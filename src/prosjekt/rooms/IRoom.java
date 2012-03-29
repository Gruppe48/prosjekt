package prosjekt.rooms;

import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */

public interface IRoom {
  public int roomNumber = -1;
  public double price = 0;
  public boolean booked = false;
  // TODO: Make AbstractGuest instead of String
  public String guest = "";
  public int[] facilities = new int[20];
}
