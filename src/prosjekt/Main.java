/*
 */
package prosjekt;

import prosjekt.booking.BookingRegistry;
import prosjekt.guests.GuestRegistry;
import prosjekt.interfaces.AdminWindow2;
import prosjekt.rooms.RoomRegistry;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.guests.Person;

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
      SingleRoom room = new SingleRoom(500f);
      roomRegistry.add(room);
    }
  }
  
  private static void setupGuests() {
    for (int i = 0; i < 10; i++) {
      Person guest = new Person("Even"+i, "Augdal"+i, "Tlf"+i, "Adresse"+i, 1000+i);
      guestRegistry.add(guest);
    }
  }
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    setupRooms();
    setupGuests();
    // Open the login window
    //new LoginWindow(
    new AdminWindow2();
  }
}
