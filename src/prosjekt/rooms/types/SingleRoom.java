package prosjekt.rooms.types;

import prosjekt.rooms.HotelRoom;

/**
 * This is the SingleRoom class.
 * This class holds the unique methods and variables for a SingleRoom.
 * It also sets the room price for 24h.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class SingleRoom extends HotelRoom {
  public SingleRoom() {
    super();
    bedCount = 2;
    price = 750;
  }
}
