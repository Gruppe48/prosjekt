/*
 */
package prosjekt;

import prosjekt.booking.BookingRegistry;
import prosjekt.guests.GuestRegistry;
import prosjekt.rooms.RoomRegistry;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.guests.Person;
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
  public static Options options = new Options(); // loadObject options!
  public static GuestBook guestBook;
  
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
  }
  
  private static void setupGuestBook() {
    if (Utils.fileExists("guestBook.json")) {
      loadGuestBook();
    }
    else {
      // Setup default options
      saveGuestBook();
    }
  }
  public static void saveRooms() {
    Utils.saveObject(roomRegistry, "roomRegistry.json");
  }
  public static void loadRooms() {
    roomRegistry = (RoomRegistry) Utils.loadObject("roomRegistry.json");
  }
  
  public static void saveGuests() {
    Utils.saveObject(guestRegistry, "guestRegistry.json");
  }
  public static void loadGuests() {
    guestRegistry = (GuestRegistry) Utils.loadObject("guestRegistry.json");
  }
  
  public static void saveBooking() {
    Utils.saveObject(bookingRegistry, "bookingRegistry.json");
  }
  public static void loadBooking() {
    bookingRegistry = (BookingRegistry) Utils.loadObject("bookingRegistry.json");
  }
  
  public static void saveGuestBook() {
    Utils.saveObject(guestBook, "guestBook.json");
  }
  public static void loadGuestBook() {
    guestBook = (GuestBook) Utils.loadObject("guestBook.json");
  }
  
  
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    setupRooms();
    setupGuests();
    setupBooking();
    setupGuestBook();
    new GuestWindow();

    
  }
}
