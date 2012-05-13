package prosjekt.utils;

import java.awt.Color;
import java.util.HashMap;
import prosjekt.IStorable;

/**
 * This is the Options class
 * This class is responsible for holding, loading and handling
 * all the options you wish to set for the program.
 * 
 * The default values are defined in init()
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * @date Mar 26, 2012
 */
public class Options implements IStorable {
  /**
   * This is the HashMap that holds all the options.
   */
  private HashMap<String, Object> options = new HashMap<String, Object>();
  
  /**
   * This is the Options constructor.
   * This calls init() the same as all the other classes
   * that implement IStoreable.
   */
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
  
  /**
   * This method gets a specific Option.
   * Remember to cast it back to what type of object it is!
   * @param key the Option name/key
   * @return A object of that option, rememberr to cast it!
   */
  public Object get(String key) {
    return options.get(key);
  }
  /**
   * This method sets a option
   * @param key the option name/key
   * @param obj the object/value to set.
   */
  public void set(String key, Object obj) {
    options.put(key, obj);
  }
  /**
   * This method checks if a option exists.
   * @param name the option name/key.
   * @return true/false, false if the option does not exist.
   */
  public boolean exists(String name) {
    
    for (String keyName : options.keySet()) {
      if (keyName.equals(name)) {
        return true;
      }
    }
    return false;
  }
}
