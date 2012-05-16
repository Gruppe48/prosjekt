package prosjekt.booking;

import java.util.ArrayList;
import java.util.Date;
import prosjekt.IStorable;
import prosjekt.Main;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;
import prosjekt.utils.Utils;

/**
 * This class implements the BookingRegistry
 * It is responsible for handling all booking of rooms.
 * 
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */
public class BookingRegistry implements IStorable {
  /**
   * The booking list itself.
   */
  private ArrayList<BookingEntry> list = new ArrayList();
  /**
   * This is our booking history object, it is pretty much identical and holds previous bookings.
   * Currently it is actually a bit surplus since bookings are never removed from the booking list.
   */
  private BookingHistory history = new BookingHistory();
  
  /**
   * Holds if the list needs to be reloaded or not.
   */
  boolean dirty = false;
  /**
   * The constructor for BookingRegistry calls init() as per the IStorable interface.
   */
  public BookingRegistry() {
    init();
  }
  
  
  @Override
  public final void init() {
    if (Utils.fileExists("bookingRegistry.json")) {
      load();
    }
    else {
      // Setup default options
      save();
    }
  }
  @Override
  public void save() {
    dirty = true;
    Utils.save(list, "bookingRegistry.json");
    history.save();
  }
  @Override
  public void load() {
    dirty = false;
    list = (ArrayList<BookingEntry>) Utils.load("bookingRegistry.json");
    history.load();
  }

  

  
  /**
   * This method adds a booking entry to the booking list,
   * if there is a available room.
   * 
   * @param from Start of the booking entry
   * @param to end of the booking entry.
   * @param guest Which guest is making a reservation/booking.
   * @param type What kind of room being booked.
   * @return A booking entry if successful, null if unsuccessful.
   */
  public BookingEntry add(Date from, Date to, AbstractGuest guest, String type) {
    //TODO: Sjekk om rommet er reservert, sjekk om gjesten har reservert osv!
    
    AbstractRoom room = findRoom(from, to, type);
    
    if (room != null) {
      // We actually found an avaible room!
      BookingEntry booking = new BookingEntry(from, to, guest, room);
      list.add(booking);
      history.add(booking); // Save the booking for history!
      save();
      return booking;
    }
    else {
      // There is no room!
      return null;
    }
  }
  /**
   * 
   * This method finds a available room of a specific type, in a specific time frame.
   * 
   * @param from start of time frame.
   * @param to end of time frame.
   * @param type Type of room we want.
   * @return A room that is available in that time frame, or null if none are available.
   */
  private AbstractRoom findRoom(Date from, Date to, String type) {
    AbstractRoom room;
    ArrayList<AbstractRoom> rooms = findUnbookedRooms(type);
    
    if (rooms.size() > 0) {
      // We have one (or more) free rooms.
      room = rooms.get(0); // Get the first room in the list!
    }
    else {
      // All the rooms have been booked before, let's check the bookingregistry for any rooms free in this period.
      room = findRoomFromRegistry(from, to, type);
    }
   return room; 
  }
  /**
   * This method finds all the rooms of a specific type that have never been booked before.
   * i.e the rooms that are guaranteed to be free.
   * @param type The type of room.
   * @return A list of free rooms.
   */
  private ArrayList<AbstractRoom> findUnbookedRooms(String type) {
    ArrayList<AbstractRoom> rooms = Main.roomRegistry.getRoomsByType(type);
    ArrayList<AbstractRoom> bookingRooms = getBookingRooms();
    ArrayList<AbstractRoom> matches = new ArrayList<AbstractRoom>();
    
    for (AbstractRoom r : rooms) {
      if (isBooked(r)) {
        continue;
      }
      else {
        matches.add(r);
      }
    }
    return matches;
  }
  /**
   * This method finds free rooms of a specific type in a specific time frame,
   * from the rooms that have been previously booked.
   * i.e rooms that have been booked before but are available again.
   * @param from the start of the time frame
   * @param to the end of the time frame
   * @param type the type of room we are looking for.
   * @return This method returns a available room, or null if there are no available rooms.
   */
  private AbstractRoom findRoomFromRegistry(Date from, Date to, String type) {
    ArrayList<AbstractRoom> rooms = Main.roomRegistry.getRoomsByType(type);
    ArrayList<Date[]> bookedDates;
    
    // Lets loop through all matching rooms
    for (AbstractRoom r : rooms) {
      // For each room, we want to loop through the bookinglist
      for (BookingEntry e : list) {
        // if we find the room in our bookinglist...
        if (e.getRoom().getID() == r.getID()) {
          //...we grab a list of it's occupied dates
          bookedDates = OccupiedDates(e.getRoom());
          // Let's make it more complicated by looping all booked dates for our room
          for(Date[] d : bookedDates) {
            // if the any date is between a booking...
            if(d[0].compareTo(from)*from.compareTo(d[1]) > 0 || d[0].compareTo(to)*to.compareTo(d[1]) > 0 || d[0].equals(from) || 
                    d[1].equals(to) || from.before(d[0]) && to.after(d[1]) || from.after(d[0]) && to.before(d[1])) {
              //...we simply leave it. No reason to be in this loop
              break;
            } 
            // hah! We made it. This room should be avaible in this period. Return it!
            return r;
          }
        }
      }
    }
    // There is no avaible room
    return null;
  }
  /**
   * This method returns all from and to dates on bookings of a specific room.
   * @param room the room we want to lookup
   * @return A list of occupied dates, or null if there is no dates to fetch.
   */
  private ArrayList<Date[]> OccupiedDates(AbstractRoom room) {
    ArrayList<Date[]> dateList = new ArrayList();
    Date[] arrDates = new Date[2]; 
    for(BookingEntry e : list) {
      if(e.getRoom().getID() == room.getID()) {
        arrDates[0] = e.getFromDate();
        arrDates[1] = e.getToDate();
        dateList.add(arrDates);
      }
    }
    return (dateList.size() > 0) ? dateList : null;
  }
  /**
   * This method returns the bookingRegistry list.
   * @return A list of all the booking entries.
   */
  public ArrayList<BookingEntry> getList() {
    if (dirty) {
      load();
    }
    return list;
  }
  /**
   * This method removes a specific booking entry.
   * 
   * @param from the start of the time frame
   * @param to the end of the time frame
   * @param room the specific room we are looking to remove.
   * @return true or false based on result, false if we cannot find the specified booking entry.
   */
  public boolean remove(Date from, Date to, AbstractRoom room) {
    for (BookingEntry e : list) {
      if (e.getFromDate().equals(from) && e.getToDate().equals(to) && e.getRoom().equals(room)) {
        list.remove(e);
        save();
        return true;
        
      }
    }
    return false;
  }
  /**
   * This method checks in a VERY simple way if the room has ever been booked.
   * @param room The room to check for
   * @return true or false, false if the room has never been booked.
   */
  public boolean isBooked(AbstractRoom room) {
    for (BookingEntry e : list) {
      if (e.getRoom().getID() == room.getID()) {
        return true;
      }
     }
    return false;
  }
  /**
   * This method returns the booking history of a specfic room.
   * @param roomID  The roomID of the specific room we want the history of.
   * @return A list of booking entries for this room, or a empty list.
   */
  public ArrayList<BookingEntry> getHistory(int roomID) {
    ArrayList<BookingEntry> out = new ArrayList<BookingEntry>();
    
    for (BookingEntry r : history.getList()) {
      if (r.getRoom().getID() == roomID) {
        out.add(r);
      }
    }
    return out;
  }
 /**
   * This method returns all the rooms that exist in the booking list.
   * @return A list of all rooms that have previously (or are currently) booked.
   */
  private ArrayList<AbstractRoom> getBookingRooms() {
    ArrayList<AbstractRoom> rooms = new ArrayList<AbstractRoom>();
    for (BookingEntry bookingEntry : list) {
      rooms.add(bookingEntry.getRoom());
    }
    return rooms;
  } 
  /**
   * This method is searching for matching bookingentry
   * @param bookingnr
   * @return the BookingEntry which matches the specified bookingnumber, or null if there is no match.
   */
  public BookingEntry getBooking(int bookingnr) {
    for(BookingEntry be : list) {
      if(be.getBookingNumber() == bookingnr) {
        return be;
      }
    }
    return null;
  }
  /**
   * This method removes all bookings this guest have made.
   * @param guest The guest we want bookings removed for.
   */
  public void removeGuestBookings(AbstractGuest guest) {
    for (BookingEntry e : list) {
      if (e.getGuest().getID() == guest.getID()) {
        list.remove(e);
      }
    }
    save();
  }
}
