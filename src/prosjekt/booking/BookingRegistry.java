/*
 */
package prosjekt.booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
 * @date Mar 29, 2012
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
    Utils.save(list, "bookingRegistry.json");
    history.save();
  }
  @Override
  public void load() {
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
      System.out.println("Rommet er ikke null, lager booking entry!");
      // We actually found a free room!
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
    AbstractRoom room = null;
    ArrayList<AbstractRoom> rooms = findUnbookedRooms(type);
    
    if (rooms.size() > 0) {
      System.out.println("There is: " + rooms.size() + " free rooms!");
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
    for (AbstractRoom r : rooms) {
      for (BookingEntry e : list) {
        if (e.getRoom().equals(r)) {
          if ((e.getFromDate().before(from) && e.getToDate().before(to)) || (e.getFromDate().after(from) && e.getToDate().after(to))) {
            return e.getRoom();
          }
        }
      }
    }
    return null;
  }
  /**
   * This method returns the bookingRegistry list.
   * @return A list of all the booking entries.
   */
  public ArrayList<BookingEntry> getList() {
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
        return true;
      }
    }
    return false;
  }
  /**
   * TODO: Fix this!
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
}
