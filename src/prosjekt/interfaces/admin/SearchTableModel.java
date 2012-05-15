package prosjekt.interfaces.admin;


import javax.swing.table.AbstractTableModel;

/**
 * This is the search table model for all JTables used for search.
 * @author Even Augdal
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
