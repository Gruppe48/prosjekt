/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

import java.io.BufferedReader;
import java.io.File;
<<<<<<< HEAD
import java.io.FileReader;
=======
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
>>>>>>> development

/**
 *
 * @author Dobbelmoral
 */
public class ServingFacility extends AbstractFacility implements IServingFacility {
  
<<<<<<< HEAD
  String menu;
  
    public ServingFacility(String name, String description, String openingHours) {
      super(name,description,openingHours);
      this.menu = menu;
=======
  String menu = null;
  
    public ServingFacility(String name, String description, String openingHours) {
    super(name,description,openingHours);
>>>>>>> development
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
<<<<<<< HEAD
    public String readMenu(String filename) {
      File file = new File(filename);
      StringBuilder content = new StringBuilder();
      BufferedReader reader = null;
=======
    public void readMenu(String filename) {
      File file = new File(filename);
      StringBuilder content = new StringBuilder();
      BufferedReader reader = null;
      String newLine = System.getProperty("line.separator");
      int counter = 0;
>>>>>>> development
      
      try {
        reader = new BufferedReader(new FileReader(file));
        String text = null;
        
<<<<<<< HEAD
        int counter = 0;
=======
>>>>>>> development
        while ((text = reader.readLine()) != null) {
          
          if (counter == 0) {
          content.append(text).append(" "); 
          }
          
          else if (counter % 2 == 0 ) {
<<<<<<< HEAD
            content.append("\n");
=======
            content.append(newLine).append(text).append(" ");
>>>>>>> development
          }
          
          else {
            content.append(text).append(" ");
<<<<<<< HEAD
          }   
        }
      }
      
      catch (Exception e) {     
      } 
      
      return content.toString();
=======
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
>>>>>>> development
    }
  
}
