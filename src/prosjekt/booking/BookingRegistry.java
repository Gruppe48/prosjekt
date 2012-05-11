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
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class BookingRegistry implements IStorable {
  // "Indeksert" etter from, to og room.
  private ArrayList<BookingEntry> list = new ArrayList();
  private BookingHistory history = new BookingHistory();

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

  public boolean add(Date from, Date to, AbstractGuest guest, String type) {
    //TODO: Sjekk om rommet er reservert, sjekk om gjesten har reservert osv!
    
    AbstractRoom room = findRoom(from, to, type);
    
    if (room != null) {
      System.out.println("Rommet er ikke null, lager booking entry!");
      // We actually found a free room!
      BookingEntry booking = new BookingEntry(from, to, guest, room);
      list.add(booking);
      history.add(booking); // Save the booking for history!
      save();
      return true;
    }
    else {
      // There is no room!
      return false;
    }
  }
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
  private AbstractRoom findRoomFromRegistry(Date from, Date to, String type) {
    ArrayList<AbstractRoom> rooms = Main.roomRegistry.getRoomsByType(type);
    AbstractRoom result = null;
    for (AbstractRoom r : rooms) {
      for (BookingEntry e : list) {
        if (e.getRoom().getID() == r.getID()) {
          // Entry overlapping our timeframe
          if ((e.getFromDate().before(from)) && (e.getToDate().after(to))) {
            continue;
          }
          // Entry inside our timeframe
          else if ((e.getFromDate().after(from)) && (e.getToDate().after(from)) && (e.getToDate().before(to))) {
            continue;
          }
          // Entry starts before our from, but ends inside our timeframe.
          else if ((e.getFromDate().before(from)) && (e.getToDate().after(from)) && (e.getToDate().before(to))) {
            continue;
          }
          // Entry starts within our timeframe and ends after our to.
          else if ((e.getFromDate().after(from)) && (e.getFromDate().before(to)) && (e.getToDate().after(to))) {
            continue;
          }
          result = e.getRoom();
        }
      }
    }
    return result;
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
      if (e.getRoom().getID() == room.getID()) {
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

 
  private ArrayList<AbstractRoom> getBookingRooms() {
    ArrayList<AbstractRoom> rooms = new ArrayList<AbstractRoom>();
    for (BookingEntry bookingEntry : list) {
      rooms.add(bookingEntry.getRoom());
    }
    return rooms;
  }
}
