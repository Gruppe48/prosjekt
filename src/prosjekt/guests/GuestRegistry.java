/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.guests;

import java.util.ArrayList;

/**
 *
 * @author kristoffer
 */
public class GuestRegistry {
  // "Indeksert" etter from, to og room.
  private ArrayList<AbstractGuest> list = new ArrayList();
  
  public boolean add() {
 
    return true;
  }
  public ArrayList<AbstractGuest> getList() {
    return list;
  }
  public boolean remove(AbstractGuest guest) {
    for (AbstractGuest g : list) {
      if (g.equals(guest)) {
        list.remove(g);
        return true;
      }
    }
    return false;
  }
  public boolean exists(AbstractGuest guest) {
    for (AbstractGuest g : list) {
      if (g.equals(guest)) {
        return true;
      }
     }
    return false;
  }
}
