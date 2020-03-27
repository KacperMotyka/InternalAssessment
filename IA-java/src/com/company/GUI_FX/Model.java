package com.company.GUI_FX;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;


import com.company.LOGIC.DataManager;

public class Model extends javafx.application.Application {

    //static final DataManager dataManager = new DataManager();
    static DataManager dataManager;

    @Override
    public void init() throws Exception {
        super.init();
        //System.out.println("Inside init() method! Perform necessary initializations here.");
        dataManager = new DataManager();
        //Game currentGame = dataManager.lotto;
    }

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Million in JavaFX");
        Parent view = FXMLLoader.load(getClass().getResource("View.fxml"));
        window.setScene(new Scene(view, 1200, 1000));

        // elements
        //SwingNode currentTable = new SwingNode();
        //createSwingContent(currentTable, new DataManagerPseudo());
        //DataManager dataManager = new DataManager();
        //System.out.println("In Model");
        //StatisticsFXTable statsTable = new StatisticsFXTable(dataManager.lotto.getBallStrategy1(), 7);

        //StackPane container = new StackPane();

        //container.getChildren().add(view);
        //pane.getChildren().add(statsTable);

        //pane.getChildren().addAll(root, statsTable);

        // System.out.println(pane.getChildren().get(0).toString());
        // pane.getChildren().add(swingNode);
        // Node resultpane = (Node) pane.lookup("#scrollPane");

        //window.setScene(new Scene(container, 1200, 900));

        window.setResizable(true);
        window.show();

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
