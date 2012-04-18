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
public class BookingHistory {
  // "Indeksert" etter from, to og room.
  private ArrayList<BookingEntry> list = new ArrayList();
  
  public boolean add(BookingEntry entry) {
    //TODO: Sjekk om rommet er reservert, sjekk om gjesten har reservert osv!
    list.add(entry);
    return true;
  }
  public ArrayList<BookingEntry> getList() {
    return list;
  }
//  public boolean remove(Date from, Date to, AbstractRoom room) {
//    for (BookingEntry e : list) {
//      if (e.getFromDate().equals(from) && e.getToDate().equals(to) && e.getRoom().equals(room)) {
//        list.remove(e);
//        return true;
//      }
//    }
//    return false;
//  }
//  public boolean isBooked(AbstractRoom room) {
//    for (BookingEntry e : list) {
//      if (e.getRoom().equals(room)) {
//        return true;
//      }
//     }
//    return false;
//  }
}
