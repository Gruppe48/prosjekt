/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

/**
 *
 * @author Dobbelmoral
 */
public class ServingFacilities extends AbstractFacilities implements IServingFacilities {
  
  String menu;
  
    public ServingFacilities(String name, String description, String openingHours, String menu) {
    super(name,description,openingHours);
    this.menu = menu;
    }

    @Override
    public String getMenu() {
      return menu;
    }

    @Override
    public void setMenu(String menu) {
      this.menu = menu;
    }
    
  
}
