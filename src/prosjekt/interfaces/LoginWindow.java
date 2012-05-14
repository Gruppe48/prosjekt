package prosjekt.interfaces;

import prosjekt.interfaces.guest.GuestWindow;
import prosjekt.interfaces.admin.AdminWindow;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * This is the very simple LoginWindow class.
 * This class defines how our login window looks like.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class LoginWindow extends GenericWindow {
  /**
   * The username field
   */
  JTextField usernameField;
  /**
   * The password field
   */
  JPasswordField passwordField;
  /**
   * The two buttons, login and exit.
   */
  JButton loginButton, exitButton;
  /**
   * Our logo label, that holds the icon.
   * An alternative here would have been to have used
   * JImageIcon instead of a label.
   */
  JLabel logoLabel;
  /**
   * The ImageIcon to hold the hotel logo itself.
   */
  ImageIcon logo;
  
  /**
   * This is the LoginWindow constructor.
   * It calls its superclass (GenericWindow) with a window title,
   * and the window dimensions.
   */
  public LoginWindow() {
    super("Login", 400, 200);
  }

  @Override
  public void create() {
    // Notify our overlord!
    super.create();
    
    try {
      // Load the logo from our assets folder.
      logo = new ImageIcon("assets/avatar.gif");
    }
    catch (Exception e) {
      System.err.println("Logo finnes ikke!");
    }
    
    // Setup the basic container.
    
    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
    
    // Create the label and fields.
    logoLabel = new JLabel(logo);
    usernameField = new JTextField(10);
    passwordField = new JPasswordField(10);
    
    // Create Buttons
    loginButton      = new JButton("Login");
    exitButton     = new JButton("Exit");
    
    // Add the actionListener to our buttons.
    loginButton.addActionListener(buttonListener);
    exitButton.addActionListener(buttonListener);
    
    // Add everything to the Container c.
    c.add(logoLabel);
    c.add(usernameField);
    c.add(passwordField);
    c.add(loginButton);
    c.add(exitButton);
  }

  @Override
  public void destroy() {
    super.destroy();
    // Do some cleaning up here.
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    
    // A very basic login mechanism, the user/pass combo of admin/admin gives you the admin window, gjest/gjest gives you the guest window.
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
