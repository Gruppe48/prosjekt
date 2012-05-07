/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
import prosjekt.rooms.AbstractRoom;


/**
 *
 * @author Even
 */
public class AdminWindow2 extends GenericWindow {
  // General
  private JTabbedPane tabbedPane;
  private JComponent panelGuest, panelRoom, panel3, panel4;
  private JPanel panel, menu; 
  private ButtonListener btnListener;
  private String columnNames[];
  private TableModel tableModel;
  
  
  // roomPanel
  private JTextField roomPanelRoomNumber, roomPanelBedCount;
  private JButton roomPanelBtnSearch, roomPanelBtnEdit;
  private JTable roomPanelSearchResults;
  private ArrayList<AbstractRoom> roomPanelListResults;
  
  public AdminWindow2() {
    super("Administratorpanel", 900, 500);
  }

  @Override
  public void create() {
    super.create();
    
    // Create ButtonListener object
    btnListener = new ButtonListener();
    
    tabbedPane = new JTabbedPane();
    
    Container c = getContentPane();
    c.setLayout( new GridLayout(1,1) );
    
    panelGuest = guestPanel();
    tabbedPane.addTab("Gjest", panelGuest);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    panelRoom = roomPanel();
    tabbedPane.addTab("Rom", panelRoom);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    panel3 = makeTextPanel("Panel #3");
    tabbedPane.addTab("Booking", panel3);
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    
    panel4 = makeTextPanel("Panel #4");
    tabbedPane.addTab("Fasiliteter", panel4);
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

 
    c.add(tabbedPane);
    
  }
  
  public JPanel guestPanel() {
    GuestPanelGUI guestPanel = new GuestPanelGUI();
    return guestPanel.getPanel();
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    
  }

  protected JComponent makeTextPanel(String text) {
        JPanel test = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        test.setLayout(new GridLayout(1, 1));
        test.add(filler);
        return test;
  }

  
  private JComponent roomPanel() {
    JPanel frame;
    JButton searchRoom, showRooms;
    
    frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // MENU 
    menu = new JPanel(new GridLayout(6,1));
    
    menu.setBackground(Color.LIGHT_GRAY);
    searchRoom = new JButton("Søk");
    showRooms  = new JButton("Vis alle");
    
    menu.add(searchRoom);
    menu.add(showRooms);
    
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
    panel = searchRoom(panel);
   

    //Place PANEL
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridx   = 1;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    frame.add(panel, c);
    
    
    // Add ActionListeners
    searchRoom.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        panel = searchRoom(panel);
        panel.updateUI();
      }
    });
    showRooms.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       panel.removeAll();
       //panel = showAllRooms(panel);
       panel.updateUI();
      }
    });
    
    return frame;
    
  }
  
  private JPanel searchRoom(JPanel panel) {
    JPanel inputPanel, buttonPanel;
    
    // Create textfields
    roomPanelRoomNumber = new JTextField(10);
    roomPanelBedCount   = new JTextField(10);
    
    // Panel for input fields
    inputPanel = new JPanel(new GridLayout(1,2));
    inputPanel.setBackground(Color.LIGHT_GRAY);
    
    // Adding all labels and textfields to inputPanel
    inputPanel.add(new JLabel("Romnummer"));
    inputPanel.add(roomPanelRoomNumber);
    inputPanel.add(new JLabel("Sengeplasser"));
    inputPanel.add(roomPanelBedCount);
            
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
    roomPanelBtnSearch = new JButton("Søk");
    roomPanelBtnSearch.addActionListener(btnListener);
 
    
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
    panel.add(roomPanelBtnSearch, c);
    
    // Array of columnnames for our JTable
    columnNames = new String[]{"Romnummer", "Type", "Status"};
        
    // Create a JTable for roomPanelSearchresults           
    tableModel = new SearchRoomTableModel(roomPanelListResults, columnNames);
    roomPanelSearchResults = new JTable(tableModel);
    roomPanelSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    
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
    panel.add(new JScrollPane(roomPanelSearchResults), c);
    
    return panel;
    
  }
 

}