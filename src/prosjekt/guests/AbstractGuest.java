package prosjekt.guests;

import prosjekt.rooms.types.ConferenceRoom;
import prosjekt.rooms.types.DoubleRoom;
import prosjekt.rooms.types.MeetingRoom;
import prosjekt.rooms.types.SingleRoom;

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
  private String firstName;
  /*
   * The guest's last name
   */
  private String lastName;
  /*
   * The guest's phone number
   */
  private String    phoneNumber;

  /*
   * The guest's address
   */
  private String address;
  /*
   * The guest's post number
   */
  private String postNumber;
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

    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.postNumber = postNumber;
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

  @Override
  public String getErrors() {
    return errors.toString();
  }
}
