package view.update;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sqlite3.PersonInfo;


public class UpdateTable {
	private DefaultTableModel tableModel = null;
	public void updateTable(JTable table, List<String> collection) {
		tableModel = (DefaultTableModel)table.getModel();
		tableModel.addRow(collection.toArray());
		table.scrollRectToVisible(table.getCellRect(tableModel.getRowCount()-1, 0, true));
	}
	
	public void updateTableFromPersonInfoList(JTable table, List<PersonInfo> list) {
		tableModel = (DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		if (!list.isEmpty()) {
			for (PersonInfo pi : list) {
				tableModel.addRow(pi.toVector());
			}
			table.scrollRectToVisible(table.getCellRect(tableModel.getRowCount()-1, 0, true));
		}
		
	}
	
	public void cleanTable(JTable table) {
		tableModel = (DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
	}
}
