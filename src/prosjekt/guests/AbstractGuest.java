/*
 */
package prosjekt.guests;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class AbstractGuest implements IGuest {
  private static int guestID = 0; 
  
  public AbstractGuest() {
    guestID += 1;
  }
  
  @Override
  public int getGuestID() {
    return guestID;
  }
  
}
