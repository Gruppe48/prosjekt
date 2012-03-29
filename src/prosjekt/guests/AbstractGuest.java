/*
 */
package prosjekt.guests;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class AbstractGuest implements IGuest {

  public AbstractGuest() {
  }

  public static String getAddress() {
    return address;
  }

  public static String getCity() {
    return city;
  }

  public static String getFirstName() {
    return firstName;
  }

  public static int getGuestID() {
    return guestID;
  }

  public static String getLastName() {
    return lastName;
  }

  public static String getMiddleName() {
    return middleName;
  }

  public static int getPostNumber() {
    return postNumber;
  }
  
}
