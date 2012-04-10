/*
 */
package prosjekt.guests.privateGuest;

import prosjekt.guests.AbstractGuest;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class PrivateGuest extends AbstractGuest implements IPrivate {
  
  private String firstName = "";
  private String lastName = "";
  
  private String address = "";
  private int postNumber = 1337;
  private String city = "";
         
  public PrivateGuest (String fName, String lName, String addr, int postNr, String c) { 
    firstName   = fName;
    lastName    = lName;
    address     = addr;
    postNumber  = postNr;
    city        = c;
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
  public String getLastName() {
    return lastName;
  }

  @Override
  public int getPostNumber() {
    return postNumber;
  }
  
}
