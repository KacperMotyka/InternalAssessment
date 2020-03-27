package com.company.GUI_FX;

import com.company.LOGIC.DataDownloader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


import com.company.LOGIC.DataManager;

public class Model extends javafx.application.Application {

    static DataManager dataManager;

    @Override
    public void init() throws Exception {
        super.init();
        dataManager = new DataManager();

    }

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Million in JavaFX");
        Parent view = FXMLLoader.load(getClass().getResource("View.fxml"));
        window.setScene(new Scene(view, 1200, 1000));
        window.setResizable(true);
        window.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // save results
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void refreshModel(){
        DataDownloader.refreshData();
        dataManager = new DataManager();
    }
}
