package prosjekt.guests;

/**
 * This is the class for our Company object.
 * The Company object represents all Company guests (i.e not a private/person guest).
 * All methods exclusive to Company are defined in the ICompany interface.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * @date Mar 29, 2012
 */
public class Company extends AbstractGuest implements ICompany {
  
  /**
   * Holds the companyName
   */
  private String companyName;
  
  /**
   * Constructor for Company
   * 
   * Initializes the new Company object with the values provided.
   * 
   * @param firstName
   * @param lastName
   * @param phoneNumber
   * @param address
   * @param postNumber
   * @param companyName
   */
  public Company(String firstName, String lastName, String phoneNumber, String address, String postNumber, String companyName) {
    super(firstName, lastName, phoneNumber, address, postNumber);
    this.companyName = companyName;
  }
  
  @Override
  public String getCompanyName() {
    return companyName;
  }
  
  /**
   * 
   * This method generates a printable string with the Company object's
   * information.
   * 
   * @return Printable String of Company information.
   */
  @Override
  public String toString() {
    return super.toString() +
            "Company: " + companyName + "\n";
  }
}
