/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces.admin;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import prosjekt.Main;
import prosjekt.booking.BookingEntry;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Company;
import prosjekt.guests.Person;
import prosjekt.utils.Utils;

/**
 *
 * @author Even
 */
public class BookingPanelGUI {
  private JPanel panelContainer, panelMenu, panelMain;
  private ActionListener btnListener;
  private JButton btnMenuNewBooking, btnMenuShowBookings, btnSearch, btnNew, btnLookup, btnCompleteBooking;
  private JTextField txtFirstname, txtLastname, txtPhoneNumber, txtAddress, txtPostNumber, txtCompanyName;
  private JTextField txtFirstname2, txtLastname2, txtPhoneNumber2, txtAddress2, txtPostNumber2, txtCompanyName2;
  private ArrayList<AbstractGuest> arrListResults;
  private String[][] rowData;
  private String[] columnNames;
  private SearchTableModel tableModel;
  private JTable tableSearchResults;
  private int selectedGuestID;
  private AbstractGuest selectedGuest;
  private JTextArea display;
  private JDateChooser arrivalDate, leavingDate;
  private JComboBox cmbRoomType;
  
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
    btnMenuNewBooking   = new JButton("Ny booking");
    btnMenuShowBookings = new JButton("Vis alle");
    btnMenuNewBooking.addActionListener(btnListener);
    btnMenuShowBookings.addActionListener(btnListener);
    panelMenu.add(btnMenuNewBooking);
    panelMenu.add(btnMenuShowBookings);
    
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
    cmbRoomType = new JComboBox(roomTypes);
    inputPanel.add(new JLabel("Velg romtype:"));
    inputPanel.add(cmbRoomType);
    
    arrivalDate = new JDateChooser();
    leavingDate = new JDateChooser();
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
    c.weighty = 0;
    panel.add(inputPanel, c);
    
    btnCompleteBooking = new JButton("Reserver");
    btnCompleteBooking.addActionListener(btnListener);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.NONE;
    c.anchor  = GridBagConstraints.FIRST_LINE_END;
    c.gridwidth =  0;
    c.gridx   = 5;
    c.gridy   = 1;
    c.weightx = 1;
    c.weighty = 0;
    panel.add(btnCompleteBooking, c);
    
    
    // Create display
    display = new JTextArea(10,50);
    display.setEditable(false);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridwidth =  0;
    c.gridx   = 0;
    c.gridy   = 2;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(display, c);
    
    
    return panel;
  }
  
  
  private JPanel showAllBookings(JPanel panel) {
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    tableSearchResults = new JTable();
    ArrayList<BookingEntry> arrBookingList = Main.bookingRegistry.getList();
    
    if(arrBookingList != null) {
      rowData = new String[arrBookingList.size()][6];
      int i = 0;
      for (BookingEntry r: arrBookingList) {
        rowData[i][0] = r.getFromDate().toString();
        rowData[i][1] = r.getToDate().toString();
        rowData[i][2] = r.getGuest().getFirstName() + " " + r.getGuest().getLastName();
        rowData[i][3] = r.getGuest().getPhoneNumber() + "";
        rowData[i][4] = r.getRoom().getID() + "";
        rowData[i][5] = r.getRoom().getRoomType();
        i++;
      }  
    }
    
    // Array of columnnames for our JTable
    columnNames = new String[]{"Fra", "Til", "Gjest", "Telefon", "RomNr", "Romtype"};
        
    // Create a JTable for guestPanelSearchresults           
    tableModel = new SearchTableModel(rowData, columnNames);
    tableSearchResults = new JTable(tableModel);
    tableSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    // Place table
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.BOTH;
    c.anchor    = GridBagConstraints.LINE_START;
    c.gridwidth = 0;
    c.gridx     = 0;
    c.gridy     = 0;
    c.weightx   = 1;
    c.weighty   = 1;
    panel.add(new JScrollPane(tableSearchResults), c);
    
    return panel;
  }
  
  private void newGuest() {
    JPanel panel = new JPanel(new GridBagLayout());
    
    // Inputpanel
    JPanel inputPanel = new JPanel(new GridLayout(3,4));
    txtFirstname2   = new JTextField(10);
    txtLastname2    = new JTextField(10);
    txtPhoneNumber2 = new JTextField(10);
    txtAddress2     = new JTextField(10);
    txtPostNumber2  = new JTextField(10);
    txtCompanyName2 = new JTextField(10);
    inputPanel.add(new JLabel("Fornavn"));
    inputPanel.add(txtFirstname2);
    inputPanel.add(new JLabel("Adresse"));
    inputPanel.add(txtAddress2);
    inputPanel.add(new JLabel("Etternavn"));
    inputPanel.add(txtLastname2);
    inputPanel.add(new JLabel("Postnummer"));
    inputPanel.add(txtPostNumber2);
    inputPanel.add(new JLabel("Telefonnummer"));
    inputPanel.add(txtPhoneNumber2);
    inputPanel.add(new JLabel("Bedrift"));
    inputPanel.add(txtCompanyName2);
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
    
    
    JFrame frame = new JFrame("Opprett ny bruker");
    JOptionPane.showMessageDialog(frame, panel);
    
    
  }
  
  private void findGuest() {
    selectedGuestID = -1;
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
    inputPanel.add(new JLabel("Bedrift*"));
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
    // Add actionlistener for our JTable
    TableResultsListener tableListener = new TableResultsListener();
    tableSearchResults.getSelectionModel().addListSelectionListener(tableListener);
    
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
    JOptionPane.showMessageDialog(frame, panel);
  }
  
  private class TableResultsListener implements ListSelectionListener {
    JTable table;
    TableResultsListener() {
      table = tableSearchResults;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
        int i = table.getSelectedRow();
        selectedGuestID = arrListResults.get(i).getID();
      } 
    }
  }
  
   private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnMenuNewBooking) {
        panelMain.removeAll();
        panelMain = newBooking(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnMenuShowBookings) {
        panelMain.removeAll();
        panelMain = showAllBookings(panelMain);
        panelMain.updateUI();
      }
      else if (e.getSource() == btnSearch) {
        findGuest();
        if(selectedGuestID != -1) {
          selectedGuest = Main.guestRegistry.getGuest(selectedGuestID);
          display.setText("Du har valg følgende gjest: " + selectedGuest.getFirstName() + " " + selectedGuest.getLastName());
        } else {
          display.setText("Du må velge en gjest");
        }
      }
      else if (e.getSource() == btnCompleteBooking) {
        String errors = "";
        if(selectedGuest == null) {
          errors += "Du må velge eller opprette en gjest \n";
        }
        if(arrivalDate.getDate() == null || leavingDate.getDate() == null) {
          errors += "Du må fylle ut ankomst- og utsjekkingsdato \n";
        }
        if(leavingDate.getDate() != null && leavingDate.getDate().before(arrivalDate.getDate())) {
          errors += "Utsjekkingsdato må være etter ankomstdato";
        }
        
        // If there are any errors
        if(errors != "") {
          Utils.showErrorMessage(null, errors, "Error:");
        }
        else {
          BookingEntry newBooking = Main.bookingRegistry.add(arrivalDate.getDate(), leavingDate.getDate(), selectedGuest, (String) cmbRoomType.getSelectedItem());
          if(newBooking != null) {
            display.append("Gjesten er nå booket inn på rom: " + newBooking.getRoom().getID() + "\n");
            display.append("\n Å Betale: \n");
            display.append(newBooking.getRoom().getPrice() + "kr for : " + newBooking.getRoom().getRoomType());
          } else {
            display.append("Hotellet er desverre fult. Vi har ikke plass til gjesten.");
          }
        
        }
      }
      else if (e.getSource() == btnNew) {
        newGuest();
        String firstname    = txtFirstname2.getText();
        String lastname     = txtLastname2.getText();
        String phoneNumber  = txtPhoneNumber2.getText();
        int postNumber      = 0;
        String address      = txtAddress2.getText();
        String companyName  = txtCompanyName2.getText();
        
        if(firstname.length()>0 && lastname.length()>0 && phoneNumber.length()>0 && txtPhoneNumber2.getText().length()>0 && address.length()>0) {
          try {
            postNumber = Integer.parseInt(txtPostNumber2.getText());
            
            if(companyName.length()>0) {
              Company newCompany = new Company(firstname, lastname, phoneNumber, address, postNumber, companyName);
              Main.guestRegistry.add(newCompany);
              display.setText("Ny firmagjest er opprettet: " + firstname + " " + lastname + " (" + companyName + ")\n");
              selectedGuest = newCompany;
            } else {
              Person newPerson = new Person(firstname, lastname, phoneNumber, address, postNumber);
              Main.guestRegistry.add(newPerson);
              display.setText("Ny gjest er opprettet: " + firstname + " " + lastname + "\n");
              selectedGuest = newPerson;
            }  
          }
          catch(NumberFormatException nfe) {
            System.err.println("Error: Numberformatexception");
          }
        } else {
          display.setText("Vennligst fyll ut alle feltene");
        }
          
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
