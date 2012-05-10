/*
 */
package prosjekt;

import prosjekt.guests.GuestBook;
import prosjekt.utils.Options;
import prosjekt.booking.BookingRegistry;
import prosjekt.guests.Company;
import prosjekt.guests.GuestRegistry;
import prosjekt.rooms.RoomRegistry;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.guests.Person;
import prosjekt.interfaces.AdminWindow2;
import prosjekt.interfaces.GuestWindow;

import prosjekt.interfaces.LoginWindow;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class Main {
  public static RoomRegistry roomRegistry = new RoomRegistry();
  public static BookingRegistry bookingRegistry = new BookingRegistry();
  public static GuestRegistry guestRegistry = new GuestRegistry();
  public static Options options = new Options(); // load options!
  public static GuestBook guestBook = new GuestBook();
  
  private static void setupRooms() {
    if (Utils.fileExists("roomRegistry.json")) {
      loadRooms();
    }
    else {
      for (int i = 0; i < 10; i++) {
        SingleRoom room = new SingleRoom();
        roomRegistry.add(room);
      }
      saveRooms();
    }
  }
  
  private static void setupGuests() {
    if (Utils.fileExists("guestRegistry.json")) {
      loadGuests();
    }
    else {
      for (int i = 0; i < 10; i++) {
        Person guest = new Person("Even"+i, "Augdal"+i, "Tlf"+i, "Adresse"+i, 1000+i);
        guestRegistry.add(guest);
      }
      saveGuests();
    }
  }
  private static void setupBooking() {
    if (Utils.fileExists("bookingRegistry.json")) {
      loadBooking();
    }
    else {
      // Setup default options
      saveBooking();
    }
    
    Company cGuest = new Company("Ole", "Hansen", "12345678", "Kirkeveien 5", 0361, "Microsoft");
    guestRegistry.add(cGuest);
  }
  public static void saveRooms() {
    Utils.save(roomRegistry, "roomRegistry.json");
  }
  public static void loadRooms() {
    roomRegistry = (RoomRegistry) Utils.load("roomRegistry.json");
  }
  
  public static void saveGuests() {
    Utils.save(guestRegistry, "guestRegistry.json");
  }
  public static void loadGuests() {
    guestRegistry = (GuestRegistry) Utils.load("guestRegistry.json");
  }
  
  public static void saveBooking() {
    Utils.save(bookingRegistry, "bookingRegistry.json");
  }
  public static void loadBooking() {
    bookingRegistry = (BookingRegistry) Utils.load("bookingRegistry.json");
  }
 
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    setupRooms();
    setupGuests();
    setupBooking();
    new AdminWindow2();

    
  }
}
