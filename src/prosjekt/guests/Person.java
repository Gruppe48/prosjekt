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
  
  
  public Person (String firstName, String lastName, String phoneNumber, String address, int postNumber) { 

    super(firstName, lastName, phoneNumber, address, postNumber);
    
  }

  
}
