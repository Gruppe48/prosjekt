/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import prosjekt.Main;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Company;

/**
 *
 * @author Even
 */
public class GuestPanelGUI {
  // General
  private JPanel panelContainer, panelMenu, panelMain;
  private String columnNames[];
  String[][] rowData, rowData2;
  private TableModel tableModel;
  private ActionListener btnListener;
  
  // guestPanel
  private JTextField txtFirstname, txtLastname, txtPhoneNumber, txtAddress, txtPostNumber, txtCompanyName;
  private JTable tableSearchResults;
  private JButton btnSearch, btnClear, btnSearchGuest, btnShowGuests;
  private ArrayList<AbstractGuest> arrListResults;

  
  public GuestPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    btnListener = new ButtonListener();
    
    // Lets make this panel
    panelContainer = guestPanel();
  }
  
  public JPanel getPanel() {
    return panelContainer;
  }
  
  
  
  private JPanel guestPanel() {
    JPanel frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnSearchGuest = new JButton("Søk");
    btnShowGuests  = new JButton("Vis alle");
    btnSearchGuest.addActionListener(btnListener);
    btnShowGuests.addActionListener(btnListener);
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
    
    // MAIN PANEL
    panelMain  = new JPanel(new GridBagLayout());
    panelMain.setBackground(Color.LIGHT_GRAY);
    panelMain = searchGuest(panelMain);
   

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
    
    // Create buttons
    btnSearch = new JButton("Søk");
    btnClear  = new JButton("Tøm");
    btnSearch.addActionListener(btnListener);
    btnClear.addActionListener(btnListener);
          
    
    // Add the buttons to our buttonPanel
    buttonPanel = new JPanel(new GridLayout(1,2));
    buttonPanel.setBackground(Color.LIGHT_GRAY);
    buttonPanel.add(btnClear);
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
    tableModel = new SearchTableModel(rowData, columnNames);
    tableSearchResults = new JTable(tableModel);
    tableSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    
    // Add actionlistener for our JTable
    TableResultsListener tableListener = new TableResultsListener();
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
  
  
  private class TableResultsListener implements ListSelectionListener {
    JTable table;

    TableResultsListener() {
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
    rowData2 = null;
    
    // Array of columnnames for our JTable
    columnNames = new String[]{"Fornavn", "Etternavn", "Telefon", "Postnummer", "Addresse", "Company"};
    
    // Create a JTable for guestPanelSearchresults           
    tableModel = new SearchTableModel(rowData2, columnNames);
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
    
    HashMap<String, AbstractGuest> hashmapAllGuests = Main.guestRegistry.getList();
          
    // Lets create and fill rowData
    if(hashmapAllGuests != null) {
      rowData2 = new String[hashmapAllGuests.size()][6];
      int i = 0;
      for (AbstractGuest g : hashmapAllGuests.values()) {
        rowData2[i][0] = g.getFirstName();
        rowData2[i][1] = g.getLastName();
        rowData2[i][2] = g.getPhoneNumber();
        rowData2[i][3] = g.getPostNumber() + "";
        rowData2[i][4] = g.getAddress();
        if(g instanceof Company) {
          Company company = (Company) g;
          rowData2[i][5] = company.getCompanyName();
        }
        i++;
      }
    }

    // Tablemodel for our JTable
    tableModel = new SearchTableModel(rowData2, columnNames);
    tableSearchResults.setModel(tableModel);
    
    // add guestPanelSearchResults (JTable) to panel
    panel.add(new JScrollPane(tableSearchResults), c);
    
    return panel;
  }
  
  
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnSearchGuest) {
        panelMain.removeAll();
        panelMain = searchGuest(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnShowGuests) {
        panelMain.removeAll();
        panelMain = showAllGuests(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnClear) {
        txtFirstname.setText("");
        txtLastname.setText("");
        txtPhoneNumber.setText("");
        txtPostNumber.setText("");
        txtAddress.setText("");
        txtCompanyName.setText("");
      }
      else if(e.getSource() == btnSearch) {
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
