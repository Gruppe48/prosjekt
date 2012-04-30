/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

/**
 *
 * @author Dobbelmoral
 */
public interface IFacilities {
  
  public String getName();
  
  public String getDescription();
  
  public String getOpeningHours();
  
  public void setName(String name);
  
  public void setDescription(String about);
  
  public void setOpeningHours(String openingHours);
  
}
