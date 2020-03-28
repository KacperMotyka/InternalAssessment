package com.company.GUI_FX;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableCellRenderer extends DefaultTableCellRenderer {

	// ATTRIBUTES
	private static final long serialVersionUID = -3936812983211466205L;
	private List<Integer> results;
	
	public TableCellRenderer(List<Integer> results) {
		super();
		this.results = results;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

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

