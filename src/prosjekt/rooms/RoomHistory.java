/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import java.util.ArrayList;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class RoomHistory {
  // "Indeksert" etter from, to og room.
  private ArrayList<AbstractRoom> list = new ArrayList();
  
  public void add(AbstractRoom room) {
    list.add(room);
  }
  public ArrayList<AbstractRoom> getList() {
    return list;
  }
  /* 
   * Gets a list of previous AbstractRoom's for this room.
   * 
   * @param roomID ID of the room you want history for.
   * 
   * 
   * @return List containing the room history, or empty list.
   * 
   */
  
//  public boolean remove(AbstractRoom room) {
//    for (AbstractRoom r : list) {
//      if (r.equals(room)) {
//        list.remove(r);
//        return true;
//      }
//    }
//    return false;
//  }
//  public boolean exists(AbstractRoom room) {
//    for (AbstractRoom r : list) {
//      if (r.equals(room)) {
//        return true;
//      }
//     }
//    return false;
//  }
}
