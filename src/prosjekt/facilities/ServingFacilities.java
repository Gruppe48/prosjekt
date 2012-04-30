/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
    
    @Override
    public String readMenu(String filename) {
      File file = new File(filename);
      StringBuilder content = new StringBuilder();
      BufferedReader reader = null;
      
      try {
        reader = new BufferedReader(new FileReader(file));
        String text = null;
        
        int counter = 0;
        while ((text = reader.readLine()) != null) {
          
          if (counter == 0) {
          content.append(text).append(" "); 
          }
          
          else if (counter % 2 == 0 ) {
            content.append("\n");
          }
          
          else {
            content.append(text).append(" ");
          }   
        }
      }
      
      catch (Exception e) {     
      } 
      
      return content.toString();
    }
  
}
