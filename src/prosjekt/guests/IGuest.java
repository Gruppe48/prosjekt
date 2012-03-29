/*
 */
package prosjekt.guests;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public interface IGuest {
  public static int guestID = 0; 
  public String firstName = "";
  public String middleName = "";
  public String lastName = "";
  
  public String address = "";
  public int postNumber = 1337;
  public String city = "";
}
