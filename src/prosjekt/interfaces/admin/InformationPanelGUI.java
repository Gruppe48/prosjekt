package prosjekt.interfaces.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.rtf.RTFEditorKit;
import prosjekt.utils.Utils;

/**
 * This is the InformationPanelGUI.
 * This panel allows the employees to do basic editing of
 * the information displayed to guests in the GuestWindow.
 * This window will work fine for editing typographical errors,
 * but a better way of editing (which would allow you to make style changes)
 * would be to use a Rich text editor and edit the files in 'assets/guests/' in the
 * root path of this folder.
 * 
 * @author Even Augdal
 * @author Kristoffer Berdal
 */
public class InformationPanelGUI {
  /**
   * Create the panels.
   */
  private JPanel panelContainer, panelMenu, panelMain;
  /**
   * Button listener.
   */
  private ActionListener buttonListener;
  /**
   * Buttons
   */
  private JButton homeButton, facilitiesButton, foodButton;
  /**
   * Editor panel to hold the text.
   */
  private JEditorPane display;
  /**
   * Root path of the files we are editing.
   */
  private final String ROOT_PATH = "assets/guests/";
  /**
   * A single button to save the file.
   */
  private JButton saveButton;
  /**
   * The default file is 'index.rtf' which is the first
   * page a guest sees in the guest window.
   *
   * '.rtf' is a rich text format file.
   * TextEdit on OSX or WordPad on Window can both edit these.
   * 
   */
  private String currentFile = "index.rtf";
  
  
  /**
   * InformationPanel constructor.
   * This sets up the panelContainer, button listener
   * and window contents.
   */
  public InformationPanelGUI() {
    if(panelContainer != null) {
      panelContainer.removeAll();
    }
    
    // Create buttonlistener
    buttonListener = new ButtonListener();
    
    panelContainer = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    panelContainer.setBackground(Color.LIGHT_GRAY);
    
    // Create menu:
    panelMenu = new JPanel(new GridLayout(6,1));
    panelMenu.setBackground(Color.LIGHT_GRAY);
    homeButton        = new JButton("Hjem");
    facilitiesButton  = new JButton("Fasiliteter");
    foodButton  = new JButton("Mat & Drikke");
    homeButton.addActionListener(buttonListener);
    facilitiesButton.addActionListener(buttonListener);
    foodButton.addActionListener(buttonListener);
    panelMenu.add(homeButton);
    panelMenu.add(facilitiesButton);
    panelMenu.add(foodButton);
    
    // Place MENU
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.HORIZONTAL;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 0;
    c.weighty = 0;
    panelContainer.add(panelMenu, c);
    
    // MAIN PANEL
    panelMain  = new JPanel(new GridBagLayout());
    panelMain.setBackground(Color.LIGHT_GRAY);
    panelMain = home(panelMain);
   

    //Place MAIN PANEL
    c.insets  = new Insets(7,7,7,7);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.PAGE_START;
    c.gridx   = 1;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panelContainer.add(panelMain, c);
  }
  
  /**
   * Getter for panelContainer.
   * @return the panelContainer.
   */
  public JPanel getPanel() {
    return panelContainer;
  }
  
  /**
   * This method sets up the home panel.
   * @return JPanel home panel.
   */
  private JPanel home(JPanel panel) {
    
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    
    // Create display
    display = new JEditorPane();
    display.setContentType("text/rtf");
    display.setEditable(true);
    display.setEditorKit(new RTFEditorKit());
    display.setText(Utils.read(ROOT_PATH + "index.rtf")); 
    //TODO: Her kunne vi evt. bruke EditorKit.read() men jeg hadde allerede denne metoden i Utils.
    //http://docs.oracle.com/javase/6/docs/api/javax/swing/text/EditorKit.html#read(java.io.InputStream, javax.swing.text.Document, int)
    
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.BOTH;
    c.anchor  = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth =  0;
    c.gridx   = 0;
    c.gridy   = 0;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(display, c);
    
    
    saveButton = new JButton("Lagre endringer");
    c.insets  = new Insets(0,0,0,0);
    c.fill    = GridBagConstraints.NONE;
    c.anchor  = GridBagConstraints.LAST_LINE_END;
    c.gridwidth =  0;
    c.gridx   = 6;
    c.gridy   = 1;
    c.weightx = 1;
    c.weighty = 0;
    panel.add(saveButton, c);
    saveButton.addActionListener(buttonListener);

    return panel;
  }
  
  
  /**
   * Button listener for button events.
   */
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == homeButton) {
        currentFile = "index.rtf";
        display.setText(Utils.read(ROOT_PATH + currentFile));
      }
      else if (e.getSource() == facilitiesButton) {
        currentFile = "facilities.rtf";
        display.setText(Utils.read(ROOT_PATH + currentFile));
        
      }
      else if (e.getSource() == foodButton) {
        currentFile = "restaurant.rtf";
        display.setText(Utils.read(ROOT_PATH + currentFile));
      }
      else if (e.getSource() == saveButton) {
        try {
          display.getEditorKit().write(new FileOutputStream(new File(ROOT_PATH+ currentFile)), display.getDocument(), 0, display.getDocument().getLength());
        } catch (Exception err) {
          Utils.showErrorMessage(null, err.getMessage(), "Error");
        }
      }
    }
  }
  
}
