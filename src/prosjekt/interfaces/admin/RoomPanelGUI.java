/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
import prosjekt.Main;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author Even
 */
public class RoomPanelGUI {
  private JPanel panelContainer, panelMenu, panelMain;
  private String columnNames[];
  private TableModel tableModel;
  private ActionListener btnListener;
  private String[][] rowData, rowData2;
  private JTextField txtRoomNumber;
  private JComboBox cmbRoomTypes;
  private JButton btnSearch, btnSearchRooms, btnShowRooms;;
  private JTable tableSearchResults;
  private ArrayList<AbstractRoom> arrListResults;
  
  public RoomPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    btnListener = new ButtonListener();
    
    // Lets make this panel
    panelContainer = roomPanel();
  }
  
  public JPanel getPanel() {
    return panelContainer;
  }
  
  private JPanel roomPanel() {
    JPanel frame;
    
    frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    btnSearchRooms = new JButton("Søk");
    btnShowRooms   = new JButton("Vis alle");
    btnSearchRooms.addActionListener(btnListener);
    btnShowRooms.addActionListener(btnListener);
    panelMenu.add(btnSearchRooms);
    panelMenu.add(btnShowRooms);
    
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
    panelMain = searchRooms(panelMain);
   

    //Place PANEL
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
  
  private JPanel searchRooms(JPanel panel) {
    JPanel inputPanel;
    
    // Create textfields
    txtRoomNumber = new JTextField(10);
   
    // Create Combobox for roomtypes
    String[] arrRoomTypes = Main.roomRegistry.getRoomTypes();
    cmbRoomTypes = new JComboBox(arrRoomTypes);
    
    // Panel for input fields
    inputPanel = new JPanel(new GridLayout(1,2));
    inputPanel.setBackground(Color.LIGHT_GRAY);
    
    // Adding all labels and textfields to inputPanel
    inputPanel.add(new JLabel("Romnummer"));
    inputPanel.add(txtRoomNumber);
    inputPanel.add(new JLabel("Romtype"));
    inputPanel.add(cmbRoomTypes);
            
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
    btnSearch.addActionListener(btnListener);
 
    
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
    panel.add(btnSearch, c);
    
    // Array of columnnames for our JTable
    columnNames = new String[]{"Romnummer", "Type", "Status"};
    
    
    // Create a JTable for roomPanelSearchresults           
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
    
    
    // add guestPanelSearchResults (JTable) to panel
    panel.add(new JScrollPane(tableSearchResults), c);
    
    return panel;
  }
  
  private JPanel showAllRooms(JPanel panel) {
    GridBagConstraints c = new GridBagConstraints();
    rowData2 = null;
    
    // Array of columnnames for our JTable
    columnNames = new String[]{"Romnummer", "Type", "Status"};
    arrListResults = Main.roomRegistry.getList();
    
    // Lets create and fill rowData
    if(arrListResults != null) {
      rowData2 = new String[arrListResults.size()][3];
      int i = 0;
      for (AbstractRoom r : arrListResults) {
        rowData2[i][0] = r.getID() + "";
        rowData2[i][1] = r.getRoomType();
        rowData2[i][2] = (r.isOccupied()) ? "Opptatt" : "Ledig";
        i++;
      }
    }
        
    // Create a JTable for roomPanelSearchresults           
    tableModel = new SearchTableModel(rowData2, columnNames);
    tableSearchResults = new JTable(tableModel);
    tableSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 8;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(new JScrollPane(tableSearchResults), c);
    
    return panel;
  }
  
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnSearchRooms) {
        panelMain.removeAll();
        panelMain = searchRooms(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnShowRooms) {
        panelMain.removeAll();
        panelMain = showAllRooms(panelMain);
        panelMain.updateUI();
      }
      else if(e.getSource() == btnSearch) {
        String roomNumber = txtRoomNumber.getText();
        String roomType   = (String) cmbRoomTypes.getSelectedItem();
        
        arrListResults = Main.roomRegistry.searchRoom(roomNumber, roomType);
    
        // Lets create and fill rowData
        if(arrListResults != null) {
          rowData = new String[arrListResults.size()][3];
          int i = 0;
          for (AbstractRoom r : arrListResults) {
            rowData[i][0] = r.getID() + "";
            rowData[i][1] = r.getRoomType();
            rowData[i][2] = (r.isOccupied()) ? "Opptatt" : "Ledig";
            i++;
          }
        }
        
        // Tablemodel for our JTable
        tableModel = new SearchTableModel(rowData, columnNames);
        tableSearchResults.setModel(tableModel);
      }
      
    }

  }
  
  
}