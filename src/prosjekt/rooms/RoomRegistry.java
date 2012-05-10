/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.rooms;

import java.util.ArrayList;
import prosjekt.rooms.types.ConferenceRoom;
import prosjekt.rooms.types.DoubleRoom;
import prosjekt.rooms.types.MeetingRoom;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.utils.Utils;

/**
 *
 * @author kristoffer
 */
public class RoomRegistry {
  // "Indeksert" etter from, to og room.
  private ArrayList<AbstractRoom> list = new ArrayList();
  private RoomHistory history = new RoomHistory();

  public RoomRegistry() {
    if (Utils.fileExists("roomRegistry.json")) {
      load();
    }
    else {
      for (int i = 0; i < 10; i++) {
        SingleRoom room = new SingleRoom();
        list.add(room);
      }
      save();
    }
  }
  public void save() {
    Utils.save(list, "roomRegistry.json");
    history.save();
  }
  public void load() {
    list = (ArrayList<AbstractRoom>) Utils.load("roomRegistry.json");
    history.load();
  }
  
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
  public ArrayList<AbstractRoom> getRoomsByType(String type) {
    ArrayList<AbstractRoom> matches = new ArrayList<AbstractRoom>();
    for (AbstractRoom r : list) {
      if (r.getRoomType().equals(type)) {
        matches.add(r);
      }
    }
    return matches;
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
