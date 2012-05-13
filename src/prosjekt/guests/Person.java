package prosjekt.guests;


/**
 * This is the class for our Person object.
 * The person object represents all private guests (i.e not a company guest).
 * All methods exclusive to Person are defined in the IPerson interface.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * @date Mar 29, 2012
 */
public class Person extends AbstractGuest implements IPerson {
  
  /**
   * Constructor for Person
   * 
   * Initializes the new Company object with the values provided.
   * 
   * @param firstName
   * @param lastName
   * @param phoneNumber
   * @param address
   * @param postNumber
   */
  public Person (String firstName, String lastName, String phoneNumber, String address, String postNumber) { 

    super(firstName, lastName, phoneNumber, address, postNumber);
    
  } 
}
