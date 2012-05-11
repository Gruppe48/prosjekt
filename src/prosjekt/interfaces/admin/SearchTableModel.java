package prosjekt.interfaces.admin;


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
  
  
  public SearchTableModel(String[][] rowData, String[] columnNames) {
    this.columnNames = columnNames;
    this.rowData = rowData;
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
