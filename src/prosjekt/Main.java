/*
 */
package prosjekt;

import prosjekt.booking.BookingRegistry;
import prosjekt.guests.GuestRegistry;
import prosjekt.interfaces.AdminWindow2;
import prosjekt.rooms.RoomRegistry;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.guests.Person;
import prosjekt.interfaces.AdminWindow;
import prosjekt.interfaces.GuestWindow;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class Main {
  public static RoomRegistry roomRegistry = new RoomRegistry();
  public static BookingRegistry bookingRegistry = new BookingRegistry();
  public static GuestRegistry guestRegistry = new GuestRegistry();
  
  private static void setupRooms() {
    for (int i = 0; i < 10; i++) {
      SingleRoom room = new SingleRoom();
      roomRegistry.add(room);
    }
  }
  
  private static void setupGuests() {
    for (int i = 0; i < 10; i++) {
      Person guest = new Person("Even"+i, "Augdal"+i, "Tlf"+i, "Adresse"+i, 1000+i);
      guestRegistry.add(guest);
    }
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
    //Utils.load("kittens");
    
    new GuestWindow();
    
  }
}
