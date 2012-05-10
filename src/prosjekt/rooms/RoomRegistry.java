/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import prosjekt.Main;
import prosjekt.utils.Utils;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.types.ConferenceRoom;
import prosjekt.rooms.types.DoubleRoom;
import prosjekt.rooms.types.MeetingRoom;
import prosjekt.rooms.types.SingleRoom;

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
    String[] roomTypes = {"Enkeltrom", "Dobbeltrom", "Konferanserom", "Møterom"};
    return roomTypes;
  }
  
  public ArrayList<AbstractRoom> searchRoom(String roomNumber, String roomType) {
    ArrayList<AbstractRoom> matches = new ArrayList();
    String rn;
    
    for(AbstractRoom r : list) {
      rn = r.roomID + "";
      if(rn.contains(roomNumber)) { 
        if(r instanceof SingleRoom && "Enkeltrom".equals(roomType)) { matches.add(r); }
        else if (r instanceof DoubleRoom && "Dobbeltrom".equals(roomType)) { matches.add(r); }
        else if (r instanceof ConferenceRoom && "Konferanserom".equals(roomType)) { matches.add(r); }
        else if (r instanceof MeetingRoom && "Møterom".equals(roomType)) { matches.add(r); }
      }
    }
    
    return matches;
  }
}
