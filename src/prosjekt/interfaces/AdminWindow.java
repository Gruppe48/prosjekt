/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author kristoffer
 */
public class AdminWindow extends GenericWindow {
  JLabel dateFromLabel, dateToLabel, firstNameLabel, lastNameLabel, adressLabel, cityLabel, postNumberLabel;
  JTextField usernameField, dateFromField, dateToField, firstNameField, lastNameField, adressField, cityField, postNumberField;
  JPasswordField password;
  JButton login, cancel;
  String[] bedOptions = {"1","2","3","4"};
  JComboBox beds = new JComboBox(bedOptions);
  
  public AdminWindow() {
    super("Admin window", 800, 800);
  }

  @Override
  public void create() {
    super.create();
    Container c = getContentPane();
    c.setLayout( new GridLayout(7,2) );
    
    dateFromLabel = new JLabel("Dato fra:");
    dateFromField = new JTextField(5);
    dateToLabel = new JLabel ("Dato til:");
    dateToField = new JTextField(5);
    firstNameLabel = new JLabel("Fornavn:");
    firstNameField = new JTextField(5);
    lastNameLabel  = new JLabel("Etternavn:");
    lastNameField = new JTextField(5);
    adressLabel = new JLabel("Adresse:");
    adressField = new JTextField(5);
    cityLabel = new JLabel("By:");
    cityField = new JTextField(5);
    postNumberLabel = new JLabel("Postnummer:");
    postNumberField = new JTextField(5);
   
    c.add(dateFromLabel);
    c.add(dateFromField);
    c.add(dateToLabel);
    c.add(dateToField);
    c.add(firstNameLabel);
    c.add(firstNameField); 
    c.add(lastNameLabel);
    c.add(lastNameField);
    c.add(adressLabel);
    c.add(adressField);
    c.add(cityLabel);
    c.add(cityField);
    c.add(postNumberLabel);
    c.add(postNumberField);
    
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void buttonPressed(ActionEvent e) {
    super.buttonPressed(e);
    
  }
  
  

}
