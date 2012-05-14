package prosjekt.booking;

import java.util.Date;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;

/**
 * This class represents a single booking entry in our booking
 * registry. It holds the to and from date, the guest that owns
 * the booking as well as the room that is booked for that
 * period of time.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */
public class BookingEntry {
  /**
   * The guest
   */
  private AbstractGuest guest = null;
  /**
   * The room
   */
  private AbstractRoom room = null;
  /**
   * The from date
   */
  private Date from = null;
  /**
   * The to date
   */
  private Date to = null;
  private int bookingCounter = 0;
  private int bookingNumber;

  /**
   * This is the constructor for BookingEntry.
   * @param from the start of the time frame.
   * @param to the end of the time frame.
   * @param guest the guest the booking is for.
   * @param room the room reserved for this guest in this time frame.
   * 
   */
  public BookingEntry(Date from, Date to, AbstractGuest guest, AbstractRoom room) {
    //TODO: Validering!
    this.from = from;
    this.to = to;
    this.guest = guest;
    this.room = room;
    bookingNumber = ++bookingCounter;
  }
  /**
   * Getter for the from date.
   */
  public Date getFromDate() {
    return from;
  }
  /**
   * Getter for the guest
   */
  public AbstractGuest getGuest() {
    return guest;
  }
  /**
   * Getter for the room
   */
  public AbstractRoom getRoom() {
    return room;
  }
  /**
   * Getter for the to date.
   */
  public Date getToDate() {
    return to;
  }
  public int getBookingNumber() {
    return bookingNumber;
  }
  
} // End of BookingEntry
