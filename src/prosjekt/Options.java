/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import prosjekt.utils.Utils;

/**
 *
 * @author kristoffer
 */
public class Options {
  
  
  private LinkedList<Option> options = new LinkedList<Option>();

  public Options() {
    if (Utils.fileExists("options.json")) {
      load();
    }
    else {
      // Setup default options
      // UI colors
      addOption("uiMainColor", Color.LIGHT_GRAY);
      
      save();
    }
  }
  
  
  public void save() {
    Utils.save(options, "options.json");
  }

  public void load() {
    options = (LinkedList<Option>) Utils.load("options.json");
  }

  public void addOption(String name, Object initial) {
    Option o = new Option(name, initial);
    if (!exists(name)) {
      options.add(o);
    }
  }
  public boolean exists(String name) {
    for (Option o : options) {
      if (o.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }
  public void setOption(String name, Object value) {
    for (Option o : options) {
      if (o.getName().equals(name)) {
        o.setValue(value);
      }
    }
  }
  public Object getOption(String name) {
    Object out = null;
    for (Option o : options) {
      if (o.getName().equals(name)) {
        System.out.println("Fetching value for: " + o.getName());
        out = o.getValue();
        System.out.println("Value is: " + out);
      }
    }
    return out;
  }
  
  // Option class
  
  private class Option {
    private String name;
    private Object value;
    
    
    private Option(String name, Object initial) {
      this.name = name;
      this.value = initial;
    }

    public String getName() {
      return name;
    }
    public Object getValue() {
      return value;
    }
    public void setValue(Object v) {
      this.value = v;
    }
  }
}
