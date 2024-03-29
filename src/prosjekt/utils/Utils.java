package prosjekt.utils;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import java.awt.Component;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This is the Utils class.
 * This class contains methods for loading and saving objects (JSON serialization). 
 * As well as methods for reading and writing regular files (text strings).
 * We also have a few methods to show popup messages/dialogues.
 * 
 * @author Kristoffer Berdal <web@flexd.net>
 * 
 */
public class Utils {
  /**
   * This method returns the difference between two dates in days.
   * Returns -1 if the endDate is before the startDate.
   * @param startDate The start date
   * @param endDate The end date
   * @return days between these dates.
   */
  public static int getDifference(Date startDate, Date endDate) {
    if (endDate.before(startDate)) return -1; //The end date is before the start date.. Clearly wrong!
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    start.setTime(startDate);
    end.setTime(endDate);
    long startMillis = start.getTimeInMillis();
    long endMillis = end.getTimeInMillis();

    // Calculate the difference
    long diff = endMillis - startMillis;
    // Divide by amount of milliseconds in 24 hours.
    int diffDays = (int)(diff / (24 * 60 * 60 * 1000));
    return diffDays;
  }
  /**
   * This method shows a JOptionPane error message
   * @param c the component (i.e the host window).
   * @param m the message to show
   * @param title the title to show.
   */
  public static void showErrorMessage(Component c, String m, String title) {
    JOptionPane.showMessageDialog(c, m, title,
        JOptionPane.ERROR_MESSAGE);
  }
  /**
   * This method shows a JOptionPane information message
   * @param c the component (i.e the host window).
   * @param m the message to show
   * @param title the title to show.
   */
  public static void showInformationMessage(Component c, String m, String title) {
    JOptionPane.showMessageDialog(c, m, title,
        JOptionPane.INFORMATION_MESSAGE);
  }
  /**
   * This method shows a JOptionPane warning message
   * @param c the component (i.e the host window).
   * @param m the message to show
   * @param title the title to show.
   */
  public static void showWarningMessage(Component c, String m, String title) {
    JOptionPane.showMessageDialog(c, m, title,
        JOptionPane.WARNING_MESSAGE);
  }
  /**
   * This method checks if a file exists.
   * @param filename the filename to check for.
   * @return true/false, false if the file does not exist.
   */
  public static boolean fileExists(String filename) {
    File f = new File(filename);
    return f.exists();
  }
  /**
   * This method saves/serializes a object to a JSON file.
   * @param o the object to save.
   * @param filename the filename to save to, could be named anything but the format is JSON.
   */
  public static void save(Object o, String filename) {
    {
      JsonWriter jw = null;
      try {
        OutputStream out;
        out = new FileOutputStream(new File(filename));
        jw = new JsonWriter(out);
        jw.write(o);
      } catch (IOException ex) {
        Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        jw.close();
      }
    }
  }
  /**
   * This method loads a previously JSON serialized object from a file.
   * Remember to cast it back to what object type is!
   * @param filename the filename to load.
   * @return The object loaded!
   */
  public static Object load(String filename) {
    {
      JsonReader jr = null;
      Object out = null;
      try {
        InputStream in;
        in = new FileInputStream(new File(filename));
        jr = new JsonReader(in);
        out = jr.readObject();
      } catch (FileNotFoundException ex) {
        Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      } 
      finally {
        jr.close();
      }
      return out;
    }
  }
  /**
   * This method reads a file and returns the string.
   * @param filename the file to read.
   * @return The full file in a string.
   */
  public static String read(String filename) {
    StringBuilder output = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append("\n");
      }
      
    }
    catch (IOException e) {
      System.err.println("Exception reading " + filename);
      e.printStackTrace();
    }
    return output.toString();
  }
  /**
   * This method writes a string to a file.
   * @param filename the file to write to.
   * @param data the string we want to write to file.
   */
  public static void write(String filename, String data) {
        
        BufferedWriter bufferedWriter = null;
        if (data == null) { System.err.println("Data er null!");
        return;
        }
        try {
            
            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
            
            //Start writing to the output stream
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedWriter
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
