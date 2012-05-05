/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class GuestBook {
  private HashMap<String, String> list = new HashMap<String, String>();
  
  private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
  }
  
  //TODO: VALIDERING
  public void add(String message) {
    list.put(getDateTime(), message);
    System.out.println("Added message: " + message);
  }
  /*
   * @return List of guests (all guests, for all history)
   */
  public HashMap<String, String> getList() {
    return list;
  } 
}
