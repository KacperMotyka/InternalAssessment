package com.company.GUI_FX;

import com.company.LOGIC.Ball;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StatisticsTable extends JTable {

    public static String[] columnNames = new String[]{"Ball number",
            "Winning Times",
            "Winning percent",
            "Last 15 winning",
            "Last 10 winning",
            "Last 5 winning",
            "Index of acceleration"};

    public StatisticsTable(List<Ball> results) {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        this.setModel(model);

        for (Ball ball : results) {
            model.addRow(
                    new Object[] {
                            ball.getNumber(),
                            ball.getTotalNumberOfWinning(),
                            ball.getTotalPercentOfWinning(),
                            ball.getLast15drawsWinning(),
                            ball.getLast10drawsWinning(),
                            ball.getLast5drawsWinning(),
                            ball.getIndexOfAcceleration()
                    });
        }
    }
}

