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

  public static int guestID = 0; 
  public String firstName = "";
  public String middleName = "";
  public String lastName = "";
  
  public String address = "";
  public int postNumber = 1337;
  public String city = "";
  
  public AbstractGuest() {
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public int getGuestID() {
    return guestID;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public String getMiddleName() {
    return middleName;
  }

  @Override
  public int getPostNumber() {
    return postNumber;
  }
  
}
