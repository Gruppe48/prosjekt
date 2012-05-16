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
 * This is the RoomPanelGUI of the AdminWindow.
 * This panel lists up the rooms and status in the hotel.
 * 
 * @author Even Augdal
 */
public class RoomPanelGUI {
  /**
   * Declare the panels.
   */
  private JPanel panelContainer, panelMenu, panelMain;
  /**
   * JTable column names.
   */
  private String columnNames[];
  /**
   * TableModel for JTable
   */
  private TableModel tableModel;
  /**
   * Button listener.
   */
  private ActionListener btnListener;
  /**
   * Search result dataa.
   */
  private String[][] rowData;
  /**
   * Text field for room Number
   */
  private JTextField txtRoomNumber;
  /**
   * Combobox for room type to search for.
   */
  private JComboBox cmbRoomTypes;
  /**
   * Buttons
   */
  private JButton btnSearch, btnSearchRooms, btnShowRooms;
  /**
   * JTable for search results.
   */
  private JTable tableSearchResults;
  /**
   * ArrayList with actual search results.
   */
  private ArrayList<AbstractRoom> arrListResults;
  /*
   * Maincolor to use as background
   */
  private Color uiMainColor;
  
  /**
   * Constructor sets up the panelContainer, button listener
   * and the roomPanel.
   */
  public RoomPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Get maincolor from Options
    uiMainColor = (Color) Main.options.get("uiMainColor");
    
    // Create buttonlistener
    btnListener = new ButtonListener();
    
    // Lets make this panel
    panelContainer = roomPanel();
  }
  /**
   * Getter for panelContainer.
   * @return panelContainer
   */
  public JPanel getPanel() {
    return panelContainer;
  }
  /**
   * This method sets up the room Panel
   * @return the room panel.
   */
  private JPanel roomPanel() {
    JPanel frame;
    
    frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(uiMainColor);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(uiMainColor);
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
    panelMain.setBackground(uiMainColor);
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
  /**
   * This method sets up the panel for room search.
   * @return Room search panel.
   */
  private JPanel searchRooms(JPanel panel) {
    JPanel inputPanel;
    
    // Create textfields
    txtRoomNumber = new JTextField(10);
   
    // Create Combobox for roomtypes
    String[] arrRoomTypes = Main.roomRegistry.getRoomTypes();
    cmbRoomTypes = new JComboBox(arrRoomTypes);
    
    // Panel for input fields
    inputPanel = new JPanel(new GridLayout(1,2));
    inputPanel.setBackground(uiMainColor);
    
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
    columnNames = new String[]{"Romnummer", "Type"};
    
    
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
  /**
   * This panel sets up the panel that shows all rooms.
   * @return A panel with all rooms in a Jtable.
   */
  private JPanel showAllRooms(JPanel panel) {
    GridBagConstraints c = new GridBagConstraints();
    
    // Array of columnnames for our JTable
    arrListResults = Main.roomRegistry.getList();
    
    updateTable();
        
    // Create a JTable for roomPanelSearchresults           
    tableModel = new SearchTableModel(rowData, columnNames);
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
  /**
   * This method updates the JTable with the arrListResults content.
   * 
   */
  public void updateTable() {
      // Lets create and fill rowData
      if(arrListResults != null) {
        rowData = new String[arrListResults.size()][2];
        int i = 0;
        for (AbstractRoom r : arrListResults) {
          rowData[i][0] = r.getID() + "";
          rowData[i][1] = r.getRoomType();
          i++;
        }
      }
      
      // Tablemodel for our JTable
      tableModel = new SearchTableModel(rowData, columnNames);
      tableSearchResults.setModel(tableModel);
    }
  /**
   * Button listener.
   */
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
        updateTable();
      }
      
    }

    

  }
  
  
}
