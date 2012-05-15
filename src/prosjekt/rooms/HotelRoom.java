
package prosjekt.rooms;

/**
 * This is the HotelRoom class.
 * This class defines methods and variables unique
 * to the hotelrooms.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */
public abstract class HotelRoom extends AbstractRoom implements IHotelRoom {
   /**
   * The amount of beds a room has.
   */
  protected int bedCount = 0;
   
  /**
   * This is the HotelRoom constructor.
   */
  public HotelRoom() {
    super();
  }
  @Override
  public int getBedCount() {
    return bedCount;
  }

  @Override
  public void setBedCount(int amount) {
    this.bedCount = amount;
  }
  
}
