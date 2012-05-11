/*
 */
package prosjekt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import prosjekt.booking.BookingEntry;
import prosjekt.guests.GuestBook;
import prosjekt.utils.Options;
import prosjekt.booking.BookingRegistry;
import prosjekt.guests.GuestRegistry;
import prosjekt.rooms.RoomRegistry;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.guests.Person;

import prosjekt.interfaces.AdminWindow;
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
   
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws ParseException {
    new AdminWindow();
    SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
    

    BookingEntry result = bookingRegistry.add(formatter.parse("1/02/2012"), formatter.parse("1/02/2013"), guestRegistry.getGuest("Even0", "Augdal0", "Tlf0"), "SingleRoom");
    if(result != null )
      System.out.println("booked!");
    else
      System.out.println("ikke plass");
  }
}
