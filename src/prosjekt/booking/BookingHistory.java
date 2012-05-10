/*
 */
package prosjekt.booking;

import java.util.ArrayList;
import java.util.Date;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class BookingHistory {
  // "Indeksert" etter from, to og room.
  private ArrayList<BookingEntry> list = new ArrayList();

  public BookingHistory() {
     if (Utils.fileExists("bookingHistory.json")) {
      load();
    }
    else {
      // Setup default options
      save();
    }
  }
  
  
   public void save() {
    Utils.save(list, "bookingHistory.json");
  }
  public void load() {
    list = (ArrayList<BookingEntry>) Utils.load("bookingHistory.json");
  }
  
  public boolean add(BookingEntry entry) {
    //TODO: Sjekk om rommet er reservert, sjekk om gjesten har reservert osv!
    list.add(entry);
    return true;
  }
  public ArrayList<BookingEntry> getList() {
    return list;
  }
}
