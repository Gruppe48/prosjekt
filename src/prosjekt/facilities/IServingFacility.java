/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

/**
 *
 * @author Dobbelmoral
 */
public interface IServingFacility {
  
  public String getMenu();
  
  public void setMenu(String menu);
  
  public void readMenu(String filename);
  
}
