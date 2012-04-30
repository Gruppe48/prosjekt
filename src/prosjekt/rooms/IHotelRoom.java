/*
 */
package prosjekt.rooms;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public interface IHotelRoom {
  /*
   * Sets the amount of beds for a room
   * @param amount amount of Beds
   */
  public void setBedCount(int amount);
  
  /*
   * gets the amount of beds in this room.
   */
  public int getBedCount();
}
