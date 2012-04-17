/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author Even
 */
public class AdminWindow2 extends GenericWindow {
  private JTabbedPane tabbedPane;
  private JComponent panelGuest, panel2, panel3;
  private JPanel panel, menu; 
  
  public AdminWindow2() {
    super("Administratorpanel", 900, 600);
  }

  @Override
  public void create() {
    super.create();
    
    tabbedPane = new JTabbedPane();
    
    Container c = getContentPane();
    c.setLayout( new GridLayout(1,1) );
    
    panelGuest = guestPanel();
    tabbedPane.addTab("Gjest", panelGuest);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    panel2 = makeTextPanel("Panel #2");
    tabbedPane.addTab("Rom", panel2);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    panel3 = makeTextPanel("Panel #3");
    tabbedPane.addTab("Fasiliteter", panel3);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

 
    c.add(tabbedPane);
    
    
   
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    
  }

  protected JComponent makeTextPanel(String text) {
        JPanel test = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        test.setLayout(new GridLayout(1, 1));
        test.add(filler);
        return test;
  }
  
  private JComponent guestPanel() {
    JPanel frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // MENU 
    menu = new JPanel(new GridLayout(6,1));
    
    menu.setBackground(Color.LIGHT_GRAY);
    JButton searchGuest = new JButton("Søk");
    JButton newGuest    = new JButton("Ny gjest");
    JButton btn1        = new JButton("Søk");
    JButton btn2        = new JButton("Ny gjest");
    JButton btn3        = new JButton("Søk");
    JButton btn4        = new JButton("Ny gjest");
    
    menu.add(searchGuest);
    menu.add(newGuest);
    menu.add(btn1);
    menu.add(btn2);
    menu.add(btn3);
    menu.add(btn4);
    
    // Place MENU
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.HORIZONTAL;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 0;
    c.weighty = 0;
    frame.add(menu, c);
    
    // PANEL
    panel  = new JPanel(new GridBagLayout());
    panel.setBackground(Color.LIGHT_GRAY);
   

    //Place PANEL
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridx   = 1;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    frame.add(panel, c);
    
    /*JTextField test = new JTextField(10);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.HORIZONTAL;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 1;
    c.weightx = 1;
    c.weighty = 0;
    panel.add(test, c);*/
    
    // Add ActionListeners
    searchGuest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel = searchGuest(panel);
        panel.updateUI();
      }
    });
    newGuest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       //panel = newGuest();
        panel.updateUI();
      }
    });
    
    
    return frame;
    
  }
  
  
  private JPanel searchGuest(JPanel panel) {
    GridBagConstraints c = new GridBagConstraints();
    
    // Display area
    JTextArea display = new JTextArea(30,50);
    display.setForeground(Color.BLACK);
    display.setBackground(Color.WHITE);
    display.setText("HALLA");
    display.setEditable(true);
    JScrollPane scroll = new JScrollPane(display);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(scroll, c);
    
    return panel;
  }
  
  private JPanel newGuest(JPanel panel) {
    return panel;
  }
  

}