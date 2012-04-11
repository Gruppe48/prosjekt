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
  private static int guestCounter = 0;
  private int guestID;
  private String firstName;
  private String lastName;
  private int    phoneNumber;
  
  public AbstractGuest(String firstName, String lastName, int phoneNumber) {
    guestID = guestCounter++;
    // Død over dumme variabelnavn.
    this.firstName   = firstName;
    this.lastName    = lastName;
    this.phoneNumber = phoneNumber;
  }
  @Override
  public int getID() {
    return guestID;
  }
  
  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }
  
}
