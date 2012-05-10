/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Dobbelmoral
 */
public class ServingFacility extends AbstractFacility implements IServingFacility {
  
  String menu = null;
  
    public ServingFacility(String name, String description, String openingHours) {
    super(name,description,openingHours);
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
    public void readMenu(String filename) {
      File file = new File(filename);
      StringBuilder content = new StringBuilder();
      BufferedReader reader = null;
      String newLine = System.getProperty("line.separator");
      int counter = 0;
      
      try {
        reader = new BufferedReader(new FileReader(file));
        String text = null;
        
        while ((text = reader.readLine()) != null) {
          
          if (counter == 0) {
          content.append(text).append(" "); 
          }
          
          else if (counter % 2 == 0 ) {
            content.append(newLine).append(text).append(" ");
          }
          
          else {
            content.append(text).append(" ");
          }  
          
          counter++;
        }
      }
      
      catch (FileNotFoundException e) {
        System.out.println("Det finnes ingen meny med navnet " + file);
      } 
      
      catch (IOException e) {
        System.out.println("Det skjedde en feil ved lesing av " + file);
      }
      
      menu = content.toString();
    }
  
}
