package prosjekt.guests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import prosjekt.IStorable;
import prosjekt.utils.Utils;

/**
 * This is the GuestBook class, responsible for handling
 * and storing guest book messages.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class GuestBook implements IStorable {
  /**
   * This list holds all the guest book messages.
   */
  private static LinkedList<String> list = new LinkedList<String>();

  /**
   * This is the constructor for GuestBook,
   * as per all classes implementing IStoreable it calls
   * init() to load the guestBook or setup a default.
   */
  public GuestBook() {
    init();
  }

  @Override
  public final void init() {
    // If the file exists, load the guestbook data.
    if (Utils.fileExists("guestBook.json")) {
      load();
    }
    else {
      // Setup default guestbook (i.e save it);
      save();
    }
  }
  
  @Override
  public void save() {
    Utils.save(list, "guestBook.json");
  }
  @Override
  public void load() {
    list = (LinkedList<String>) Utils.load("guestBook.json");
  }
  /**
   * This method gets the current date in the 
   * format of 2012-05-13
   * @return The date formatted as yyyy-MM-dd
   */
  private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
  }
  /**
   * This method adds a message to the guest book,
   * provided the message is under 250 characters.
   * @param message The message to be added.
   * @return true or false, false if the message is longer than 250 characters.
   */
  public boolean add(String message) {
    if (message.length() < 250) {
      StringBuilder fmt = new StringBuilder();
      fmt.append(getDateTime());
      fmt.append("\n");
      fmt.append(message);
      fmt.append("\n");
      list.add(fmt.toString());
      save();
      return true;
    }
    // If we get here, the message is longer than 250 characters and we did not add it.
    return false;
  }
  /**
   * @return List of guests (all guests, for all history)
   */
  public LinkedList<String> getList() {
    return list;
  } 
}
