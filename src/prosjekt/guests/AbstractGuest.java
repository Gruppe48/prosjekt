package prosjekt.guests;

import prosjekt.Main;
import prosjekt.rooms.types.ConferenceRoom;
import prosjekt.rooms.types.DoubleRoom;
import prosjekt.rooms.types.MeetingRoom;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class AbstractGuest implements IGuest {
  /*
   * Counter to keep track of what then next guestID will be.
   */
  private static int guestCounter = 0;
  /*
   * The guest's ID
   */
  private int guestID;
  /*
   * The guest's first name
   */
  private String firstName = "";
  /*
   * The guest's last name
   */
  private String lastName = "";
  /*
   * The guest's phone number
   */
  private String phoneNumber = "";
  /*
   * The guest's address
   */
  private String address = "";
  /*
   * The guest's post number
   */
  private String postNumber = "";
  private StringBuilder errors = new StringBuilder();
  
  /*
   * Constructor for AbstractGuest
   * 
   * Initializes the new AbstractGuest object with the values provided.
   * 
   * @param firstName
   * @param lastName
   * @param phoneNumber
   */
  public AbstractGuest(String firstName, String lastName, String phoneNumber, String address, String postNumber) {
    guestID = guestCounter++;
    setFirstName(firstName);
    setLastName(lastName);
    setPhoneNumber(phoneNumber);
    setAddress(address);
    setPostNumber(postNumber);
  }
  @Override
  public final boolean setAddress(String address) {
    //TODO: Validation?
    this.address = address;
    return true;
  }

  @Override
  public final boolean setFirstName(String firstName) {
    this.firstName = firstName;
    return true;
  }

  @Override
  public final boolean setLastName(String lastName) {
    this.lastName = lastName;
    return true;
  }

  @Override
  public final boolean setPhoneNumber(String phoneNumber) {
    if (phoneNumber.matches("\\d{8}")) {
      this.phoneNumber = phoneNumber;
      return true;
    }
    return false;
  }

  @Override
  public final boolean setPostNumber(String postNumber) {
    if (postNumber.matches("[0123456789]{4,4}")) {
      this.postNumber = postNumber;
      return true;
    }
    return false;
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
  
  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  @Override
  public String getAddress() {
    return address;
  }
  @Override
  public String getPostNumber() {
    return postNumber;
  }

  
  @Override
  public String toString() {
    return "GjesteID: " + guestID + "\n" +
            "Navn: " + firstName + " " + lastName + "\n" + 
            "Telefonnummer: " + phoneNumber + "\n" +
            "Addresse: " + postNumber + " " + address + "\n"; 
  }

  public boolean validate() {
    boolean status = true;
    if (!phoneNumber.matches("\\d{8}")) {
      errors.append("Phone number is not valid!");
      errors.append("\n");
      status = false;
    }
    if (!postNumber.matches("\\d{4}")) {
      errors.append("Post number is not valid!");
      errors.append("\n");
      status = false;
    }
    return status;
  }

  public String getErrors() {
    return errors.toString();
  }
}
