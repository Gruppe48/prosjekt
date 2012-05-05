/*
 */
package prosjekt.utils;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net> @studnr 180212 @date Mar 26, 2012
 */
public class Utils {

    public static boolean fileExists(String filename) {
        File f = new File(filename);
        return f.exists();
    }

    public static void saveObject(Object o, String filename) {
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

    public static Object loadObject(String filename) {
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
            } finally {
                jr.close();
            }
            return out;
        }
    }

    public static String readFile(String filename) {
        StringBuilder output = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Impossible to read " + filename + ", error: ");
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void writeToFile(String data, String filename) {

        BufferedWriter bufferedWriter = null;

        try {

            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(filename));


            //Start writing to the output stream
            bufferedWriter.write(data);

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
