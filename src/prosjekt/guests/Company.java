/*
 */
package prosjekt.guests;



/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class Company extends AbstractGuest implements ICompany {
  
  /*
   * Holds the companyName
   */
  private String companyName;
  
  /*
   * Constructor for Company
   * 
   * Initializes the new Company object with the values provided.
   * 
   * @param String firstName
   * @param String lastName
   * @param String phoneNumber
   * @param String address
   * @param int postNumber
   * @param String companyName
   */
  public Company(String firstName, String lastName, String phoneNumber, String address, int postNumber, String companyName) {
    super(firstName, lastName, phoneNumber, address, postNumber);
    this.companyName = companyName;
  }
  
  @Override
  public String getCompanyName() {
    return companyName;
  }
  
  @Override
  public String toString() {
    return super.toString() +
            "Company: " + companyName;
  }
}
