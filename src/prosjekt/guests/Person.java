/*
 */
package prosjekt.guests;

import prosjekt.guests.AbstractGuest;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class Person extends AbstractGuest implements IPerson {
  private String address;
  private int postNumber;
  
  public Person (String firstName, String lastName, int phoneNumber, String address, int postNumber, String c) { 
    //TODO: Hva faen er c?
    super(firstName, lastName, phoneNumber);
    this.address = address;
    this.postNumber = postNumber;
  }

  
}
