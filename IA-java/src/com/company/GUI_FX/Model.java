package com.company.GUI_FX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.company.LOGIC.DataManager;
import com.company.LOGIC.Game;

public class Model extends javafx.application.Application {

    DataManager data;
    Game currentGame;

    private Model(DataManager dataManager){
        this.data = dataManager;
        this.currentGame = data.lotto;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Calculator");
        // primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root, 1000, 800));
        //primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        DataManager data = new DataManager();
        Model gui = new Model(data);
    }
}
