package prosjekt.interfaces.admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import prosjekt.interfaces.GenericWindow;


/**
 * The AdminWindow class
 * This class is responsible for all bits administrational.
 * 
 * @author Even Augdal
 */
public class AdminWindow extends GenericWindow {
  private JTabbedPane tabbedPane;
  private JComponent panelGuest, panelRoom, panelBooking, panelInformation;
  private JMenuBar menuBar;
  
  /**
   * This is the constructor for AdminWindow.
   */
  public AdminWindow() {
    // Tell GenericWindow our title and dimensions.
    super("Administratorpanel", 900, 500);
    
    // Create menubar
    menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    JMenu fileMenu = new JMenu("Fil");
    JMenuItem exitAction = new JMenuItem("Avslutt");
    exitAction.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          System.exit(0);
      }
    });
    fileMenu.add(exitAction);
    menuBar.add(fileMenu);

  }

  @Override
  public void create() {
    super.create();

    
    tabbedPane = new JTabbedPane();
    
    Container c = getContentPane();
    c.setLayout( new GridLayout(1,1) );
    
    panelGuest = guestPanel();
    tabbedPane.addTab("Gjest", panelGuest);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    panelRoom = roomPanel();
    tabbedPane.addTab("Rom", panelRoom);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    panelBooking = bookingPanel();
    tabbedPane.addTab("Booking", panelBooking);
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    
    panelInformation = informationPanel();
    tabbedPane.addTab("Informasjon", panelInformation);
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

 
    c.add(tabbedPane);
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  
  @Override
  public void buttonPressed(ActionEvent e) {
    /*
     * This is a bit more advanced with multiple panels, so for simplicity
     * we make our own button listeners where needed. 
     */
    
  }

  /**
   * This method sets up the guestPanel
   * @return The guestPanel's JPanel.
   */
  private JPanel guestPanel() {
    GuestPanelGUI guestPanel = new GuestPanelGUI();
    return guestPanel.getPanel();
  }
  /**
   * This method sets up the roomPanel
   * @return The roomPanel's JPanel.
   */
  private JPanel roomPanel() {
    RoomPanelGUI roomPanel = new RoomPanelGUI();
    return roomPanel.getPanel();
  }
  /**
   * This method sets up the bookingPanel
   * @return The bookingPanel's JPanel.
   */
  private JPanel bookingPanel() {
    BookingPanelGUI bookingPanel = new BookingPanelGUI();
    return bookingPanel.getPanel();
  }
  /**
   * This method sets up the informationPanel
   * @return The informationPanel's JPanel.
   */
  private JPanel informationPanel() {
    InformationPanelGUI informationPanel = new InformationPanelGUI();
    return informationPanel.getPanel();
  }
}