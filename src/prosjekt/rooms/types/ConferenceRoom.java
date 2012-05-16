package prosjekt.rooms.types;

import prosjekt.rooms.SeminarRoom;

/**
 * This is the ConferenceRoom class.
 * This class holds the unique methods and variables for a Conference Room.
 * It also sets the room price for 24h.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class ConferenceRoom extends SeminarRoom {

  /**
   * Construactor for conference room.
   * This sets the price.
   */
  public ConferenceRoom() {
    super();
    price = 3000;
  }
  
}
