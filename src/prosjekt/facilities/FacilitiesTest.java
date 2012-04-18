/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.facilities;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Dobbelmoral
 */
public class FacilitiesTest {
  public static void main(String[] args) {
    ServingFacilities bar = new ServingFacilities("Resturant","blabla","10-16");
    
    JOptionPane.showMessageDialog(null,bar.getName());
    
    bar.readMenu("meny.txt");
    
    JTextArea panel = new JTextArea(10,20);
    panel.append(bar.getMenu());
    
    JOptionPane.showMessageDialog(null,panel);
    
  }
  
}
