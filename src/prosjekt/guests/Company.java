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
  
  private String companyName;
  
  public Company(String firstName, String lastName, String phoneNumber, String address, int postNumber, String companyName) {
    super(firstName, lastName, phoneNumber, address, postNumber);
    this.companyName = companyName;
  }
  
  @Override
  public String getCompanyName() {
    return companyName;
  }
}
