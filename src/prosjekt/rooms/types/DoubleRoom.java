package prosjekt.rooms.types;

import prosjekt.rooms.HotelRoom;

/**
 * This is the DoubleRoom class.
 * This class holds the unique methods and variables for a DoubleRoom.
 * It also sets the room price for 24h.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class DoubleRoom extends HotelRoom {

  public DoubleRoom() {
    super();
    bedCount = 4;
    price = 1200;
  }
  
}
