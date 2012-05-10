/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import prosjekt.Main;

/**
 *
 * @author Even
 */
public class BookingPanelGUI {
  private JPanel panelContainer, panelMenu, panelMain;
  private ActionListener btnListener;
  private JButton btnNewBooking, btnFindBooking, btnShowBookings, btnSearch, btnNew;
 
  
  public BookingPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    btnListener = new ButtonListener();
    
    // Lets make this panel
    panelContainer = bookingPanel();
  }
  
  public JPanel getPanel() {
    return panelContainer;
  }
  
  private JPanel bookingPanel() {
    JPanel frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnNewBooking   = new JButton("Ny booking");
    btnFindBooking  = new JButton("Finn booking");
    btnShowBookings = new JButton("Vis alle");
    panelMenu.add(btnNewBooking);
    panelMenu.add(btnFindBooking);
    panelMenu.add(btnShowBookings);
    
    // Place MENU
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.HORIZONTAL;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 0;
    c.weighty = 0;
    frame.add(panelMenu, c);
    
    // MAIN PANEL
    panelMain  = new JPanel(new GridBagLayout());
    panelMain.setBackground(Color.LIGHT_GRAY);
    panelMain = newBooking(panelMain);
   

    //Place MAIN PANEL
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridx   = 1;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    frame.add(panelMain, c);
    
    return frame;
  }
  
  private JPanel newBooking(JPanel panel) {
    
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    
    // Create inputpanel
    JPanel inputPanel = new JPanel(new GridLayout(4,2));
    inputPanel.setBackground(Color.LIGHT_GRAY);
    
    btnSearch = new JButton("Finn gjest");
    btnNew    = new JButton("Ny gjest");
    btnSearch.addActionListener(btnListener);
    btnNew.addActionListener(btnListener);
    inputPanel.add(btnSearch);
    inputPanel.add(btnNew);
    
    String[] roomTypes = Main.roomRegistry.getRoomTypes();
    JComboBox cmbRoomType = new JComboBox(roomTypes);
    inputPanel.add(new JLabel("Velg romtype:"));
    inputPanel.add(cmbRoomType);
    
    JDateChooser arrivalDate = new JDateChooser();
    JDateChooser leavingDate = new JDateChooser();
    arrivalDate.setBackground(Color.LIGHT_GRAY);
    leavingDate.setBackground(Color.LIGHT_GRAY);;
    
    inputPanel.add(new JLabel("Ankomstdato"));
    inputPanel.add(arrivalDate);
    inputPanel.add(new JLabel("Utsjekkingsdato:"));
    inputPanel.add(leavingDate);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.NONE;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth =  0;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 0.2;
    panel.add(inputPanel, c);
    
    
    // Create display
    JTextArea display = new JTextArea(10,50);
    display.setEditable(false);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridwidth =  0;
    c.gridx   = 0;
    c.gridy   = 1;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(display, c);
    
    
    return panel;
  }
  
  private JPanel findBooking(JPanel panel) {
    return panel;
  }
  
  private JPanel showAllBookings(JPanel panel) {
    return panel;
  }
  
  private void findGuest() {
    JPanel panel = new JPanel(new GridBagLayout());
    
    // Inputpanel
    JPanel inputPanel = new JPanel(new GridLayout(3,4));
    JTextField txtFirstname   = new JTextField(10);
    JTextField txtLastname    = new JTextField(10);
    JTextField txtPhoneNumber = new JTextField(10);
    JTextField txtAddress     = new JTextField(10);
    JTextField txtPostNumber  = new JTextField(10);
    JTextField txtCompanyName = new JTextField(10);
    inputPanel.add(new JLabel("Fornavn"));
    inputPanel.add(txtFirstname);
    inputPanel.add(new JLabel("Adresse"));
    inputPanel.add(txtAddress);
    inputPanel.add(new JLabel("Etternavn"));
    inputPanel.add(txtLastname);
    inputPanel.add(new JLabel("Postnummer"));
    inputPanel.add(txtPostNumber);
    inputPanel.add(new JLabel("Telefonnummer"));
    inputPanel.add(txtPhoneNumber);
    inputPanel.add(new JLabel("Bedrift"));
    inputPanel.add(txtCompanyName);
    GridBagConstraints c = new GridBagConstraints();
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.HORIZONTAL;
    c.anchor    = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 10;
    c.gridx     = 0;
    c.gridy     = 0;
    c.weightx   = 1;
    c.weighty   = 0;
    panel.add(inputPanel, c);
    
    btnSearch = new JButton("Søk");
    btnSearch.addActionListener(btnListener);
    c.insets      = new Insets(0,0,0,0);
    c.fill        = GridBagConstraints.RELATIVE;
    c.anchor      = GridBagConstraints.LINE_END;
    c.gridwidth   = 1;
    c.gridheight  = 1;
    c.gridx       = 9;
    c.gridy       = 1;
    c.weightx     = 1;
    c.weighty     = 0.05;
    panel.add(btnSearch, c);
    
    JFrame frame = new JFrame("Søk etter gjest");
    frame.add(panel);
    JDialog d = new JDialog(frame, "test");
    d.setVisible(true);
    //JDialog window = new JDialog(frame);
    //JOptionPane.showMessageDialog(frame, panel);
  }
  
   private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnNewBooking) {
        panelMain.removeAll();
        panelMain = newBooking(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnFindBooking) {
        panelMain.removeAll();
        panelMain = findBooking(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnShowBookings) {
        panelMain.removeAll();
        panelMain = showAllBookings(panelMain);
        panelMain.updateUI();
      }
      else if (e.getSource() == btnSearch) {
        findGuest();
      }
    }
   }
  
}
