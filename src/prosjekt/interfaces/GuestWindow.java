/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import prosjekt.Main;
import prosjekt.utils.Utils;

/**
 *
 * @author kristoffer
 */
public class GuestWindow extends GenericWindow {
  private JButton homeButton, facilitiesButton, restaurantButton;
  private JPanel buttonPanel, contentPanel;
  private JTextPane contentPane;
  private Color uiMainColor;
  private final String ROOT_PATH = "assets/guests/";
  
  public GuestWindow() {
    super("Guest window", 600, 400);
  }

  @Override
  public void create() {
    super.create();
    uiMainColor = (Color)Main.options.get("uiMainColor");
    Container c = getContentPane();
    c.setLayout( new GridBagLayout() );
    
    this.setBackground(uiMainColor);
    buttonPanel = new JPanel();
    contentPanel = new JPanel();
    
    contentPane = new JTextPane();
    contentPane.setContentType("text/rtf");
    contentPane.setText(Utils.read(ROOT_PATH+"index.rtf"));
    
    contentPane.setEditable(false);
    
    buttonPanel.setLayout(new GridLayout(20, 1));
    contentPanel.setLayout(new GridLayout(1,10));
    
    contentPanel.add(contentPane);
    
    homeButton = new JButton("Hjem");
    facilitiesButton = new JButton("Fasiliter");
    restaurantButton = new JButton("Restaurantmeny");
    buttonPanel.add(homeButton);
    buttonPanel.add(facilitiesButton);
    buttonPanel.add(restaurantButton);
    
    homeButton.addActionListener(buttonListener);
    facilitiesButton.addActionListener(buttonListener);
    restaurantButton.addActionListener(buttonListener);
    GridBagConstraints g = new GridBagConstraints();
    // Buttons
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.HORIZONTAL;
    g.anchor = GridBagConstraints.FIRST_LINE_START;
    g.gridx = 0;
    g.gridy = 0;
    g.weightx = 0;
    g.weighty = 0;
    c.add(buttonPanel, g);
    // Output
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.BOTH;
    g.anchor = GridBagConstraints.PAGE_START;
    g.gridx = 1;
    g.gridy = 0;
    g.weightx = 1;
    g.weighty = 1;
    
    c.add(contentPanel, g);
 
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    
    if (e.getSource() == homeButton) {
      contentPane.setText(Utils.read(ROOT_PATH+"index.rtf"));
    }
    else if (e.getSource() == facilitiesButton) {
      contentPane.setText(Utils.read(ROOT_PATH+"facilities.rtf"));
    }
    else if (e.getSource() == restaurantButton) {
      contentPane.setText(Utils.read(ROOT_PATH+"restaurant.rtf"));
    }
  }
}
