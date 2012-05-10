/*
 */
package prosjekt.utils;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Mar 26, 2012
 */
public class Utils {
  
  public static boolean fileExists(String filename) {
    File f = new File(filename);
    return f.exists();
  }
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
  
  public static String read(String filename) {
    StringBuilder output = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line = "";
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
}
