package prosjekt.guests;

/**
 *
 * This is the base abstract superclass for our guest classes.
 * It holds the basic implementations of most methods and variables needed,
 * such as simple getters and basic validation.
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class AbstractGuest implements IGuest {
  /**
   * Counter to keep track of what then next guestID will be.
   */
  private static int guestCounter = 0;
  /**
   * The guest's ID
   */
  private int guestID;
  /**
   * The guest's first name
   */
  private String firstName;
  /**
   * The guest's last name
   */
  private String lastName;
  /**
   * The guest's phone number
   */
  private String phoneNumber;
  /**
   * The guest's address
   */
  private String address;
  /**
   * The guest's post number
   */
  private String postNumber;
  /**
   * A StringBuilder buffer to hold validation errors.
   */
  private StringBuilder errors = new StringBuilder();
  
  /**
   * Constructor for AbstractGuest
   * 
   * Initializes the new AbstractGuest object with the values provided.
   * 
   * @param firstName
   * @param lastName
   * @param phoneNumber
   * @param address
   * @param postNumber
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

  
  /**
   * This method returns a printable String for this guest.
   * 
   * @return Printable string with guest information.
   */
  @Override
  public String toString() {
    return "GjesteID: " + guestID + "\n" +
            "Navn: " + firstName + " " + lastName + "\n" + 
            "Telefonnummer: " + phoneNumber + "\n" +
            "Addresse: " + postNumber + " " + address + "\n"; 
  }

  @Override
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

  @Override
  public String getErrors() {
    return errors.toString();
  }
}
