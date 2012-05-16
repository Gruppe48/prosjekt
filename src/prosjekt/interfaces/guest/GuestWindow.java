package prosjekt.interfaces.guest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.*;
import prosjekt.Main;
import prosjekt.interfaces.GenericWindow;
import prosjekt.utils.Utils;

/**
 * This is the GuestWindow.
 * This is the window guests will be able to see at a computer (or several)
 * in the lobby. This window displays information about the hotel and 
 * the surrounding areas to guests.
 * 
 * @author Kristoffer Berdall <web@flexd.net>
 */
public class GuestWindow extends GenericWindow {

  // Declare all the things.
  private JButton homeButton, facilitiesButton, restaurantButton, guestBookButton, saveMessageButton;
  private JPanel menuPanel, mainPanel, contentPanel;
  private JTextPane contentPane;
  private JTextArea messageArea;
  private JTextField messageTextField;
  private Color uiMainColor;
  private final String ROOT_PATH = "assets/guests/";
  private JScrollPane scrollPane;
  
  /**
   * This is the GuestWindow's constructor, which calls to GenericWindow's constructor with
   * our title and dimensions.
   */
  public GuestWindow() {
    super("Nyttig informasjon for gjester", 600, 400);
  }
 
  
  @Override
  public void create() {
    super.create();
    
    if(contentPanel != null) {
      contentPanel.removeAll();
    }
    
    uiMainColor = (Color) Main.options.get("uiMainColor");
    contentPanel = new JPanel(new GridBagLayout());
    contentPanel.setBackground(uiMainColor);
    
    menuPanel = new JPanel(new GridLayout(8, 1));
    menuPanel.setBackground(uiMainColor);
    mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBackground(uiMainColor);

    mainPanel = textContent(mainPanel);
    
    // Create menu
    homeButton = new JButton("Hjem");
    facilitiesButton = new JButton("Fasiliter ved hotellet");
    restaurantButton = new JButton("Mat og uteliv");
    guestBookButton = new JButton("Gjestebok");
    homeButton.addActionListener(buttonListener);
    facilitiesButton.addActionListener(buttonListener);
    restaurantButton.addActionListener(buttonListener);
    guestBookButton.addActionListener(buttonListener);
    menuPanel.add(homeButton);
    menuPanel.add(facilitiesButton);
    menuPanel.add(restaurantButton);
    menuPanel.add(guestBookButton);
    
 
    GridBagConstraints g = new GridBagConstraints();
    // Menu
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.HORIZONTAL;
    g.anchor = GridBagConstraints.FIRST_LINE_START;
    g.gridx = 0;
    g.gridy = 0;
    g.weightx = 0;
    g.weighty = 0;
    contentPanel.add(menuPanel, g);
    // Output
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.BOTH;
    g.anchor = GridBagConstraints.PAGE_START;
    g.gridx = 1;
    g.gridy = 0;
    g.weightx = 1;
    g.weighty = 1;

    contentPanel.add(mainPanel, g);
    
    Container c = getContentPane();
    c.setLayout( new GridLayout(1,1) );
    c.add(contentPanel);

  }

  @Override
  public void destroy() {
    super.destroy();
  }
  /**
   * This method gets the latest Guestbook messages
   * and adds them to the guestbook messageArea.
   * It also attempts at scrolling the messageArea as 
   * far down as it possible to see the latest message.
   */
  private void getMessages() {
    Collection<String> messages = Main.guestBook.getList();
    messageArea.setText("");
    for (String m : messages) {
      messageArea.append(m);
      messageArea.append("\n");
      scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

    }
  }
  /**
   * This method sets up the textContent panel
   * The textContent panel is for the information panels
   * like introduction page, facilities and food & restaurants
   * that is loaded from file.
   * 
   * @return The textContent JPanel.
   */
  private JPanel textContent(JPanel p) {
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
    
    contentPane = new JTextPane();
    contentPane.setContentType("text/rtf");
    contentPane.setText(Utils.read(ROOT_PATH + "index.rtf"));
    contentPane.setEditable(false);
    
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.BOTH;
    c.anchor    = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 0;
    c.gridx     = 0;
    c.gridy     = 0;
    c.weightx   = 1;
    c.weighty   = 1;
    p.add(contentPane, c);
    return p;
  }
  
  /**
   * This method sets up the guestBook panel
   * 
   * @return The guettBook JPanel.
   */
  private JPanel guestBook(JPanel p) {
    messageArea = new JTextArea(10, 10);
    messageArea.setEditable(false);
    messageArea.setLineWrap(true);
    scrollPane = new JScrollPane(messageArea);
    getMessages();
    saveMessageButton   = new JButton("Lagre");
    saveMessageButton.addActionListener(buttonListener);
    messageTextField = new JTextField(10);
    
    // Create a contraints variable for gridbaglayout
    GridBagConstraints c = new GridBagConstraints();
   
    // Place Display
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.BOTH;
    c.anchor    = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 0;
    c.gridx     = 0;
    c.gridy     = 0;
    c.weightx   = 1;
    c.weighty   = 1;
    
   
    p.add(scrollPane, c);
    
    // Place txtMessage
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.HORIZONTAL;
    c.anchor    = GridBagConstraints.FIRST_LINE_START;
    c.gridx     = 0;
    c.gridy     = 1;
    c.weightx   = 1;
    c.weighty   = 0;
    p.add(messageTextField, c);
    
    // Place btnSave
    c.insets    = new Insets(0,0,0,0);
    c.fill      = GridBagConstraints.NONE;
    c.anchor    = GridBagConstraints.FIRST_LINE_END;
    c.gridx     = 0;
    c.gridy     = 2;
    c.weightx   = 1;
    c.weighty   = 0;
    p.add(saveMessageButton, c);

    
    return p;
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);

    // Based on which button clicked, load the appropriate panel and contents.
    if (e.getSource() == homeButton) {
      mainPanel.removeAll();
      mainPanel = textContent(mainPanel);
      contentPane.setText(Utils.read(ROOT_PATH + "index.rtf"));
      mainPanel.updateUI();
    } else if (e.getSource() == facilitiesButton) {
      mainPanel.removeAll();
      mainPanel = textContent(mainPanel);
      contentPane.setText(Utils.read(ROOT_PATH + "facilities.rtf"));
      mainPanel.updateUI();
    } else if (e.getSource() == restaurantButton) {
      mainPanel.removeAll();
      mainPanel = textContent(mainPanel);
      contentPane.setText(Utils.read(ROOT_PATH + "restaurant.rtf"));
      mainPanel.updateUI();
      
      // Guest book buttons below.
    } else if (e.getSource() == guestBookButton) {
      mainPanel.removeAll();
      mainPanel = guestBook(mainPanel);
      mainPanel.updateUI();
    } else if (e.getSource() == saveMessageButton) {
      String message = messageTextField.getText();
      if (message.equals("")) {
        JOptionPane.showMessageDialog(this, "Vi kan ikke lagre en tom melding!");
      }
      else if (message != null) {
        boolean result = Main.guestBook.add(message);
        if (result) {
          JOptionPane.showMessageDialog(this, "Melding lagret!");
          getMessages(); // Reload the messages after adding a new one!
          
          messageTextField.setText("");
        }
        else {
          JOptionPane.showMessageDialog(this, "Meldingen er for lang!");
        }
      }
    }
  }
}
