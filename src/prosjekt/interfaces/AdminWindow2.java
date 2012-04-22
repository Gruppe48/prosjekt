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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import prosjekt.Main;
import prosjekt.guests.AbstractGuest;


/**
 *
 * @author Even
 */
public class AdminWindow2 extends GenericWindow {
  private JTabbedPane tabbedPane;
  private JComponent panelGuest, panel2, panel3, panel4;
  private JPanel panel, menu; 
  private JTextField guestPanelFirstname, guestPanelLastname, guestPanelPhoneNumber, guestPanelAddress, guestPanelPostNumber, guestPanelCompanyName;
  private JTable guestPanelSearchResults;
  private JButton guestPanelBtnSearch, guestPanelBtnEdit;
  private ButtonListener btnListener;
  private TableModel tableModel;
  private String[][] rowData;
  private Object columnNames[];
  
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

    panel2 = makeTextPanel("Panel #2");
    tabbedPane.addTab("Rom", panel2);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    panel3 = makeTextPanel("Panel #3");
    tabbedPane.addTab("Booking", panel3);
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    
    panel4 = makeTextPanel("Panel #4");
    tabbedPane.addTab("Fasiliteter", panel4);
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

 
    c.add(tabbedPane);
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    
    if(e.getSource() == guestPanelBtnSearch) {
      String firstname    = guestPanelFirstname.getText();
      String lastname     = guestPanelLastname.getText();
      String phoneNumber  = guestPanelPhoneNumber.getText();
      String address      = guestPanelAddress.getText();
      String companyName  = guestPanelCompanyName.getText();
      
      try {
        int postNumber = 0;
        
        if(!guestPanelPostNumber.getText().equals("")) {
          postNumber = Integer.parseInt(guestPanelPostNumber.getText());
        }
        
        ArrayList<AbstractGuest> listResults = Main.guestRegistry.searchGuests(firstname, lastname, phoneNumber, address, postNumber, companyName);
          
        rowData = new String[listResults.size()][5];
        
        if(listResults == null) {
          rowData[0][0] = "Finner ingen matchende gjester";
        }
        else {
          int i = 0;
          for (AbstractGuest g : listResults) {
            rowData[i][0] = g.getFirstName();
            rowData[i][1] = g.getLastName();
            rowData[i][2] = g.getPhoneNumber();
            rowData[i][3] = g.getAddress();
            i++;
          }
        }
        
        // Array of columnnames for our JTable
        //Object columnNames[] = {"Fornavn", "Etternavn", "Telefon", "Addresse"};
        
        columnNames = new String[4];
        columnNames[0] = "Fornavn";
        columnNames[1] = "Etternavn";
        columnNames[2] = "Telefon";
        columnNames[3] = "Addresse";
        
        // Tablemodel for our JTable
        tableModel = new DefaultTableModel(rowData, columnNames);
         
        guestPanelSearchResults.setModel(tableModel);
      }
      catch(NumberFormatException nfe) {
        System.out.println("error! NumberFormatException");
      }
    }
    
  }
  
  private static class ColoredCellRenderer extends DefaultListCellRenderer {  
    @Override
        public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {  
            Component c = super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );  
            
            if ( index % 2 == 0 ) {  
                c.setBackground( Color.WHITE ); 
                c.setForeground( Color.BLACK);
            }  
            else {  
                c.setBackground( Color.LIGHT_GRAY ); 
            }  
            
            if(isSelected)
               c.setBackground( Color.GRAY );
            
            return c;  
        }  
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
    JPanel frame;
    JButton searchGuest, showGuests;
    
    frame = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    frame.setBackground(Color.LIGHT_GRAY);
    
    // MENU 
    menu = new JPanel(new GridLayout(6,1));
    
    menu.setBackground(Color.LIGHT_GRAY);
    searchGuest = new JButton("Søk");
    showGuests  = new JButton("Vis alle");
    
    menu.add(searchGuest);
    menu.add(showGuests);
    
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
    panel = searchGuest(panel);
   

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
    searchGuest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        panel = searchGuest(panel);
        panel.updateUI();
      }
    });
    showGuests.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       panel.removeAll();
       panel = showAllGuests(panel);
       panel.updateUI();
      }
    });
    
    
    return frame;
    
  }
  
  
  private JPanel showAllGuests(JPanel panel) {
    GridBagConstraints c = new GridBagConstraints();
    
    // Display area
    JTextArea display = new JTextArea(10,30);
    display.setForeground(Color.BLACK);
    display.setBackground(Color.WHITE);
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
  
  
  private JPanel searchGuest(JPanel panel) {
    JPanel inputPanel, buttonPanel;
    
    // Create textfields
    guestPanelFirstname   = new JTextField(10);
    guestPanelLastname    = new JTextField(10);
    guestPanelPhoneNumber = new JTextField(10);
    guestPanelAddress     = new JTextField(10);
    guestPanelPostNumber  = new JTextField(10);
    guestPanelCompanyName = new JTextField(10);
    
    // Panel for input fields
    inputPanel = new JPanel(new GridLayout(3,4));
    inputPanel.setBackground(Color.LIGHT_GRAY);
    
    // Adding all labels and textfields to inputPanel
    inputPanel.add(new JLabel("Fornavn"));
    inputPanel.add(guestPanelFirstname);
    inputPanel.add(new JLabel("Adresse"));
    inputPanel.add(guestPanelAddress);
    inputPanel.add(new JLabel("Etternavn"));
    inputPanel.add(guestPanelLastname);
    inputPanel.add(new JLabel("Postnummer"));
    inputPanel.add(guestPanelPostNumber);
    inputPanel.add(new JLabel("Telefonnummer"));
    inputPanel.add(guestPanelPhoneNumber);
    inputPanel.add(new JLabel("Bedrift"));
    inputPanel.add(guestPanelCompanyName);
            
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
    guestPanelBtnSearch = new JButton("Søk");
    guestPanelBtnEdit   = new JButton("Endre");
    guestPanelBtnEdit.setEnabled(false);
    guestPanelBtnSearch.addActionListener(btnListener);
          
    
    // Add the buttons to our buttonPanel
    buttonPanel = new JPanel(new GridLayout(1,2));
    buttonPanel.setBackground(Color.LIGHT_GRAY);
    buttonPanel.add(guestPanelBtnEdit);
    buttonPanel.add(guestPanelBtnSearch);
    
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
    
    // Create a JTable for guestPanelSearchresults           
    tableModel = new DefaultTableModel(rowData, columnNames);
    guestPanelSearchResults = new JTable(tableModel);
    guestPanelSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
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
    panel.add(new JScrollPane(guestPanelSearchResults), c);
    
    return panel;
  }
  

}