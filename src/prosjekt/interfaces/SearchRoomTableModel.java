/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjekt.interfaces;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import prosjekt.guests.AbstractGuest;
import prosjekt.rooms.AbstractRoom;

/**
 *
 * @author Even
 */
public class SearchRoomTableModel extends AbstractTableModel {
  private String[] columnNames;
  private String[][] rowData;
  ArrayList<AbstractGuest> guestList;
  ArrayList<AbstractRoom> roomList;

  public SearchRoomTableModel(ArrayList<AbstractRoom> rooms, String[] columns) {
    roomList    = rooms;
    columnNames = columns;
    
    
    if(rooms != null) {
      rowData = new String[rooms.size()][3];
      
      int i = 0;
      for (AbstractRoom r : rooms) {
        rowData[i][0] = r.getID() + "";
        rowData[i][1] = r.getPrice() + "";
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
