/*
 */
package prosjekt.rooms.abstractroom;

import prosjekt.rooms.AbstractRoom;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class AbstractRoomTest {
  
  public AbstractRoomTest() {
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
   * Test of isBooked method, of class AbstractRoom.
   */
  @Test
  public void testIsBooked() {
    System.out.println("isBooked");
    boolean expResult = false;
    boolean result = AbstractRoom.isBooked();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getGuest method, of class AbstractRoom.
   */
  @Test
  public void testGetGuest() {
    System.out.println("getGuest");
    String expResult = "";
    String result = AbstractRoom.getGuest();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPrice method, of class AbstractRoom.
   */
  @Test
  public void testGetPrice() {
    System.out.println("getPrice");
    double expResult = 0.0;
    double result = AbstractRoom.getPrice();
    assertEquals(expResult, result, 0.0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getRoomNumber method, of class AbstractRoom.
   */
  @Test
  public void testGetRoomNumber() {
    System.out.println("getRoomNumber");
    int expResult = 0;
    int result = AbstractRoom.getRoomNumber();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFacilities method, of class AbstractRoom.
   */
  @Test
  public void testGetFacilities() {
    System.out.println("getFacilities");
    int[] expResult = null;
    int[] result = AbstractRoom.getFacilities();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
}
