package com.company.GUI_FX;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.company.LOGIC.Ball;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class StatisticsTable extends TableView {

    List<Ball> results;

    @FXML
    TableColumn<Ball,String> col11;
    @FXML
    TableColumn<Ball,String> col12;
    @FXML
    TableColumn<Ball,String> col13;
    @FXML
    TableColumn<Ball,String> col14;
    @FXML
    TableColumn<Ball,String> col15;
    @FXML
    TableColumn<Ball,String> col16;
    @FXML
    TableColumn<Ball,String> col17;


    public StatisticsTable (List<Ball> results, int colNumber) {
        super();
        if (results == null)
            System.out.println("null");
        else
            System.out.println("size :" + results.size());
            for (Ball ball: results){
                System.out.println(ball.toString());
            }


        this.results = results;
        this.getItems().clear();
        this.getItems().addAll(this.results);
        //this.setItems(FXCollections.observableArrayList(this.results));

        TableColumn col1 = new TableColumn("Ball number");
        col1.setMinWidth(100);
        col1.setCellValueFactory(new PropertyValueFactory<Ball, String>("number"));

        TableColumn col2 = new TableColumn("Winning times");
        col2.setMinWidth(100);
        col2.setCellValueFactory(new PropertyValueFactory<Ball, String>("totalNumberOfWinning"));

        TableColumn col3 = new TableColumn("Winning percent");
        col3.setMinWidth(100);
        col3.setCellValueFactory(new PropertyValueFactory<Ball, String>("totalPercentOfWinning"));

        TableColumn col4 = new TableColumn("Last 15 winning");
        col4.setMinWidth(100);
        col4.setCellValueFactory(new PropertyValueFactory<Ball, String>("last15drawsWinning"));


        TableColumn col5 = new TableColumn("Last 10 winning");
        col5.setMinWidth(100);
        col5.setCellValueFactory(new PropertyValueFactory<Ball, String>("last10drawsWinning"));

        TableColumn col6 = new TableColumn("Last 5 winning");
        col6.setMinWidth(100);
        col6.setCellValueFactory(new PropertyValueFactory<Ball, String>("last5drawsWinning"));

        TableColumn col7 = new TableColumn("Index of acceleration");
        col7.setMinWidth(120);
        col7.setCellValueFactory(new PropertyValueFactory<Ball, String>("indexOfAcceleration"));

        this.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
        //this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

}
