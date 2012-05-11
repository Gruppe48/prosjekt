/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.rtf.RTFEditorKit;
import prosjekt.utils.Utils;

/**
 *
 * @author Even
 */
public class InformationPanelGUI {
  private JPanel panelContainer, panelMenu, panelMain;
  private ActionListener buttonListener;
  private JButton homeButton, facilitiesButton, foodButton;
  private JEditorPane display;
  private final String ROOT_PATH = "assets/guests/";
  private JButton saveButton;
  private String currentFile = "index.rtf";
  
  public InformationPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    buttonListener = new ButtonListener();
    
    panelContainer = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    panelContainer.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    homeButton        = new JButton("Hjem");
    facilitiesButton  = new JButton("Fasiliteter");
    foodButton  = new JButton("Mat & Drikke");
    homeButton.addActionListener(buttonListener);
    facilitiesButton.addActionListener(buttonListener);
    foodButton.addActionListener(buttonListener);
    panelMenu.add(homeButton);
    panelMenu.add(facilitiesButton);
    panelMenu.add(foodButton);
    
    // Place MENU
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.HORIZONTAL;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 0;
    c.weighty = 0;
    panelContainer.add(panelMenu, c);
    
    // MAIN PANEL
    panelMain  = new JPanel(new GridBagLayout());
    panelMain.setBackground(Color.LIGHT_GRAY);
    panelMain = home(panelMain);
   

    //Place MAIN PANEL
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridx   = 1;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panelContainer.add(panelMain, c);
  }
  
  public JPanel getPanel() {
    return panelContainer;
  }
  
  private JPanel home(JPanel panel) {
    
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    
    // Create display
    display = new JEditorPane();
    display.setContentType("text/rtf");
    display.setEditable(true);
    display.setEditorKit(new RTFEditorKit());
    display.setText(Utils.read(ROOT_PATH + "index.rtf")); 
    //TODO: Her kunne vi evt. bruke EditorKit.read() men jeg hadde allerede denne metoden i Utils.
    //http://docs.oracle.com/javase/6/docs/api/javax/swing/text/EditorKit.html#read(java.io.InputStream, javax.swing.text.Document, int)
    
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth =  0;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(display, c);
    
    
    saveButton = new JButton("Lagre endringer");
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.NONE;
    c.anchor  = GridBagConstraints.LAST_LINE_END;
    c.gridwidth =  0;
    c.gridx   = 6;
    c.gridy   = 1;
    c.weightx = 1;
    c.weighty = 0;
    panel.add(saveButton, c);
    saveButton.addActionListener(buttonListener);
    
    
    
    return panel;
  }
  
  
  
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == homeButton) {
        currentFile = "index.rtf";
        display.setText(Utils.read(ROOT_PATH + currentFile));
      }
      else if (e.getSource() == facilitiesButton) {
        currentFile = "facilities.rtf";
        display.setText(Utils.read(ROOT_PATH + currentFile));
        
      }
      else if (e.getSource() == foodButton) {
        currentFile = "restaurant.rtf";
        display.setText(Utils.read(ROOT_PATH + currentFile));
      }
      else if (e.getSource() == saveButton) {
        try {
          display.getEditorKit().write(new FileOutputStream(new File(ROOT_PATH+ currentFile)), display.getDocument(), 0, display.getDocument().getLength());
        } catch (Exception err) {
          err.printStackTrace();
        }
      }
    }
  }
  
}
