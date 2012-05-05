/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.EditorKit;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;
import prosjekt.Main;
import prosjekt.utils.Utils;

/**
 *
 * @author kristoffer
 */
public class GuestWindow extends GenericWindow {
  private JButton home, facilities, food, activities, guestbook;
  private JPanel buttons, contentarea;
  private JTextArea output;
  private Color uiMainColor;
  public static final String ROOT_PATH = "assets/guests/";
  
  public GuestWindow() {
    super("Guest window", 800, 400);
  }

  @Override
  public void create() {
    super.create();
        setUiMainColor((Color)Main.options.get("uiMainColor"));
    Container c = getContentPane();
    c.setLayout( new GridBagLayout() );
    
    this.setBackground(getUiMainColor());
    buttons = new JPanel();
    contentarea = new JPanel();
    
    output = new JTextArea();
    output.setEditable(false);
    // Initial "front page" value for the output
    output.setText(Utils.readFile(ROOT_PATH+"index.txt"));
    
    buttons.setLayout(new GridLayout(20, 1));
    contentarea.setLayout(new GridLayout(1,10));
    
    contentarea.add(output);
    
    home = new JButton("Hjem");
    facilities = new JButton("Fasiliter");
    food = new JButton("Mat");
    activities = new JButton("Aktiviteter");
    guestbook = new JButton("Gjestebok");
    
    buttons.add(home);
    buttons.add(facilities);
    buttons.add(food);
    buttons.add(activities);
    buttons.add(guestbook);
   
    home.addActionListener(buttonListener);
    facilities.addActionListener(buttonListener);
    food.addActionListener(buttonListener);
    activities.addActionListener(buttonListener);
    guestbook.addActionListener(buttonListener);
    
    
    GridBagConstraints g = new GridBagConstraints();
    // Buttons
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.HORIZONTAL;
    g.anchor = GridBagConstraints.FIRST_LINE_START;
    g.gridx = 0;
    g.gridy = 0;
    g.weightx = 0;
    g.weighty = 0;
    c.add(buttons, g);
    // Output
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.BOTH;
    g.anchor = GridBagConstraints.PAGE_START;
    g.gridx = 1;
    g.gridy = 0;
    g.weightx = 1;
    g.weighty = 1;
    
    c.add(contentarea, g);
 
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    if (e.getSource() == home) {
        output.setText(Utils.readFile(ROOT_PATH+"index.txt"));
    }
    else if (e.getSource() == facilities) {
        output.setText(Utils.readFile(ROOT_PATH+"facilities.txt"));
    }
    else if (e.getSource() == food) {
        output.setText(Utils.readFile(ROOT_PATH+"food.txt"));
    }
    else if (e.getSource() == activities) {
        output.setText(Utils.readFile(ROOT_PATH+"activities.txt"));
    }
    else if (e.getSource() == guestbook) {
       //TODO: Last gjestebok vindu her!
        System.out.println("Open guestbook window!");
    }
  }

    /**
     * @return the uiMainColor
     */
    public Color getUiMainColor() {
        return uiMainColor;
    }

    /**
     * @param uiMainColor the uiMainColor to set
     */
    public void setUiMainColor(Color uiMainColor) {
        this.uiMainColor = uiMainColor;
    }
}
