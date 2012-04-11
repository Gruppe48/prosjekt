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
  private String address;
  private int postNumber;
  
  public PrivateGuest (String firstName, String lastName, int phoneNumber, String address, int postNumber, String c) { 
    //TODO: Hva faen er c?
    super(firstName, lastName, phoneNumber);
    this.address = address;
    this.postNumber = postNumber;
  }

  
}
