/*
 */
package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.RoomMisc.facilities;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public abstract class AbstractRoom implements IRoom {
  private static int roomCounter = 0;
  private int roomID;
  private float price = 0;
  // TODO: Make AbstractGuest instead of String
  private AbstractGuest guest;
  protected facilities facilities;
  
  public AbstractRoom(float price) {
    roomID = roomCounter++;
    this.price = price;
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

  public int getID() {
    return roomID;
  }
  
  

  @Override
  public facilities getFacilities() {
    return facilities;
  }

  @Override
  public String toString() {
    return "ID: " + getID() + ", Pris: " + getPrice() + ", Occupied? " + isOccupied() + "\n";
  }
  
  
}
