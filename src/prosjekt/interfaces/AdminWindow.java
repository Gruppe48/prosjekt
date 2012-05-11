/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 *
 * @author Even
 */
public class AdminWindow extends GenericWindow {
  // General
  private JTabbedPane tabbedPane;
  private JComponent panelGuest, panelRoom, panelBooking, panelInformation;
  
  public AdminWindow() {
    super("Administratorpanel", 900, 500);
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
    
  }


  private JPanel guestPanel() {
    GuestPanelGUI guestPanel = new GuestPanelGUI();
    return guestPanel.getPanel();
  }
  private JPanel roomPanel() {
    RoomPanelGUI roomPanel = new RoomPanelGUI();
    return roomPanel.getPanel();
  }
  private JPanel bookingPanel() {
    BookingPanelGUI bookingPanel = new BookingPanelGUI();
    return bookingPanel.getPanel();
  }
  private JPanel informationPanel() {
    InformationPanelGUI informationPanel = new InformationPanelGUI();
    return informationPanel.getPanel();
  }
}