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
   * @param name First name to set.
   * @return true if it succeeds, false if it fails.
   */
  public boolean setFirstName(String name);
  /*
   * @param name last name to set.
    * @return true if it succeeds, false if it fails.
   */
  public boolean setLastName(String name);
  
  /*
   * @return number the phone number to set.
   * @return true if it succeeds, false if it fails.
   */
  public boolean setPhoneNumber(String number);
  
  /*
   * @param address the address to set.
   * @return true if it succeeds, false if it fails.
   */
  public boolean setAddress(String address);
  
  /*
   * @param number the number to set.
   * @return true if it succeeds, false if it fails.
   */
  public boolean setPostNumber(String number);
  
  /*
   * @return Returns the errors.
   */
  public String getErrors();
  
}

