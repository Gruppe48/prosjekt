/*
 */
package prosjekt.guests;

/**
 *
 * This is the interface for AbstractGuest
 * It defines the methods implemented in AbstractGuest.
 * All other guests extend AbstractGuest.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public interface IGuest {
  /**
   * guestID getter
   * @return The guests ID
   */
  public int    getID();
  /**
   * firstName getter
   * @return the guest's first name
   */
  public String getFirstName();
  /**
   * lastName getter
   * @return the guest's last name
   */
  public String getLastName();
  
  /**
   * phoneNumber getter
   * @return the guest's phone number
   */
  public String getPhoneNumber();
  
  /**
   * address getter.
   * @return the guest's address
   */
  public String getAddress();
  
  /**
   * postNumber getter.
   * @return the guest's post number
   */
  public String getPostNumber();
  
  /**
   * Getter for validation errors.
   * 
   * @return Returns the errors.
   */
  public String getErrors();
  
  /**
   * This method returns true or false based on the result of
   * input validation.
   * If the method returns false call the method getErrors()
   * to see the validation error.
   * 
   * @return true or false based on validation.
   */
  public boolean validate();
}

