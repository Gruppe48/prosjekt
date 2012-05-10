/*
 */
package prosjekt.rooms;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public abstract class HotelRoom extends AbstractRoom implements IHotelRoom {
  protected int bedCount = 0;
  
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
