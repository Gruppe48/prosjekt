/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.guests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class GuestBook {
  private static LinkedList<String> list = new LinkedList<String>();

  public GuestBook() {
     if (Utils.fileExists("guestBook.json")) {
      load();
    }
    else {
      // Setup default guestbook (i.e save it);
      save();
    }
  }
  
  public static void save() {
    Utils.save(list, "guestBook.json");
  }
  public static void load() {
    list = (LinkedList<String>) Utils.load("guestBook.json");
  }
  private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
  }
  
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
  /*
   * @return List of guests (all guests, for all history)
   */
  public LinkedList<String> getList() {
    return list;
  } 
}
