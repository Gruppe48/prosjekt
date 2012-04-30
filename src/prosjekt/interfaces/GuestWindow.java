/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author kristoffer
 */
public class GuestWindow extends GenericWindow {
  JButton facilities, restaurant;
  JPanel buttons, contentarea;
  JTextArea output;
  
  public GuestWindow() {
    super("Guest window", 800, 800);
  }

  @Override
  public void create() {
    super.create();
    Container c = getContentPane();
    c.setLayout( new GridBagLayout() );
    
    
    buttons = new JPanel();
    contentarea = new JPanel();
    output = new JTextArea(10, 10);
    buttons.setLayout(new GridLayout(20, 1));
    contentarea.setLayout(new GridLayout(1,10));
    
    contentarea.add(output);
    
    facilities = new JButton("Fasiliter");
    restaurant = new JButton("Restaurantmeny");
    buttons.add(facilities);
    buttons.add(restaurant);
    
    GridBagConstraints g = new GridBagConstraints();
    // Buttons
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.HORIZONTAL;
    g.anchor = GridBagConstraints.FIRST_LINE_START;
    g.gridx = 0;
    g.gridy = 0;
    g.weightx = 0;
    g.weighty = 0;
    c.add(buttons, g);
    // Output
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.BOTH;
    g.anchor = GridBagConstraints.PAGE_START;
    g.gridx = 1;
    g.gridy = 0;
    g.weightx = 1;
    g.weighty = 1;
    c.add(contentarea, g);
 
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);    
  }
}
