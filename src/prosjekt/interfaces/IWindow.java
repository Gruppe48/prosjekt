package prosjekt.interfaces;

import java.awt.event.ActionEvent;

/**
 * This is the IWindow interface responsible for defining
 * methods for GenericWindow (and all subclasses).
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public interface IWindow { 
  /**
   * This method is called in the constructor of Generic Windoww.
   * This method is a good place to load things or setup your window.
   */  
  public void create();
  /**
   * This method is called right before the window is closed.
   * This is a good place to save things, or free up resources
   * to prevent memory leaks.
   * (not that we can do that in Java, silly JVM! >:()
   */
  public void destroy();
  /**
   * This is a generic method called in actionPerformed
   * on GenericWindow. You can use this to catch mouse events,
   * or you could create your own. This is here simply to be a
   * simple alternative to setting up your own things, but
   * that might be needed in more advanced windows with multiple
   * panels.
   * @param e 
   */
  public void buttonPressed(ActionEvent e);
}
