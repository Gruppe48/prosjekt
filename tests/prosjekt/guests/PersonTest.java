/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.guests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kristoffer
 */
public class PersonTest {
  
  public PersonTest() {
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
   * Test of getID method, of class AbstractGuest.
   */
  @Test
  public void testGetID() {
    System.out.println("getID");
    Person instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    int expResult = 0;
    int result = instance.getID();
    assertEquals(expResult, result);
  }

  /**
   * Test of getFirstName method, of class AbstractGuest.
   */
  @Test
  public void testGetFirstName() {
    System.out.println("getFirstName");
    Person instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    String expResult = "Even";
    String result = instance.getFirstName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLastName method, of class AbstractGuest.
   */
  @Test
  public void testGetLastName() {
    System.out.println("getLastName");
    AbstractGuest instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    String expResult = "Augdal";
    String result = instance.getLastName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getPhoneNumber method, of class AbstractGuest.
   */
  @Test
  public void testGetPhoneNumber() {
    System.out.println("getPhoneNumber");
    AbstractGuest instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    String expResult = "12345678";
    String result = instance.getPhoneNumber();
    assertEquals(expResult, result);
  }

  /**
   * Test of getAddress method, of class AbstractGuest.
   */
  @Test
  public void testGetAddress() {
    System.out.println("getAddress");
    AbstractGuest instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    String expResult = "Skoleveien 1";
    String result = instance.getAddress();
    assertEquals(expResult, result);
  }

  /**
   * Test of getPostNumber method, of class AbstractGuest.
   */
  @Test
  public void testGetPostNumber() {
    System.out.println("getPostNumber");
    AbstractGuest instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    String expResult = "1002";
    String result = instance.getPostNumber();
    assertEquals(expResult, result);
  }

  /**
   * Test of validate method, of class AbstractGuest.
   */
  @Test
  public void testValidate() {
    System.out.println("validate");
    AbstractGuest instance = new Person("Even", "Augdal", "12345678", "Skoleveien 1", "1002");
    boolean expResult = true;
    boolean result = instance.validate();
    assertEquals(expResult, result);
  }
}
