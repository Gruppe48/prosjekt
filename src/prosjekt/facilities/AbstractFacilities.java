/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

/**
 *
 * @author Dobbelmoral
 */
public abstract class AbstractFacilities implements IFacilities{
  
  /* Information discription the facility. 
   * I picture discription and menu to just be bulks of text that can be edited as
   * they don't serve any other purpose other than information 
   */
  
  private String name;
  private String discription;
  private String openingHours;
  
  public AbstractFacilities(String name,String discription,String openingHours) {
    this.name = name;
    this.discription = discription;
    this.openingHours = openingHours;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String getDiscription() {
    return discription;
  }
  
  @Override
  public String getOpeningHours() {
    return openingHours;
  }
  
  
  @Override
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public void setDiscription(String discription) {
    this.discription = discription;
  }
  
  @Override
  public void setOpeningHours(String openingHours) {
    this.openingHours = openingHours;
  }
  
}
