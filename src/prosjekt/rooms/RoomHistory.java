package prosjekt.rooms;

import java.util.ArrayList;
import prosjekt.IStorable;
import prosjekt.utils.Utils;

/**
 * This is the RoomHistory class.
 * This class holds the RoomHistory (if anything was to change in the rooms).
 * Currently nothing can be changed, so this is mostly here for future enhancements.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class RoomHistory implements IStorable {
   /**
   * This list holds the AbstractRooms (i.e revisions of each room).
   */
  private ArrayList<AbstractRoom> list = new ArrayList();

   /**
   * This is the RoomHistory constructor.
   * This constructor calls init() just as any other class
   * implementing IStoreable.
   */
  public RoomHistory() {
    init();
  }
  @Override
  public final void init() {
    if (Utils.fileExists("roomHistory.json")) {
      load();
    }
    else {
      save();
    }
  }
  
  @Override
  public void save() {
    Utils.save(list, "roomHistory.json");
  }
  @Override
  public void load() {
    list = (ArrayList<AbstractRoom>) Utils.load("roomHistory.json");
  }
  
   /**
   * This method adds a room to the list,
   * there is no validation because that
   * should be taken care of in RoomRegistry.
   * 
   * @param room the room to add to history.
   */
  public void add(AbstractRoom room) {
    list.add(room);
  }
   /**
   * This method gets a list of previous AbstractRoom's for this room.
   * 
   * @param roomID ID of the room you want history for.
   * @return List containing the room history, or empty list.
   * 
   */
  public ArrayList<AbstractRoom> getList() {
    return list;
  }
}
