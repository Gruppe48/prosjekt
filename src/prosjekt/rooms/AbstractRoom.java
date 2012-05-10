/*
 */
package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.RoomMisc.facilities;
import prosjekt.rooms.types.ConferenceRoom;
import prosjekt.rooms.types.DoubleRoom;
import prosjekt.rooms.types.MeetingRoom;
import prosjekt.rooms.types.SingleRoom;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public abstract class AbstractRoom implements IRoom {
  protected static int roomCounter = 0;
  protected int roomID;
  protected float price = 0;

  protected AbstractGuest guest;
  protected facilities facilities;
  
  public AbstractRoom() {
    roomID = roomCounter++;
  }

  @Override
  public void rent(AbstractGuest guest) {
    this.guest = guest;
  }
  @Override
  public void empty() {
    this.guest = null;
  }
  @Override
  public boolean isOccupied() {
    return (guest != null);
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
  public facilities getFacilities() {
    return facilities;
  }
  
  public String getRoomType() {
    String rt = "";
    
    if(this instanceof SingleRoom) { rt = "Enkeltrom"; }
    else if (this instanceof DoubleRoom) { rt = "Dobbeltrom"; }
    else if (this instanceof ConferenceRoom) { rt = "Konferanserom"; }
    else if (this instanceof MeetingRoom) { rt = "Møterom"; }
    
    return rt;
  }

  @Override
  public String toString() {
    String r = "RomID: " + getID() + 
            "\nPris: " + getPrice() + ",-" + 
            "\nLedig? ";
    r += (isOccupied()) ? "Nei" : "Ja" + "\n";
    
    return r;
  }
  
  
  
}
