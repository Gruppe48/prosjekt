/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import prosjekt.interfaces.guest.GuestWindow;
import prosjekt.interfaces.admin.AdminWindow;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author kristoffer
 */
public class LoginWindow extends GenericWindow {
  JTextField usernameField;
  JPasswordField passwordField;
  JButton loginButton, exitButton;
  JLabel logoLabel;
  ImageIcon logo;
  
  public LoginWindow() {
    super("Login", 400, 200);
  }

  @Override
  public void create() {
    super.create();
    try {
      logo = new ImageIcon("assets/avatar.gif");
    }
    catch (Exception e) {
      System.err.println("Logo finnes ikke!");
    }
    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
    logoLabel = new JLabel(logo);
    usernameField = new JTextField(10);
    passwordField = new JPasswordField(10);
    // Create Buttons
    loginButton      = new JButton("Login");
    exitButton     = new JButton("Exit");
    
    loginButton.addActionListener(buttonListener);
    exitButton.addActionListener(buttonListener);
    c.add(logoLabel);
    c.add(usernameField);
    c.add(passwordField);
    c.add(loginButton);
    c.add(exitButton);
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    
    if (e.getSource().equals(loginButton)) {
      //TODO: Noe mer fancy?
      String user = usernameField.getText();
      String pass = new String(passwordField.getPassword());
      if (!user.equals("") && !pass.equals("")) {
        // Vi har bruker og passord.
        if (user.equals("admin") && pass.equals("admin")) {
          //TODO: Gjør noe.
          setVisible(false);
          new AdminWindow();
        }
        else if (user.equals("gjest") && pass.equals("gjest")) {
          //TODO: Gjør noe annet
          setVisible(false);
          new GuestWindow();
        }
        else {
          JOptionPane.showMessageDialog(this, "Denne brukeren finnes ikke");
          usernameField.setText("");
          passwordField.setText("");
        }
      }
    }
    else if (e.getSource().equals(exitButton)) {
      System.exit(0);
    }
    
  }
  
  

}
