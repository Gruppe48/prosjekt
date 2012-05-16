package prosjekt.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * This is the class GenericWindow.
 * This class is meant to serve as a super class for
 * all our window classes.
 * We setup some sane defaults here and add a button listener.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class GenericWindow extends JFrame implements IWindow {
  /**
   * A button listener.
   */
  protected ButtonListener buttonListener = new ButtonListener();
  /**
   * This is the constructor for GenericWindow
   * @param title The title of the window
   * @param width the desired width of the window in pixels
   * @param height the desired height of the window in pixels
   */
  public GenericWindow(String title, int width, int height) {
    super(title);
    try {
      create();
    } catch (Exception ex) {
      Logger.getLogger(GenericWindow.class.getName()).log(Level.SEVERE, null, ex);
    } 
    setSize(width, height);
    setVisible(true);
    
    addWindowListener(new WindowAdapter() {

    @Override
    public void windowClosing(WindowEvent e) {
      try {
        //TODO: Lagre klassen her, eller noe.
        destroy();
      } catch (Exception ex) {
        Logger.getLogger(GenericWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.exit(0);
    }
  });
  }

  @Override
  public void create() {
    // Sette opp stasj hente lagrede data osv?
  }
  @Override
  public void destroy() {
    // Godt sted å lagre ting.
  }
  @Override
  public void buttonPressed(ActionEvent e) {
    
  }
  /**
   * This is the button listener, this enables us to
   * catch button events
   */
  protected class ButtonListener implements ActionListener {
    /**
     * This catches button events, and they are all passed to
     * buttonPressed(ActionEvent e)
     * 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonPressed(e);
    }
  }
}
