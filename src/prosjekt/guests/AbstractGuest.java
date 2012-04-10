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
  private static int guestID = 0; 
  private String firstName;
  private String lastName;
  private int    phoneNumber;
  
  public AbstractGuest(String fName, String lName, int phoneNr) {
    guestID += 1;
    firstName   = fName;
    lastName    = lName;
    phoneNumber = phoneNr;
  }
  
  @Override
  public int getGuestID() {
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
  
}
