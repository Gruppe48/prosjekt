/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Even
 */
public class InformationPanelGUI {
  private JPanel panelContainer, panelMenu, panelMain;
  private ActionListener btnListener;
  private JButton btnHome, btnFacilities, btnFoodDrinks;
  private JTextArea display;
  
  public InformationPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    btnListener = new ButtonListener();
    
    panelContainer = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    panelContainer.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnHome        = new JButton("Hjem");
    btnFacilities  = new JButton("Fasiliteter");
    btnFoodDrinks  = new JButton("Mat & Drikke");
    btnHome.addActionListener(btnListener);
    btnFacilities.addActionListener(btnListener);
    btnFoodDrinks.addActionListener(btnListener);
    panelMenu.add(btnHome);
    panelMenu.add(btnFacilities);
    panelMenu.add(btnFoodDrinks);
    
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
    display = new JTextArea(10,50);
    display.setEditable(false);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth =  0;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(display, c);
    
    
    JButton btnSave = new JButton("Lagre endringer");
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.NONE;
    c.anchor  = GridBagConstraints.LAST_LINE_END;
    c.gridwidth =  0;
    c.gridx   = 6;
    c.gridy   = 1;
    c.weightx = 1;
    c.weighty = 0;
    panel.add(btnSave, c);
    
    
    
    
    return panel;
  }
  
  
  
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnHome) {
        
      }
      else if (e.getSource() == btnFacilities) {
        
      }
      else if (e.getSource() == btnFoodDrinks) {
        
      }
    }
  }
  
}
