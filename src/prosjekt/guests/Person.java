/*
 */
package prosjekt.guests;


/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class Person extends AbstractGuest implements IPerson {
  
  /*
   * Constructor for Person
   * 
   * Initializes the new Company object with the values provided.
   * 
   * @param String firstName
   * @param String lastName
   * @param String phoneNumber
   * @param String address
   * @param int postNumber
   */
  public Person (String firstName, String lastName, String phoneNumber, String address, String postNumber) { 

    super(firstName, lastName, phoneNumber, address, postNumber);
    
  }

  
}
