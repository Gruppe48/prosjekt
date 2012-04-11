/*
 */
package prosjekt.rooms;

import prosjekt.rooms.RoomMisc.facilities;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public abstract class HotelRoom extends AbstractRoom implements IHotelRoom {

  public HotelRoom(float price) {
    super(price);
  }

}
