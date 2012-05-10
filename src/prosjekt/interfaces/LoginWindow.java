/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author kristoffer
 */
public class LoginWindow extends GenericWindow {
  JTextField username;
  JPasswordField password;
  JButton login, cancel;
  JLabel logoLabel;
  ImageIcon logo;
  
  public LoginWindow() {
    super("Login", 400, 200);
  }

  @Override
  public void create() {
    super.create();
    try {
      logo = new ImageIcon(getClass().getResource("/assets/avatar.gif"));
    }
    catch (Exception e) {
      System.err.println("Logo finnes ikke!");
    }
    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
    logoLabel = new JLabel(logo);
    username = new JTextField(10);
    password = new JPasswordField(10);
    // Create Buttons
    login      = new JButton("Login");
    cancel     = new JButton("Exit");
    
    login.addActionListener(buttonListener);
    cancel.addActionListener(buttonListener);
    c.add(logoLabel);
    c.add(username);
    c.add(password);
    c.add(login);
    c.add(cancel);
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    
    if (e.getSource().equals(login)) {
      //TODO: Noe mer fancy?
      String user = username.getText();
      String pass = new String(password.getPassword());
      if (!user.equals("") && !pass.equals("")) {
        // Vi har bruker og passord.
        if (user.equals("admin") && pass.equals("admin")) {
          //TODO: Gjør noe.
          setVisible(false);
          new AdminWindow2();
        }
        if (user.equals("gjest") && pass.equals("gjest")) {
          //TODO: Gjør noe annet
          setVisible(false);
          new GuestWindow();
        }
      }
    }
    
  }
  
  

}
