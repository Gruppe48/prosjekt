/*
 */
package prosjekt.guests;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public interface IGuest {
  /*
   * @return The guests ID
   */
  public int    getID();
  /*
   * @return the guest's first name
   */
  public String getFirstName();
  /*
   * @return the guest's last name
   */
  public String getLastName();
  
  /*
   * @return the guest's phone number
   */
  public String getPhoneNumber();
  
  /*
   * @return the guest's address
   */
  public String getAddress();
  
  /*
   * @return the guest's post number
   */
  public String getPostNumber();
  
  /*
   * @return Returns the errors.
   */
  public String getErrors();
  
}

