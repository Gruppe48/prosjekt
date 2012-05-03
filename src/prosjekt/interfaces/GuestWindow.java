/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.EditorKit;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;
import prosjekt.Main;

/**
 *
 * @author kristoffer
 */
public class GuestWindow extends GenericWindow implements HyperlinkListener {
  JButton facilities, restaurant;
  JPanel buttons, contentarea;
  JEditorPane output;
  Color uiMainColor;
  public GuestWindow() {
    super("Guest window", 800, 800);
  }

  @Override
  public void create() {
    super.create();
    uiMainColor = (Color)Main.options.get("uiMainColor");
    Container c = getContentPane();
    c.setLayout( new GridBagLayout() );
    
    this.setBackground(uiMainColor);
    buttons = new JPanel();
    contentarea = new JPanel();
    
    output = new JEditorPane();
    output.setDocument(new HTMLDocument());
    output.setContentType("text/html");
    try {
     
      output.read(new FileInputStream(new File("assets/guests/index.html")), null);
    } catch (IOException ex) {
      Logger.getLogger(GuestWindow.class.getName()).log(Level.SEVERE, null, ex);
    }
    output.setEditable(false);
    output.addHyperlinkListener(this);
    
    buttons.setLayout(new GridLayout(20, 1));
    contentarea.setLayout(new GridLayout(1,10));
    
    contentarea.add(output);
    
    facilities = new JButton("Fasiliter");
    restaurant = new JButton("Restaurantmeny"); //TODO: Skrivefeil?
    buttons.add(facilities);
    buttons.add(restaurant);
    
    facilities.addActionListener(buttonListener);
    restaurant.addActionListener(buttonListener);
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
    
    if (e.getSource() == facilities) {
      try {
        output.read(new FileInputStream(new File("assets/guests/facilities.html")), null);
      } catch (IOException ex) {
        Logger.getLogger(GuestWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    else if (e.getSource() == restaurant) {
      try {
        output.read(new FileInputStream(new File("assets/guests/restaurant.html")), null);
      } catch (IOException ex) {
        Logger.getLogger(GuestWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  public void hyperlinkUpdate(HyperlinkEvent event) {
    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      try {
        output.setPage(event.getURL());
      } catch(IOException ioe) {
        warnUser("Can't follow link to " 
                 + event.getURL().toExternalForm() + ": " + ioe);
      }
    }
  }
  
  private void warnUser(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", 
                                  JOptionPane.ERROR_MESSAGE);
  }
}
