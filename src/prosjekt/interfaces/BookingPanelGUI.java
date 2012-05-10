/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import prosjekt.Main;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Company;

/**
 *
 * @author Even
 */
public class BookingPanelGUI {
  private JPanel panelContainer, panelMenu, panelMain;
  private ActionListener btnListener;
  private JButton btnNewBooking, btnShowBookings, btnSearch, btnNew, btnLookup;
  private JTextField txtFirstname, txtLastname, txtPhoneNumber, txtAddress, txtPostNumber, txtCompanyName;
  private ArrayList<AbstractGuest> arrListResults;
  private String[][] rowData;
  private String[] columnNames;
  private SearchTableModel tableModel;
  private JTable tableSearchResults;
  
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
    btnShowBookings = new JButton("Vis alle");
    panelMenu.add(btnNewBooking);
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
    leavingDate.setBackground(Color.LIGHT_GRAY);
    
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
  
  
  private JPanel showAllBookings(JPanel panel) {
    return panel;
  }
  
  private void findGuest() {
    JPanel panel = new JPanel(new GridBagLayout());
    
    // Inputpanel
    JPanel inputPanel = new JPanel(new GridLayout(3,4));
    txtFirstname   = new JTextField(10);
    txtLastname    = new JTextField(10);
    txtPhoneNumber = new JTextField(10);
    txtAddress     = new JTextField(10);
    txtPostNumber  = new JTextField(10);
    txtCompanyName = new JTextField(10);
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
    
    btnLookup = new JButton("Søk");
    btnLookup.addActionListener(btnListener);
    c.insets      = new Insets(0,0,0,0);
    c.fill        = GridBagConstraints.RELATIVE;
    c.anchor      = GridBagConstraints.LINE_END;
    c.gridwidth   = 1;
    c.gridheight  = 1;
    c.gridx       = 9;
    c.gridy       = 1;
    c.weightx     = 1;
    c.weighty     = 0.05;
    panel.add(btnLookup, c);
    
    // Create table
    columnNames = new String[]{"Fornavn", "Etternavn", "Telefon", "Postnummer", "Addresse", "Company"};
    rowData = null;        
    tableModel = new SearchTableModel(rowData, columnNames);
    tableSearchResults = new JTable(tableModel);
    tableSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    // New constraints
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.BOTH;
    c.anchor    = GridBagConstraints.LINE_START;
    c.gridwidth = 10;
    c.gridx     = 0;
    c.gridy     = 2;
    c.weightx   = 1;
    c.weighty   = 1;
    
          
    // Lets create and fill rowData
    if(arrListResults != null) {
      rowData = new String[arrListResults.size()][6];
      int i = 0;
      for (AbstractGuest g : arrListResults) {
        rowData[i][0] = g.getFirstName();
        rowData[i][1] = g.getLastName();
        rowData[i][2] = g.getPhoneNumber();
        rowData[i][3] = g.getPostNumber() + "";
        rowData[i][4] = g.getAddress();
        if(g instanceof Company) {
          Company company = (Company) g;
          rowData[i][5] = company.getCompanyName();
        }
        i++;
      }
    }

    // Tablemodel for our JTable
    tableModel = new SearchTableModel(rowData, columnNames);
    tableSearchResults.setModel(tableModel);
    
    // add guestPanelSearchResults (JTable) to panel
    panel.add(new JScrollPane(tableSearchResults), c);
    
    Frame frame = new JFrame("Søk etter gjest");
    /*
    frame.add(panel);
    JDialog d = new JDialog(frame, "test");
    d.setVisible(true);
    * 
    */
    //JDialog window = new JDialog(frame);
    //JOptionPane.showMessageDialog(frame, panel);
    JOptionPane.showMessageDialog(frame, panel);
  }
  
   private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnNewBooking) {
        panelMain.removeAll();
        panelMain = newBooking(panelMain);
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
      else if (e.getSource() == btnLookup) {
        String firstname    = txtFirstname.getText();
        String lastname     = txtLastname.getText();
        String phoneNumber  = txtPhoneNumber.getText();
        String address      = txtAddress.getText();
        String companyName  = txtCompanyName.getText();

        try {
          int postNumber = 0;

          if(!txtPostNumber.getText().equals("")) {
            postNumber = Integer.parseInt(txtPostNumber.getText());
          }
          
          arrListResults = Main.guestRegistry.searchGuests(firstname, lastname, phoneNumber, address, postNumber, companyName);
          
          // Lets create and fill rowData
          if(arrListResults != null) {
            rowData = new String[arrListResults.size()][6];
            int i = 0;
            for (AbstractGuest g : arrListResults) {
              rowData[i][0] = g.getFirstName();
              rowData[i][1] = g.getLastName();
              rowData[i][2] = g.getPhoneNumber();
              rowData[i][3] = g.getPostNumber() + "";
              rowData[i][4] = g.getAddress();
              if(g instanceof Company) {
                Company c = (Company) g;
                rowData[i][5] = c.getCompanyName();
              }
              i++;
            }
          }
          
          // Tablemodel for our JTable
          tableModel = new SearchTableModel(rowData, columnNames);
          tableSearchResults.setModel(tableModel);
        }
        catch(NumberFormatException nfe) {
          System.out.println("error! NumberFormatException");
        }
      }
    }
   }
  
}
