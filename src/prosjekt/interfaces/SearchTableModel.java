package prosjekt.interfaces;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import prosjekt.guests.AbstractGuest;
import prosjekt.guests.Company;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Even
 */
class SearchTableModel extends AbstractTableModel {
  private String[] columnNames;
  private String[][] rowData;
  ArrayList<AbstractGuest> guestList;
  
  /*
  public CustomTableModel(String[][] data, String[] columns) {
    columnNames = columns;
    rowData = data;
  }*/
  
  public SearchTableModel(ArrayList<AbstractGuest> guests, String[] columns) {
    guestList = guests;
    columnNames = columns;
    
    if(guests != null) {
      rowData = new String[guests.size()][5];
      
      int i = 0;
      for (AbstractGuest g : guests) {
        rowData[i][0] = g.getFirstName();
        rowData[i][1] = g.getLastName();
        rowData[i][2] = g.getPhoneNumber();
        rowData[i][3] = g.getAddress();
        if(g instanceof Company) {
          Company c = (Company) g;
          rowData[i][4] = c.getCompanyName();
        }
        i++;
      }
    }
  }
  
  @Override
  public int getColumnCount() {
    try {
      return columnNames.length;
    }
    catch(NullPointerException npe) {
      return 0;
    }
  }

  @Override
  public int getRowCount() {
    try {
      return rowData.length;
    }
    catch(NullPointerException npe) {
      return 0;
    }
  }

  @Override
  public String getColumnName(int col) {
    return columnNames[col];
  }

  @Override
  public Object getValueAt(int row, int col) {
    return rowData[row][col];
  }
  
  @Override
  public boolean isCellEditable(int row, int col) {
        return false;
    }
  
}
