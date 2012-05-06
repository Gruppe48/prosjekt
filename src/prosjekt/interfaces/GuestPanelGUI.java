/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import prosjekt.Main;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Company;
import prosjekt.guests.Person;

/**
 *
 * @author Even
 */
public class GuestPanelGUI {
  // General
  private JPanel panelContainer, panelMenu, panelMain; 
  private GenericWindow.ButtonListener btnListener;
  private String columnNames[];
  private TableModel tableModel;
  
  // guestPanel
  private JTextField txtFirstname, txtLastname, txtPhoneNumber, txtAddress, txtPostNumber, txtCompanyName;
  private JTable tableSearchResults;
  private JButton btnSearch, btnEdit;
  private ArrayList<AbstractGuest> arrListResults;
  private JButton btnSearchGuest, btnShowGuests;

  
  public GuestPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnSearchGuest = new JButton("Søk");
    btnShowGuests  = new JButton("Vis alle");
    panelMenu.add(btnSearchGuest);
    panelMenu.add(btnShowGuests);
    
    
    // Lets make this panel
    panelContainer = guestPanel();
  }
  
  public JPanel getPanel() {
    return panelContainer;
  }
  
  
  
  private JPanel guestPanel() {
    JPanel frame;
    
    frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnSearchGuest = new JButton("Søk");
    btnShowGuests  = new JButton("Vis alle");
    panelMenu.add(btnSearchGuest);
    panelMenu.add(btnShowGuests);
    
    // Place MENU
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.HORIZONTAL;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 0;
    c.weighty = 0;
    frame.add(panelMenu, c);
    
    // PANEL
    panelMain  = new JPanel(new GridBagLayout());
    panelMain.setBackground(Color.LIGHT_GRAY);
    panelMain = searchGuest(panelMain);
   

    //Place PANEL
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridx   = 1;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    frame.add(panelMain, c);

    
    // Add ActionListeners
    btnSearchGuest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelMain.removeAll();
        panelMain = searchGuest(panelMain);
        panelMain.updateUI();
      }
    });
    btnShowGuests.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       panelMain.removeAll();
       panelMain = showAllGuests(panelMain);
       panelMain.updateUI();
      }
    });
    
    return frame;
    
  }
  
  private JPanel searchGuest(JPanel panel) {
    JPanel inputPanel, buttonPanel;
    
    // Create textfields
    txtFirstname   = new JTextField(10);
    txtLastname    = new JTextField(10);
    txtPhoneNumber = new JTextField(10);
    txtAddress     = new JTextField(10);
    txtPostNumber  = new JTextField(10);
    txtCompanyName = new JTextField(10);
    
    // Panel for input fields
    inputPanel = new JPanel(new GridLayout(3,4));
    inputPanel.setBackground(Color.LIGHT_GRAY);
    
    // Adding all labels and textfields to inputPanel
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
            
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    // Constraints
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.HORIZONTAL;
    c.anchor    = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 10;
    c.gridx     = 0;
    c.gridy     = 0;
    c.weightx   = 1;
    c.weighty   = 0;
    
    // add inputPanel to panel
    panel.add(inputPanel, c);
    
    // Create search and edit button
    btnSearch = new JButton("Søk");
    btnEdit   = new JButton("Endre");
    btnEdit.setEnabled(false);
    btnSearch.addActionListener(btnListener);
    btnEdit.addActionListener(btnListener);
          
    
    // Add the buttons to our buttonPanel
    buttonPanel = new JPanel(new GridLayout(1,2));
    buttonPanel.setBackground(Color.LIGHT_GRAY);
    buttonPanel.add(btnEdit);
    buttonPanel.add(btnSearch);
    
    // New constraints
    c.insets      = new Insets(0,0,0,0);
    c.fill        = GridBagConstraints.RELATIVE;
    c.anchor      = GridBagConstraints.LINE_END;
    c.gridwidth   = 1;
    c.gridheight  = 1;
    c.gridx       = 9;
    c.gridy       = 1;
    c.weightx     = 1;
    c.weighty     = 0.05;
    
    // Adding buttonPanel to panel
    panel.add(buttonPanel, c);
    
    // Array of columnnames for our JTable
    columnNames = new String[]{"Fornavn", "Etternavn", "Telefon", "Postnummer", "Addresse", "Company"};
        
    // Create a JTable for guestPanelSearchresults           
    tableModel = new SearchTableModel(arrListResults, columnNames);
    tableSearchResults = new JTable(tableModel);
    tableSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    
    // Add actionlistener for our JTable
    tableResultsListener tableListener = new tableResultsListener();
    tableSearchResults.getSelectionModel().addListSelectionListener(tableListener);
    //guestPanelSearchResults.getColumnModel().getSelectionModel().addListSelectionListener(tableListener);
    
    
    // New constraints
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.BOTH;
    c.anchor    = GridBagConstraints.LINE_START;
    c.gridwidth = 10;
    c.gridx     = 0;
    c.gridy     = 2;
    c.weightx   = 1;
    c.weighty   = 1;
    
    
    // add guestPanelSearchResults (JTable) to panel
    panel.add(new JScrollPane(tableSearchResults), c);
    
    return panel;
  }
  
  
  private class tableResultsListener implements ListSelectionListener {
    JTable table;

    tableResultsListener() {
      table = tableSearchResults;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
        int i = table.getSelectedRow();
        
        if(i != -1) {
          txtFirstname.setText(arrListResults.get(i).getFirstName());
          txtLastname.setText(arrListResults.get(i).getLastName());
          txtAddress.setText(arrListResults.get(i).getAddress());
          txtPhoneNumber.setText(arrListResults.get(i).getPhoneNumber());
          txtPostNumber.setText(arrListResults.get(i).getPostNumber() + "");
          txtCompanyName.setText("");

          btnEdit.setEnabled(true);

          if(arrListResults.get(i) instanceof Company) {
            Company c = (Company) arrListResults.get(i);
            txtCompanyName.setText(c.getCompanyName());
          }
        }
      } 
    }
    
  }
  
  private JPanel showAllGuests(JPanel panel) {
    GridBagConstraints c = new GridBagConstraints();
    
    // Display area
    JTextArea display = new JTextArea(10,30);
    display.setForeground(Color.BLACK);
    display.setBackground(Color.WHITE);
    display.setText(Main.guestRegistry.toString());
    display.setEditable(false);
    JScrollPane scroll = new JScrollPane(display);
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 8;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(scroll, c);
    
    return panel;
  }
  
  public void buttonPressed(ActionEvent e) {
    
    if(e.getSource() == btnSearch) {
      btnEdit.setEnabled(false);
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
        
        // Tablemodel for our JTable
        tableModel = new SearchTableModel(arrListResults, columnNames);
         
        tableSearchResults.setModel(tableModel);
      }
      catch(NumberFormatException nfe) {
        System.out.println("error! NumberFormatException");
      }
    }
    else if(e.getSource() == btnEdit) {
      String firstname    = txtFirstname.getText();
      String lastname     = txtLastname.getText();
      String phoneNumber  = txtPhoneNumber.getText();
      String address      = txtAddress.getText();
      String companyName  = txtCompanyName.getText();
      AbstractGuest editedGuest;
      
      try {
        int postNumber = 0;
        
        if(!txtPostNumber.getText().equals("")) {
          postNumber = Integer.parseInt(txtPostNumber.getText());
        }
        
        // Create new guest based on the old
        if(companyName.length() > 0) {
          editedGuest = new Company(firstname, lastname, phoneNumber, address, postNumber, companyName);
        }
        else {
          editedGuest = new Person(firstname, lastname, phoneNumber, address, postNumber);
        }
        
        // Swap the old one with the new one.
        Main.guestRegistry.swapGuest(arrListResults.get(tableSearchResults.getSelectedRow()), editedGuest);
        
      }
      catch(NumberFormatException nfe) {
        System.out.println("error! NumberFormatException");
      }
      catch(NullPointerException npe) {
        System.out.println("error! NullPointerException");
      }
      
    }
    
  }
  
}
