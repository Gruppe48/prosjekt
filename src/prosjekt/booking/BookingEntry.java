/*
 */
package prosjekt.booking;

import java.util.Date;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 * Dette er objektet som representerer en booking i Bookinglisten.
 */
public class BookingEntry {
  private AbstractGuest guest = null;
  private AbstractRoom room = null;
  private Date from = null;
  private Date to = null;
}
