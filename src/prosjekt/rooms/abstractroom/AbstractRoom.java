/*
 */
package prosjekt.rooms.abstractroom;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public abstract class AbstractRoom implements IRoom {

  public static boolean isBooked() {
    return booked;
  }

  public static String getGuest() {
    return guest;
  }

  public static double getPrice() {
    return price;
  }

  public static int getRoomNumber() {
    return roomNumber;
  }

  public AbstractRoom() {
  }

  public static int[] getFacilities() {
    return facilities;
  }
  
  
}
