package prosjekt.booking;

import java.util.ArrayList;
import prosjekt.IStorable;
import prosjekt.utils.Utils;

/**
 * This is the BookingHistory class.
 * This class is responsible for tracking all booking entries
 * of all time.
 * This should be a way of tracking statistics and guest history.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * @date Mar 29, 2012
 */
public class BookingHistory implements IStorable {
  /**
   * This is the list that holds the booking entries.
   */
  private ArrayList<BookingEntry> list = new ArrayList();
  
  /**
   * This is the constructor for BookingHistory
   * This constructor runs init() as per all the other classes
   * implementing IStoreable.
   */
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
  
  /**
   * This method adds a BookingEntry to the history list.
   * We do no validation here as the Booking entry is already
   * validated in the BookingRegistry before being passed here.
   * @param entry the booking entry.
   * @return true/false based on success. (always true).
   */
  public boolean add(BookingEntry entry) {
    //TODO: Sjekk om rommet er reservert, sjekk om gjesten har reservert osv!
    list.add(entry);
    return true;
  }
  /**
   * This method returns a list of all the booking entries.
   */
  public ArrayList<BookingEntry> getList() {
    return list;
  }
}
