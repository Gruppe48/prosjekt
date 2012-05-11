/*
 */
package prosjekt.booking;

import java.util.ArrayList;
import java.util.Date;
import prosjekt.IStorable;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class BookingHistory implements IStorable {
  // "Indeksert" etter from, to og room.
  private ArrayList<BookingEntry> list = new ArrayList();

  public BookingHistory() {
   init();
  }
  @Override
  public final void init() {
      if (Utils.fileExists("bookingHistory.json")) {
      load();
    }
    else {
      // Setup default options
      save();
    }
  }
  
  @Override
   public void save() {
    Utils.save(list, "bookingHistory.json");
  }
  @Override
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
