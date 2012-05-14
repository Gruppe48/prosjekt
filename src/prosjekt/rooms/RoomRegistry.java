package prosjekt.rooms;

import java.util.ArrayList;
import prosjekt.IStorable;
import prosjekt.rooms.types.SingleRoom;
import prosjekt.utils.Utils;

/**
 * This is the RoomRegistry class.
 * This method is responsible for holding and keeping track of
 * all the hotels different rooms.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class RoomRegistry implements IStorable {
   /**
   * The list of rooms.
   */
  private ArrayList<AbstractRoom> list = new ArrayList();
   /**
   * A instance of our RoomHistory class to hold history.
   */
  private RoomHistory history = new RoomHistory();

   /**
   * This is the RoomRegistry constructor.
   * It calls init() as all classes implementing
   * IStoreable do.
   */
  public RoomRegistry() {
    init();
  }
  @Override
  public final void init() {
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
  @Override
  public void save() {
    Utils.save(list, "roomRegistry.json");
    history.save();
  }
  @Override
  public void load() {
    list = (ArrayList<AbstractRoom>) Utils.load("roomRegistry.json");
    history.load();
  }
  
   /**
   * This method adds a room to the room list.
   * The room will only be added if it does not already exist in the list.
   * @param room The room to add
   * @return true/false based on success.
   */
  public boolean add(AbstractRoom room) {
    if (!exists(room)) {
          list.add(room);
          history.add(room);
          return true;
    }
    return false;
  }
   /**
   * This method returns the list of rooms.
   * @return  The room list.
   */
  public ArrayList<AbstractRoom> getList() {
    return list;
  }
   /**
   * This method returns a list of rooms of the specified type.
   * @param type the type of room we want a list of.
   * @return A list of all the rooms of that type, or a empty list if there are none.
   */
  public ArrayList<AbstractRoom> getRoomsByType(String type) {
    ArrayList<AbstractRoom> matches = new ArrayList<AbstractRoom>();
    for (AbstractRoom r : list) {
      if (r.getRoomType().equals(type)) {
        matches.add(r);
      }
    }
    return matches;
  } 
   /**
   * This method removes a specific room from the list, provided it exists.
   * 
   * @param room the room to remove.
   * @return true or false, false if the room does not exist.
   */
  public boolean remove(AbstractRoom room) {
    for (AbstractRoom r : list) {
      if (r.equals(room)) {
        list.remove(r);
        return true;
      }
    }
    return false;
  }
   /**
   * This method checks if a specific room exists in the list.
   * @param room the room to check for.
   * @return true/false, false if the room does not exist in the list.
   */
  public boolean exists(AbstractRoom room) {
    for (AbstractRoom r : list) {
      if (r.equals(room)) {
        return true;
      }
     }
    return false;
  }
  
   /**
   * This method returns a printable string of all the rooms in the list.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (AbstractRoom r : list) {
      sb.append(r.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
  
   /**
   * This method returns a array of strings with the different room types in the hotel.
   * This is for the administration GUI.
   * @return A array of strings with room types.
   */
  public String[] getRoomTypes() {
    String[] roomTypes = {"Enkeltrom", "Dobbeltrom", "Konferanserom", "Møterom"};
    return roomTypes;
  }
   /**
   * This method searches for a specific room based on room type and roomID.
   * @param roomNumber the roomNumber/roomID of the room you want.
   * @param roomType the type of room you are looking for to filter the results a bit quicker.
   */
  public ArrayList<AbstractRoom> searchRoom(String roomNumber, String roomType) {
    ArrayList<AbstractRoom> matches = new ArrayList();
    String rn;
    
    for(AbstractRoom r : list) {
      rn = r.roomID + "";
      if(rn.contains(roomNumber) && r.getRoomType().equals(roomType)) { 
        matches.add(r);
      }
    }
    
    return matches;
  }
}
