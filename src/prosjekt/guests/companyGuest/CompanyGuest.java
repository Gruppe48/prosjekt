/*
 */
package prosjekt.guests.companyGuest;

import prosjekt.guests.AbstractGuest;


/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class CompanyGuest extends AbstractGuest implements ICompany {
  
  private String companyName;
  
  public CompanyGuest(String fName, String lName, int phoneNr, String companyName) {
    super(fName, lName, phoneNr);
    this.companyName = companyName;
  }
  
  @Override
  public String getCompanyName() {
    return companyName;
  }
}
