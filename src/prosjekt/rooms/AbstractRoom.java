/*
 */
package prosjekt.rooms;

import prosjekt.guests.AbstractGuest;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public abstract class AbstractRoom implements IRoom {
  private static int roomCounter = 0;
  private int roomID;
  private double price = 0;
  private boolean occupied = false;
  // TODO: Make AbstractGuest instead of String
  private AbstractGuest guest;
  private int[] facilities = new int[20];
  
  public AbstractRoom() {
    roomID = roomCounter++;
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
  public double getPrice() {
    return price;
  }

  public int getID() {
    return roomID;
  }
  
  

  @Override
  public int[] getFacilities() {
    return facilities;
  }
  
  
}
