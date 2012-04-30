/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author kristoffer
 */
public class GenericWindow extends JFrame implements IWindow {

  protected ButtonListener buttonListener = new ButtonListener();
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
  protected class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonPressed(e);
    }
  }
}
