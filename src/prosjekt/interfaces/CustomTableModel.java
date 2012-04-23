package prosjekt.interfaces;


import javax.swing.table.AbstractTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Even
 */
class CustomTableModel extends AbstractTableModel {
  private String[] columnNames;
  private String[][] rowData;
  
  public CustomTableModel(String[][] data, String[] columns) {
    columnNames = columns;
    rowData = data;
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
