/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author kristoffer
 */
public class AdminWindow extends GenericWindow {
  JTextField username;
  JPasswordField password;
  JButton login, cancel;
  
  public AdminWindow() {
    super("Admin window", 800, 800);
  }

  @Override
  public void create() {
    super.create();
    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
  
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
