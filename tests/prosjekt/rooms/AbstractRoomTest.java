/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Person;
import prosjekt.rooms.types.SingleRoom;

/**
 *
 * @author kristoffer
 */
public class AbstractRoomTest {
  Person guest = null;
  SingleRoom instance = null;
  public AbstractRoomTest() {
    guest = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    instance = new SingleRoom();
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
   * Test of rent method, of class AbstractRoom.
   */
  @Test
  public void testRent() {
    System.out.println("rent");
    
    instance.rent(guest);
  }
  /**
   * Test of isOccupied method, of class AbstractRoom.
   */
  @Test
  public void testIsOccupied() {
    System.out.println("isOccupied");
    boolean expResult = false;
    boolean result = instance.isOccupied();
    assertEquals(expResult, result);

  }
  /**
   * Test of getPrice method, of class AbstractRoom.
   */
  @Test
  public void testGetPrice() {
    System.out.println("getPrice");
    float expResult = 750f;
    float result = instance.getPrice();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getID method, of class AbstractRoom.
   */
  @Test
  public void testGetID() {
    System.out.println("getID");
    int expResult = 4;
    int result = instance.getID();
    assertEquals(expResult, result);
  }

  /**
   * Test of getRoomType method, of class AbstractRoom.
   */
  @Test
  public void testGetRoomType() {
    System.out.println("getRoomType");
    String expResult = "Enkeltrom";
    String result = instance.getRoomType();
    assertEquals(expResult, result);
  }
}
