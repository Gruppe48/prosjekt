package prosjekt.rooms;

/**
 * This is the IHotelRoom interface.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public interface IHotelRoom {
  /**
   * Sets the amount of beds for a room
   * @param amount of beds in the room
   */
  public void setBedCount(int amount);
  
  /**
   * This method returns the amount of beds in this room.
   * @return The amount of beds in this room.
   */
  public int getBedCount();
}
