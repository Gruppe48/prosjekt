/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTextArea;
import prosjekt.Main;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author kristoffer
 */
public class ListWindow extends GenericWindow {
  JTextArea out;
  
  public ListWindow() {
    super("Listing test", 400, 600);
  }

  @Override
  public void create() {
    super.create();
    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
    out = new JTextArea(20, 20);
    c.add(out);
    ArrayList<AbstractRoom> list = Main.roomRegistry.getList();
    for (AbstractRoom room : list) {
      out.append(room.toString());
      
    }
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);    
  }
}
