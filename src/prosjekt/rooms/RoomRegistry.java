/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import java.util.ArrayList;
import prosjekt.guests.AbstractGuest;

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
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (AbstractRoom r : list) {
      sb.append(r.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
  
  public String[] getRoomTypes() {
    String[] roomTypes = {"Enkeltrom", "Dobbeltrom", "Familierom"};
    return roomTypes;
  }
}
