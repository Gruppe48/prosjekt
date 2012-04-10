/*
 */
package prosjekt.guests.privateGuest;

import prosjekt.guests.AbstractGuest;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 29, 2012
 */
public class PrivateGuest extends AbstractGuest implements IPrivate {
          
  public PrivateGuest (String fName, String lName, int phoneNr, String addr, int postNr, String c) { 
    super(fName, lName, phoneNr, addr, postNr, c);
  }

  
}
