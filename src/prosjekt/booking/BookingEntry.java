/*
 */
package prosjekt.booking;

import java.util.Date;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author Kristoffer Berdal 180212 <web@flexd.net>
 * @date Mar 29, 2012
 * Dette er objektet som representerer en booking i Bookinglisten.
 */
public class BookingEntry {
  private AbstractGuest guest = null;
  private AbstractRoom room = null;
  private Date from = null;
  private Date to = null;

  public BookingEntry(Date from, Date to, AbstractGuest guest, AbstractRoom room) {
    //TODO: Validering!
    this.from = from;
    this.to = to;
    this.guest = guest;
    this.room = room;
  }

  public Date getFromDate() {
    return from;
  }

  public AbstractGuest getGuest() {
    return guest;
  }

  public AbstractRoom getRoom() {
    return room;
  }

  public Date getToDate() {
    return to;
  }
  
  
  
}
