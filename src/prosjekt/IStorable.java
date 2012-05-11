/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt;

/**
 *
 * @author kristoffer
 */
public interface IStorable {
  
  /*
   * 
   * Saves what you want the class to store.
   * 
   */
  public void save();
  /*
   * 
   * Loads what you have saved previously!
   * 
   */
  public void load();
  /*
   * 
   * This method should check if the file exists -- if it does: Load the file with load(), if not: save the file with save().
   * This should probably have been setup in a abstract class that all the classes in need should extend.
   * But since it's checking for a different file each time, I opted to implement this independently per class.
   * 
   */
  public void init();
}
