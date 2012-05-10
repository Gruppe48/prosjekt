/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import java.util.ArrayList;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class RoomHistory {
  // "Indeksert" etter from, to og room.
  private ArrayList<AbstractRoom> list = new ArrayList();

  public RoomHistory() {
    if (Utils.fileExists("roomHistory.json")) {
      load();
    }
    else {
      save();
    }
  }
  
  
  public void save() {
    Utils.save(list, "roomHistory.json");
  }
  public void load() {
    list = (ArrayList<AbstractRoom>) Utils.load("roomHistory.json");
  }
  public void add(AbstractRoom room) {
    list.add(room);
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
  public ArrayList<AbstractRoom> getList() {
    return list;
  }
 
 
}
