/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.utils;

import java.awt.Color;
import java.util.HashMap;
import prosjekt.utils.Utils;
import prosjekt.IStorable;
/**
 *
 * @author kristoffer
 */
public class Options implements IStorable {
  
  
  //private LinkedList<Option> options = new LinkedList<Option>();
  private HashMap<String, Object> options = new HashMap<String, Object>();
  public Options() {
    init();
  }
  
   @Override
  public final void init() {
    if (Utils.fileExists("options.json")) {
      load();
    }
    else {
      // Setup default options
      // UI colors
      options.put("uiMainColor", Color.LIGHT_GRAY);
      
      save();
    }
  }
   @Override
  public void save() {
    Utils.save(options, "options.json");
  }

  @Override
  public final void load() {
    options = (HashMap<String, Object>) Utils.load("options.json");
  }
  
  public Object get(String key) {
    return options.get(key);
  }
  public void set(String key, Object obj) {
    options.put(key, obj);
  }
  public boolean exists(String name) {
    
    for (String keyName : options.keySet()) {
      if (keyName.equals(name)) {
        return true;
      }
    }
    return false;
  }
}
