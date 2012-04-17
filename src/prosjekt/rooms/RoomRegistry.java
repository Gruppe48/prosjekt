/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import java.util.ArrayList;

/**
 *
 * @author kristoffer
 */
public class RoomRegistry {
  // "Indeksert" etter from, to og room.
  private ArrayList<AbstractRoom> list = new ArrayList();
  private RoomHistory history = new RoomHistory();
  
  public boolean add(AbstractRoom room) {
    if (!exists(room)) {
          list.add(room);
          history.add(room);
          return true;
    }
    return false;
  }
  public ArrayList<AbstractRoom> getList() {
    return list;
  }
  public boolean remove(AbstractRoom room) {
    for (AbstractRoom r : list) {
      if (r.equals(room)) {
        list.remove(r);
        return true;
      }
    }
    return false;
  }
  public boolean exists(AbstractRoom room) {
    for (AbstractRoom r : list) {
      if (r.equals(room)) {
        return true;
      }
     }
    return false;
  }
}
