package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.types.ConferenceRoom;
import prosjekt.rooms.types.DoubleRoom;
import prosjekt.rooms.types.MeetingRoom;
import prosjekt.rooms.types.SingleRoom;

/**
 * This is the AbstractRoom class.
 * This class is the superclass for all room types in the hotel.
 * This class defines variables and methods common to all rooms.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */
public abstract class AbstractRoom implements IRoom {
  /**
   * This variable holds the roomCounter, which is increased each time a new room is added.
   * The variable is shared between all instances of the object so that each time we add a room the number
   * increases (by using roomCounter++).
   */
  protected static int roomCounter = 0;
   /**
   * This is the roomID.
   */
  protected int roomID;
   /**
   * This is a variable holding the rooms price, it is statically defined on a per room type basis.
   */
  protected float price = 0;
  
   /**
   * This variable holds the guest currently living in the room.
   */
  protected AbstractGuest guest;
   
  private boolean occupied = false;
  
  /**
  * This is the AbstractRoom constructor.
  * This constructor sets the roomID by increasing the roomCounter variable.
  */
  public AbstractRoom() {
    roomID = ++roomCounter;
  }

  @Override
  public void rent(AbstractGuest guest) {
    this.guest = guest;
  }
  @Override
  public void empty() {
    this.guest = null;
    occupied = false;
  }
  
  /**
   * This method checks a guest into the room.
   * ie. the room is marked as occupied.
   */
  public void checkIn() {
    occupied = true;
  }
  /**
   * This method checks a guest out of the room.
   * ie. the room is marked as unoccupied.
   */
  public void checkOut() {
    occupied = false;
  }
  @Override
  public boolean isOccupied() {
    return occupied;
  }
  
  @Override
  public AbstractGuest getGuest() {
    return guest;
  }

  @Override
  public float getPrice() {
    return price;
  }

  @Override
  public int getID() {
    return roomID;
  }
   
  @Override
  public String getRoomType() {
    String rt = "";
    
    if(this instanceof SingleRoom) { rt = "Enkeltrom"; }
    else if (this instanceof DoubleRoom) { rt = "Dobbeltrom"; }
    else if (this instanceof ConferenceRoom) { rt = "Konferanserom"; }
    else if (this instanceof MeetingRoom) { rt = "Møterom"; }
    
    return rt;
  }
  
  /**
   * This method returns a string with all the room info.
   * @return A string containing all the room info.
   */
  @Override
  public String toString() {
    String r = "RomID: " + getID() + 
            "\nPris: " + getPrice() + ",-" + 
            "\nLedig? ";
    r += (isOccupied()) ? "Nei" : "Ja" + "\n";
    
    return r;
  }
  
  
  
}
