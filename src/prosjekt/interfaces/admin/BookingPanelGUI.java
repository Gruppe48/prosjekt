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
 * This is the BookingPanelGUI (A part of the tabbed panel in AdminWindow).
 * 
 * @author Even Augdal
 */
public class BookingPanelGUI {
  /**
   * The panels
   */
  private JPanel panelContainer, panelMenu, panelMain;
  /**
   * Button Listener
   */
  private ActionListener btnListener;
  /**
   * All the buttons.
   */
  private JButton btnMenuNewBooking, btnMenuShowBookings, btnMenuCheckInOut, btnSearch, btnNew, btnLookup, btnCompleteBooking, btnCheckin, btnCheckout;
  /**
   * Text fields for one of the panels.
   */
  private JTextField txtFirstname, txtLastname, txtPhoneNumber, txtAddress, txtPostNumber, txtCompanyName;
  /**
   * Text fields for the other panels.
   * These should really have better names.
   */
  private JTextField txtFirstname2, txtLastname2, txtPhoneNumber2, txtAddress2, txtPostNumber2, txtCompanyName2;
  /**
   * Booking number text field. 
   */
  private JTextField txtBookingNumber;
  /**
   * ArrayList to hold guest searching results.
   */
  private ArrayList<AbstractGuest> arrListResults;
  /**
   * This holds the row data for guest search
   */
  private String[][] rowData;
  /**
   * This holds the column titles in the guest search.
   */
  private String[] columnNames;
  /**
   * Table model for the guest search.
   */
  private SearchTableModel tableModel;
  /**
   * Table to hold guest search results.
   */
  private JTable tableSearchResults;
  /**
   * This holds the guestID of the guest selected in the
   * JTable.
   */
  private int selectedGuestID;
  /**
   * This holds the actual guest selected.
   */
  private AbstractGuest selectedGuest;
  /**
   * Text areas to display information.
   */
  private JTextArea display, displayCheckInOut;
  /**
   * JDateChooser to chose dates
   * @see JDateChooser
   */
  private JDateChooser arrivalDate, leavingDate;
  /**
   * Combobox to select room type.
   */
  private JComboBox cmbRoomType;
  
  /**
   * This is the BookingPanelGUI constructor.
   * Sets up the panelContainer and button listener
   * as well as the "default" panel, which is the booking panel.
   */
  public BookingPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    btnListener = new ButtonListener();
    
    // Lets make this panel
    panelContainer = bookingPanel();
  }
  /**
   * Getter for panelContainer.
   */
  public JPanel getPanel() {
    return panelContainer;
  }
  /**
   * This method sets up the bookingPanel
   * @return JPanel the booking panel.
   */
  private JPanel bookingPanel() {
    JPanel frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnMenuNewBooking   = new JButton("Ny booking");
    btnMenuShowBookings = new JButton("Vis Bookinger");
    btnMenuCheckInOut   = new JButton("Inn/utsjekking");
    btnMenuNewBooking.addActionListener(btnListener);
    btnMenuShowBookings.addActionListener(btnListener);
    btnMenuCheckInOut.addActionListener(btnListener);
    panelMenu.add(btnMenuNewBooking);
    panelMenu.add(btnMenuShowBookings);
    panelMenu.add(btnMenuCheckInOut);
    
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
  
  /**
   * This method sets up the panel for creating
   * a new booking.
   * @return JPanel The new booking panel.
   */
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
    panel.add(new JScrollPane(display), c);
    
    
    return panel;
  }
  
  /**
   * This method sets up the panel that shows all the bookings.
   * @return JPanel the show all bookings panel.
   */
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
  /**
   * This method sets up the panel for checking in/out of the hotel.
   * @return JPanel the checkin/checkout panel.
   */
  private JPanel checkInOut(JPanel panel) {
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    // Create inputpanel
    JPanel inputPanel = new JPanel(new GridLayout(1, 4));
    inputPanel.setBackground(Color.LIGHT_GRAY);
    txtBookingNumber = new JTextField(10);
    btnCheckin  = new JButton("Sjekkinn");
    btnCheckout = new JButton("Sjekkut");
    btnCheckin.addActionListener(btnListener);
    btnCheckout.addActionListener(btnListener);
    inputPanel.add(new JLabel("Bookingnummer:"));
    inputPanel.add(txtBookingNumber);
    inputPanel.add(btnCheckin);
    inputPanel.add(btnCheckout);
    
    // Place elements
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.HORIZONTAL;
    c.anchor    = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 0;
    c.gridx     = 0;
    c.gridy     = 0;
    c.weightx   = 1;
    c.weighty   = 0.1;
    panel.add(inputPanel, c);
    
    // Create display
    displayCheckInOut = new JTextArea(10, 10);
    displayCheckInOut.setEditable(false);
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.BOTH;
    c.anchor    = GridBagConstraints.LINE_START;
    c.gridwidth = 0;
    c.gridx     = 0;
    c.gridy     = 1;
    c.weightx   = 1;
    c.weighty   = 1;
    panel.add(displayCheckInOut, c);
    
    return panel;
  }
  /**
   * This method creates a JOptionPane popup where you are
   * asked to input the details for a new guest.
   */
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
  /**
   * This method creates a JOptionPane popup where you are asked
   * to enter the details of the guest you want to find.
   */
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
  
  /**
   * This is the TableResultsListener.
   * This enables us to see which guest is selected in the JTables.
   */
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
   /**
   * This is the ButtonListener.
   * This enables us to see what buttons are clicked and 
   * act accordingly.
   */
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
      else if(e.getSource() == btnMenuCheckInOut) {
        panelMain.removeAll();
        panelMain = checkInOut(panelMain);
        panelMain.updateUI();
      }
      else if (e.getSource() == btnSearch) {
        findGuest();
        if(selectedGuestID != -1) {
          selectedGuest = Main.guestRegistry.getGuest(selectedGuestID);
          display.setText("Du har valg følgende gjest: " + selectedGuest.getFirstName() + " " + selectedGuest.getLastName() + "\n");
        } else {
          display.setText("Du må velge en gjest\n");
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
        if(!errors.equals("")) {
          Utils.showErrorMessage(null, errors, "Error:");
        }
        else {
          BookingEntry newBooking = Main.bookingRegistry.add(arrivalDate.getDate(), leavingDate.getDate(), selectedGuest, (String) cmbRoomType.getSelectedItem());
          if(newBooking != null) {
            display.append("\nGjesten er nå booket inn på rom: " + newBooking.getRoom().getID() + "\n");
            display.append("Bookingnummer: " + newBooking.getBookingNumber() + " må vises ved inn/utsjekking\n");
            display.append("Å Betale: \n");
            int dayCount = Utils.getDifference(newBooking.getFromDate(), newBooking.getToDate());
            float roomPrice = newBooking.getRoom().getPrice();
            float price = dayCount * roomPrice;
            display.append(price + " kr for " + newBooking.getRoom().getRoomType() + " @ " + roomPrice + " x " + dayCount + " dager \n");
          } else {
            display.append("Hotellet er desverre fult. Vi har ikke plass til gjesten.\n");
          }
        
        }
      }
      else if (e.getSource() == btnCheckin) {
        if(txtBookingNumber.getText().length() > 0) {
          try {
            int bookingnr = Integer.parseInt(txtBookingNumber.getText());
            BookingEntry booking = Main.bookingRegistry.getBooking(bookingnr);
            
            if(booking != null) {
              if(booking.getRoom().isOccupied()) {
                displayCheckInOut.setText("Gjesten har allerede sjekket inn");
                return;
              }
              booking.getRoom().checkIn();
              displayCheckInOut.setText(booking.getGuest().getFirstName() + " " + booking.getGuest().getLastName() + " er nå sjekket inn på rom: " + booking.getRoom().getID());
            } else {
              displayCheckInOut.setText("Finner ikke bookingen. Prøv på nytt");
            }
          }
          catch(NumberFormatException nfe) {
            Utils.showErrorMessage(null, "Bookingnummer kan kun bestå av tall", "Error: Bookingnummer");
          }
        } else { 
          Utils.showWarningMessage(null, "Vennligst fyll inn bookingnummer", "Warning: Bookingnummer"); 
        } 
      }
      else if (e.getSource() == btnCheckout) {
        if(txtBookingNumber.getText().length() > 0) {
          try {
            int bookingnr = Integer.parseInt(txtBookingNumber.getText());
            BookingEntry booking = Main.bookingRegistry.getBooking(bookingnr);
            
            if(booking != null) {
              if(booking.getRoom().isOccupied() == false) {
                displayCheckInOut.setText("Bookingnummer: " + bookingnr + " har enten ikke sjekket inn eller har allerede sjekket ut");
                return;
              }
              booking.getRoom().checkOut();
              displayCheckInOut.setText(booking.getGuest().getFirstName() + " " + booking.getGuest().getLastName() + " er nå sjekket ut av rom: " + booking.getRoom().getID());
            } else {
              displayCheckInOut.setText("Finner ikke bookingen. Prøv på nytt");
            }
          }
          catch(NumberFormatException nfe) {
            Utils.showErrorMessage(null, "Bookingnummer kan kun bestå av tall", "Error: Bookingnummer");
          }
        }
        else {
          Utils.showWarningMessage(null, "Vennligst fyll inn bookingnummer", "Warning: Bookingnummer");
        }
      }
      else if (e.getSource() == btnNew) {
        newGuest();
        String firstname    = txtFirstname2.getText();
        String lastname     = txtLastname2.getText();
        String phoneNumber  = txtPhoneNumber2.getText();
        String postNumber   = "0";
        String address      = txtAddress2.getText();
        String companyName  = txtCompanyName2.getText();
        
        if(firstname.length()>0 && lastname.length()>0 && phoneNumber.length()>0 && txtPhoneNumber2.getText().length()>0 && address.length()>0) {
          try {
            postNumber = txtPostNumber2.getText();
            
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
            Utils.showErrorMessage(null, "Postnummer må være et tall.", "Error: Postnummer");
          }
        } else {
          display.setText("Vennligst fyll ut alle feltene\n");
        }
          
      }
      else if (e.getSource() == btnLookup) {
        String firstname    = txtFirstname.getText();
        String lastname     = txtLastname.getText();
        String phoneNumber  = txtPhoneNumber.getText();
        String address      = txtAddress.getText();
        String companyName  = txtCompanyName.getText();

        try {
          String postNumber = "0";

          if(!txtPostNumber.getText().equals("")) {
            postNumber = txtPostNumber.getText();
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
          Utils.showErrorMessage(null, "Postnummer må være et tall.", "Error: Postnummer");
        }
      }
    }
   }
  
}
