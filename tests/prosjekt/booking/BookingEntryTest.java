/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.booking;

import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Person;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author kristoffer
 */
public class BookingEntryTest {
  
  public BookingEntryTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getFromDate method, of class BookingEntry.
   */
  @Test
  public void testGetFromDate() {
    System.out.println("getFromDate");
    BookingEntry instance = new BookingEntry(Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), null, null);
    Date expResult = Calendar.getInstance().getTime();
    Date result = instance.getFromDate();
    assertEquals(expResult, result);
  }
  /**
   * Test of getToDate method, of class BookingEntry.
   */
  @Test
  public void testGetToDate() {
    System.out.println("getToDate");
    BookingEntry instance = new BookingEntry(Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), null, null);
    Date expResult = Calendar.getInstance().getTime();
    Date result = instance.getToDate();
    assertEquals(expResult, result);
  }

  /**
   * Test of getBookingNumber method, of class BookingEntry.
   */
  @Test
  public void testGetBookingNumber() {
    System.out.println("getBookingNumber");
    BookingEntry instance = new BookingEntry(Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), null, null);
    int expResult = 3; 
    int result = instance.getBookingNumber();
    assertEquals(expResult, result);
  }
}
