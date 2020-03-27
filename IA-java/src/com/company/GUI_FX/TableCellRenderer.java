package com.company.GUI_FX;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * @Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = -3936812983211466205L;
	private List<Integer> results;
	
	public TableCellRenderer() {
		super();
	}
	public TableCellRenderer(List<Integer> results) {
		super();
		this.results = results;
	}
	/**
	 * 隔行换色
	 * 
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
	 *      (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		//if (row % 2 == 0) {
		//if ((row * 7 + column)%2 == 0) {
		Integer number = row * 7 + column;
		if (results.contains(number+1)) {
			setBackground(Color.ORANGE);
		} else {
			setBackground(Color.WHITE);
		}
			
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}
}