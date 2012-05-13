/*
 */
package prosjekt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import prosjekt.guests.GuestBook;
import prosjekt.utils.Options;
import prosjekt.booking.BookingRegistry;
import prosjekt.guests.GuestRegistry;
import prosjekt.rooms.RoomRegistry;

import prosjekt.interfaces.AdminWindow2;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class Main {
  /**
   * roomRegistry for the entire application stack.
   */
  public static RoomRegistry roomRegistry = new RoomRegistry();
  /**
   * bookingRegistry for the entire application stack.
   */
  public static BookingRegistry bookingRegistry = new BookingRegistry();
  /**
   * guestRegistry for the entire application stack.
   */
  public static GuestRegistry guestRegistry = new GuestRegistry();
  /**
   * options for the entire application stack.
   */
  public static Options options = new Options();
  /**
   * guestBook for the entire application stack.
   * TODO: This could actually only be loaded in GuestWindow as it is not used anywhere else.
   * An alternative would be to have a moderation window for new guest book messages in the administration window,
   * but that will have to be in the future.
   */
  public static GuestBook guestBook = new GuestBook();
   
  /**
   * 
   * The main method that creates the first window (LoginWindow).
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) throws ParseException {
    new AdminWindow2();
    SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
    boolean result = false;

    result = bookingRegistry.add(formatter.parse("1/02/2012"), formatter.parse("1/02/2013"), guestRegistry.getGuest("Even0", "Augdal0", "Tlf0"), "SingleRoom");
    System.out.println(result); 
  }
}
