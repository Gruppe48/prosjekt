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
  private int roomNumber = -1;
  private double price = 0;
  private boolean booked = false;
  // TODO: Make AbstractGuest instead of String
  private AbstractGuest guest;
  private int[] facilities = new int[20];
  
  @Override
  public boolean isBooked() {
    return booked;
  }

  @Override
  public AbstractGuest getGuest() {
    return guest;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public int getRoomNumber() {
    return roomNumber;
  }

  public AbstractRoom() {
  }

  @Override
  public int[] getFacilities() {
    return facilities;
  }
  
  
}
