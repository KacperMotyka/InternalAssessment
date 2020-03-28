package com.company.GUI_FX;

import javax.swing.*;
import java.util.List;

public class StrategyTable extends JTable {

	public static String[][] grid1 = new String[][]{
		{"1","2","3","4","5","6","7"},
		{"8","9","10","11","12","13","14"},
		{"15","16","17","18","19","20","21"},
		{"22","23","24","25","26","27","28"},
		{"29","30","31","32","33","34","35"},
		{"36","37","38","39","40","41","42"},
		{"43","44","45","46","47","48","49"}
	};
	public static String[][] grid2 = new String[][]{
			{"1","2","3","4","5","6","7"},
			{"8","9","10","11","12","13","14"},
			{"15","16","17","18","19","20","21"},
			{"22","23","24","25","26","27","28"},
			{"29","30","31","32","33","34","35"},
			{"36","37","38","39","40","41","42"}
	};

	public static String[] columnNames = new String[]{" ", " ", " "," ", " ", " "," "};
	public List<Integer> results;
	public TableCellRenderer render ;
	
	public StrategyTable(List<Integer> results, String[][] grid) {
		super(grid, columnNames);
		this.setBounds(30,40,200,300);
		this.setRowHeight(40);
		this.getColumnModel().getColumn(0).setWidth(40);
		this.results = results;
		// define colors
		this.render = new TableCellRenderer(this.results);
		this.render.setHorizontalAlignment(JLabel.CENTER);
		this.render.setVerticalAlignment(JLabel.CENTER);
		// add colors to table according to results
		this.setDefaultRenderer(Object.class, render);
	}
}
