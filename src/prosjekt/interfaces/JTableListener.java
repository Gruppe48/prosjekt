package prosjekt.interfaces;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Even
 */
class JTableListener implements ListSelectionListener {
  
  JTable table;

  JTableListener(JTable t) {
    table = t;
  }
  
  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
      int first = e.getFirstIndex();
      int last = e.getLastIndex();
      System.out.println(table.getSelectedRow());
    } 
    else if (e.getSource() == table.getColumnModel().getSelectionModel() && table.getColumnSelectionAllowed()) {
      int first = e.getFirstIndex();
      int last = e.getLastIndex();
      System.out.println("hmm.. " + first + " " + last);
    }
  }
  
}