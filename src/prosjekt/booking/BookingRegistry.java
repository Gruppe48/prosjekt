/*
 */
package prosjekt.booking;

import java.util.ArrayList;
import java.util.Date;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class BookingRegistry {
  // "Indeksert" etter from, to og room.
  private ArrayList<BookingEntry> list = new ArrayList();
  private BookingHistory history = new BookingHistory();
  public boolean add(Date from, Date to, AbstractGuest guest, AbstractRoom room) {
    //TODO: Sjekk om rommet er reservert, sjekk om gjesten har reservert osv!
    if (isBooked(room)) { 
      return false; 
    }
    BookingEntry booking = new BookingEntry(from, to, guest, room);
     
    
    list.add(booking);
    history.add(booking); // Save the booking for history!
    
    return true;
  }
  public ArrayList<BookingEntry> getList() {
    return list;
  }
  public boolean remove(Date from, Date to, AbstractRoom room) {
    for (BookingEntry e : list) {
      if (e.getFromDate().equals(from) && e.getToDate().equals(to) && e.getRoom().equals(room)) {
        list.remove(e);
        return true;
      }
    }
    return false;
  }
  public boolean isBooked(AbstractRoom room) {
    for (BookingEntry e : list) {
      if (e.getRoom().equals(room)) {
        return true;
      }
     }
    return false;
  }
  public ArrayList<BookingEntry> getHistory(int roomID) {
    ArrayList<BookingEntry> out = new ArrayList<BookingEntry>();
    
    for (BookingEntry r : history.getList()) {
      if (r.getRoom().getID() == roomID) {
        out.add(r);
      }
    }
    return out;
  }
}