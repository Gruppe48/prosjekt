/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.event.ActionEvent;

/**
 *
 * @author kristoffer
 */
public interface IWindow { 
  public void create();
  public void destroy();
  public void buttonPressed(ActionEvent e);
}
