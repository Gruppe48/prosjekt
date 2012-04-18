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
  
  /* Information description the facility. 
   * I picture description and menu to just be bulks of text that can be edited as
   * they don't serve any other purpose other than information 
   */
  
  private String name;
  private String description;
  private String openingHours;
  
  public AbstractFacilities(String name,String description,String openingHours) {
    this.name = name;
    this.description = description;
    this.openingHours = openingHours;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String getDescription() {
    return description;
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
  public void setDescription(String description) {
    this.description = description;
  }
  
  @Override
  public void setOpeningHours(String openingHours) {
    this.openingHours = openingHours;
  }
  
}
