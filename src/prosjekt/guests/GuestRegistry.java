/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.guests;

import java.util.ArrayList;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class GuestRegistry {
  // "Indeksert" etter from, to og room.
  private ArrayList<AbstractGuest> list = new ArrayList();
  
  /*
   * @param AbstractGuest guest
   * @returns true/false
   * 
   */
  //TODO: VALIDERING
  public boolean add(AbstractGuest guest) {
    if (exists(guest)) {
      return false;
    }
    list.add(guest);
    return true;
  }
  /*
   * @return List of guests (all guests, for all history)
   */
  public ArrayList<AbstractGuest> getList() {
    return list;
  }
  /*
   * @param guest guest to remove!
   * @return true/false om gjesten ble fjernet/fantes!
   */
  public boolean remove(AbstractGuest guest) {
    for (AbstractGuest g : list) {
      if (g.equals(guest)) {
        list.remove(g);
        return true;
      }
    }
    return false;
  }
  /*
   * @param firstName Fornavn til gjest du skal finne
   * @param lastName  Etternavn til gjest du skal finne.
   * @param phoneNumber Telefonnummer til gjest du skal finne.
   */
  //TODO: Normaliser telefonnummer med regulært utrykk: Hvis noen skriver f.eks 93 82 81 06 må vi kunne matche det mot 93828106 osv.
  public AbstractGuest getGuest(String firstName, String lastName, String phoneNumber) {
    for (AbstractGuest g : list) {
      if (g.getFirstName().equals(firstName) && g.getLastName().equals(lastName) && g.getPhoneNumber().equals(phoneNumber)) {
        return g;
      }
    }
    return null;
  }
  /*
   * @param guest Guest to find
   * @returns AbstractGuest guest
   */
  public AbstractGuest getGuest(AbstractGuest guest) {
    for (AbstractGuest g : list) {
      if (g.equals(guest)) {
        return g;
      }
    }
    return null;
  }
  /*
   * @param AbstractGuest guest, guest to find
   * @return true/false
   */
  public boolean exists(AbstractGuest guest) {
    for (AbstractGuest g : list) {
      if (g.getID() == guest.getID()) {
        return true;
      }
     }
    return false;
  }
  
  /*
   * @param firstName, string to search for as a substring of firstname
   * @param lastName, string to search for as a substring of lastname
   * @param phoneNumber, string to search for as a substring of phoneNumber
   * @param address, string to search for as a substring of address
   * @param postNumber, int to match a guest's postNumber
   * 
   * @returns an ArrayList of matching guests or null if there is no matches.
   */
  public ArrayList<AbstractGuest> searchGuests(String firstName, String lastName, String phoneNumber, String address, int postNumber, String company) {
    ArrayList<AbstractGuest> matches = new ArrayList();
    
    /*
    for (AbstractGuest g : list) {
      
      if(g instanceof Company) {
        Company c = (Company) g;
        if(company.length() > 0 && c.getCompanyName().toLowerCase().contains(company.toLowerCase())) {
          matches.add(g);
        }
        else if(firstName.length()==0 && lastName.length()==0 && phoneNumber.length()==0 && address.length()==0 && (g.getPostNumber()==postNumber || postNumber==0)) {
          matches.add(g);
        }
      }
      else if(g.getFirstName().toLowerCase().contains(firstName.toLowerCase()) && g.getLastName().toLowerCase().contains(lastName.toLowerCase()) &&
              g.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()) && g.getAddress().toLowerCase().contains(address.toLowerCase()) &&
              (g.getPostNumber()==postNumber || postNumber==0)) {
        matches.add(g);
      }*/
      
    for (AbstractGuest g : list) {
      if(g instanceof Company) {
          
          Company c = (Company) g;
          
          if(company.length() > 0 && c.getCompanyName().toLowerCase().contains(company.toLowerCase())) {
            matches.add(g);
          }
          
          if (c.getPostNumber()==postNumber) {
            System.out.println(c.getCompanyName());
            matches.add(c);
          }
      }
      
      if(g.getFirstName().toLowerCase().contains(firstName.toLowerCase()) && g.getLastName().toLowerCase().contains(lastName.toLowerCase()) &&
              g.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()) && g.getAddress().toLowerCase().contains(address.toLowerCase()) &&
              (g.getPostNumber()==postNumber || postNumber==0)) {
        
        if(g instanceof Company) {
          
          Company c = (Company) g;
          
          if (c.getPostNumber()==postNumber) {
            matches.add(c);
          }
          
          if(company.length() > 0 && c.getCompanyName().toLowerCase().contains(company.toLowerCase())) {
            matches.add(c);
            break;
          }
          else if (c.getPostNumber()==postNumber) {
            matches.add(c);
            break;
          }
          
        }
       
        if(company.length() == 0) {
          matches.add(g);
        }
      }
      
      
    }
    
    return (matches.isEmpty()) ? null : matches;
  }
}
