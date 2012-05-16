package prosjekt.rooms.types;

import prosjekt.rooms.SeminarRoom;

/**
 * This is the MeetingRoom class.
 * This class holds the unique methods and variables for a Meeting Room.
 * It also sets the room price for 24h.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class MeetingRoom extends SeminarRoom {

  /**
   * Constructor for Meeting Room
   * This sets the price for a meeting room.
   */
  public MeetingRoom() {
    super();
    price = 1500;
  }
  
}
