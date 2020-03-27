package com.company.GUI_FX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;




import javafx.embed.swing.SwingNode;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;

import com.company.LOGIC.DataManagerPseudo;
import com.company.LOGIC.DataManager;
import com.company.LOGIC.Game;
import com.company.GUI_swing.*;
import java.util.Arrays;

public class Model extends javafx.application.Application {

    //static final DataManager dataManager = new DataManager();
    static DataManager dataManager;

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Inside init() method! Perform necessary initializations here.");
        dataManager = new DataManager();
        //Game currentGame = dataManager.lotto;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Million + Swing in JavaFX");
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        //stage.setScene(new Scene(root, 1000, 800));

        // elements
        //SwingNode currentTable = new SwingNode();
        //createSwingContent(currentTable, new DataManagerPseudo());
        //DataManager dataManager = new DataManager();
        //System.out.println("In Model");
        StatisticsTable statsTable = new StatisticsTable(dataManager.lotto.getBallStrategy1(), 7);

        StackPane pane = new StackPane();

        pane.getChildren().add(root);
        //pane.getChildren().add(statsTable);

        //pane.getChildren().addAll(root, statsTable);

        // System.out.println(pane.getChildren().get(0).toString());
        // pane.getChildren().add(swingNode);
        // Node resultpane = (Node) pane.lookup("#scrollPane");

        stage.setScene(new Scene(pane, 1000, 800));

        stage.setResizable(true);
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // System.out.println("Inside stop() method! Destroy resources. Perform Cleanup.");
        // save results
    }

    public static void main(String[] args) {
        launch(args);
    }
}
