/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.guests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import prosjekt.IStorable;
import prosjekt.utils.Utils;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @since 2012-04-16
 */
public class GuestRegistry implements IStorable {

  private HashMap<String, AbstractGuest> list = new HashMap<String, AbstractGuest>();

  public GuestRegistry() {
    init();
  }
  @Override
  public final void init() {
     if (Utils.fileExists("guestRegistry.json")) {
      load();
    }
    else {
      // Default values
      ArrayList<AbstractGuest> guests = new ArrayList<AbstractGuest>();
      // 10 people
      guests.add(new Person("Even", "Augdal", "12345678", "Skoleveien 1", 1000));
      guests.add(new Person("Kristoffer", "Berdal", "93828106", "Skoleveien 2", 1000));
      guests.add(new Person("Ole", "Hansen",   "38383838", "Skoleveien 5", 1000));
      guests.add(new Person("Ole", "Augdal",   "99999999", "En vei 2", 4000));
      guests.add(new Person("Jens", "Knutsen", "48586949", "Skoleveien 389", 5000));
      guests.add(new Person("Hanna", "Jonsen", "13131313", "Byveien 2", 2222));
      guests.add(new Person("Even", "Halvorsen", "14949494", "Skogveien 50", 7070));
      guests.add(new Person("Tommy", "Nyrud", "50392342", "Fjellveien 3", 5883));
      guests.add(new Person("Lise", "Olsen", "16969482", "Skoleveien 19", 1000));
      guests.add(new Person("Marie", "Olsen", "15838292", "Skoleveien 19", 1000));
      
      // 5 companies
      guests.add(new Company("Sergey", "Brin", "10203040", "Ampfitheatre Parkway", 1600, "Google"));
      guests.add(new Company("Steve", "Jobs", "30405060", "Infinite Loop 1", 1337, "Apple"));
      guests.add(new Company("Steve", "Balmer", "92939106", "Microsoft road 1", 0101, "Microsoft"));
      guests.add(new Company("Jeff", "Bezos", "30284020", "Amazonas 1", 1593, "Amazon"));
      guests.add(new Company("Paul", "Graham", "13371337", "San Franscisco road 1", 4022, "YCombinator"));
      
      // Loop through and add them all.
      for (AbstractGuest g : guests) {
        add(g);
      }
      save();
    }
  }
  @Override
  public void save() {
    Utils.save(list, "guestRegistry.json");
  }
  @Override
  public void load() {
    list = (HashMap<String, AbstractGuest>) Utils.load("guestRegistry.json");
  }
  
  /*
   * @param AbstractGuest guest
   * @return String "hash" based on guest.
   * 
   */
  //TODO: VALIDERING
  public String getHash(AbstractGuest guest) {
    StringBuilder output = new StringBuilder();
    // This is not the best way of making a hash, and should probably be refactored in a future version. Changing a guests details will result in a new hash, which means that guests bookings and everything will need to be changed to the new hash.
    
    // Fetch the first name, last name and phone number to create a unique "hash" to identify a guest.
    output.append(guest.getFirstName());
    output.append(guest.getLastName());
    output.append(guest.getPhoneNumber());
    return output.toString();
  }

  public boolean add(AbstractGuest guest) {
    if (exists(guest)) {
      return false;
    }
    String hash = getHash(guest);
    list.put(hash, guest);
    System.out.println("Added guest with hash: " + hash);
    return true;
  }
  /*
   * @return List of guests (all guests, for all history)
   */

  public HashMap<String, AbstractGuest> getList() {
    return list;
  }
  /*
   * @param guest guest to remove!
   * @return true/false om gjesten ble fjernet/fantes!
   */

  public boolean remove(AbstractGuest guest) {
    boolean result = false;
    String hash = getHash(guest);
    if (list.remove(hash) != null) {
      System.out.println("Removed guest with hash: " + hash);
      result = true;
    }
    return result;
  }
  /*
   * @param firstName Fornavn til gjest du skal finne
   * @param lastName  Etternavn til gjest du skal finne.
   * @param phoneNumber Telefonnummer til gjest du skal finne.
   */
  //TODO: Normaliser telefonnummer med regulært utrykk: Hvis noen skriver f.eks 93 82 81 06 må vi kunne matche det mot 93828106 osv.

  public AbstractGuest getGuest(String firstName, String lastName, String phoneNumber) {
    String hash = firstName + lastName + phoneNumber;
    return list.get(hash);
  }
  /*
   * @param guest Guest to find
   * @returns AbstractGuest guest
   */

  public AbstractGuest getGuest(AbstractGuest guest) {
    return list.get(getHash(guest));
  }
  
  public AbstractGuest getGuest(int i) {
    for (AbstractGuest g : list.values()) {
      if(g.getID() == i) {
        return g;
      }
    }
    return null;
  }

  public boolean swapGuest(AbstractGuest oldGuest, AbstractGuest newGuest) {
    ListIterator listIterator = (ListIterator) list.values().iterator();

    while (listIterator.hasNext()) {
      if (listIterator.next().equals(oldGuest)) {
        listIterator.set(newGuest);
        return true;
      }
    }
    return false;
  }

  /*
   * @param AbstractGuest guest, guest to find
   * @return true/false
   */
  public boolean exists(AbstractGuest guest) {
    return list.containsKey(getHash(guest));
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
    
    for (AbstractGuest g : list.values()) {
      if(g.getFirstName().toLowerCase().contains(firstName.toLowerCase()) && g.getLastName().toLowerCase().contains(lastName.toLowerCase()) &&
              g.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()) && g.getAddress().toLowerCase().contains(address.toLowerCase()) &&
              (g.getPostNumber()==postNumber || postNumber==0)) {
        
        if(g instanceof Company) {
          Company c = (Company) g;
          
          if(company.length()>0 && c.getCompanyName().toLowerCase().contains(company.toLowerCase())) {
            matches.add(c);
            break;
          }
          else if (c.getPostNumber()==postNumber) {
            matches.add(c);
            break;
          }
          
        }
        
        // if no companyname is specified, we should show the element
        if(company.length() == 0) {
          matches.add(g);
        }
      }
 
    }
    return (matches.isEmpty()) ? null : matches;
  }

  @Override
  public String toString() {
  StringBuilder r = new StringBuilder();
    for (AbstractGuest g : list.values()) {
      r.append(g.toString());
      r.append("\n");
    }
    return r.toString();
  }
}
