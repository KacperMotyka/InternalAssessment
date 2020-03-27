package com.company.GUI_FX;

import com.company.GUI_swing.StrategyTableComponent;
import com.company.LOGIC.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.RadioButton;
import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
//import java.awt.ActiveEvent;
import java.util.Arrays;

public class Controller implements Initializable {

        static Game currentGame;
        static String currentChoice;
        List<Ball> currentResults;
        private StringProperty currentGameProperty = new SimpleStringProperty();
        private StringProperty currentStrategyProperty = new SimpleStringProperty();
        ///////////////////////////////////////////////////
        //// FXML ATTRIBUTES
        ///////////////////////////////////////////////////
        @FXML
        private AnchorPane scrollPane;
        @FXML
        ImageView logo;
        @FXML
        private ImageView currentLogo;
        @FXML
        private Label currentGameLabel;
        @FXML
        private Label currentStrategyLabel;



        @FXML
        private SwingNode predictionGrid;
        @FXML
        static TableView<Ball> statsTable;
        @FXML
        static TableColumn<Ball,String> col1;
        @FXML
        static TableColumn<Ball,String> col2;
        @FXML
        static TableColumn<Ball,String> col3;
        @FXML
        static TableColumn<Ball,String> col4;
        @FXML
        static TableColumn<Ball,String> col5;
        @FXML
        static TableColumn<Ball,String> col6;
        @FXML
        static TableColumn<Ball,String> col7;

        public void setCurrentGameProperty(String text) {
              currentGameProperty.set(text);
        }

        public void setCurrentStrategyProperty(String text) {
                currentStrategyProperty.set(text);
        }
////////////////////////////////////////////////////////////////
        //// INITIALIZE METHOD - OVERRIDE FROM INTERFACE INITIALIZABLE
        ////////////////////////////////////////////////////////////////

        @Override
        public void initialize(URL location, ResourceBundle resources) {


                currentGame = Model.dataManager.lotto;
                currentGameLabel.textProperty().bind(currentGameProperty);
                currentStrategyLabel.textProperty().bind(currentStrategyProperty);

                //Image image = new Image("@./img/million.png");
                //this.logo.setImage(image);
                System.out.println("In Controller Initialize : current game "+ currentGame.getName());

                setCurrentGame(currentGame);
                setCurrentGameProperty(currentGame.getName() + " Game : Last drawing " + currentGame.getHistory().get(0).getDate());
                //setCurrentStrategyProperty("Prediction according to Strategy 1 : Random choice from most frequent");

                System.out.println("In Controller Initialize : current results ");
                currentResults = currentGame.getBallStrategy3();
                System.out.println("Controler Initialize current results "+ currentResults.toString());

                System.out.println("In Controller Initialize : create and set swing prediction grid");
                createAndSetPredictionGrid(predictionGrid, currentResults);

                System.out.println("In Controller Initialize : create and set statistic table");
                //statsTable = new TableView();
                //statsTable.getItems().addAll(currentResults);
                //statsTable.setItems(FXCollections.observableArrayList(currentResults));
                createAndSetStatisticTable(currentResults, 3);

        }


        ///////////////////////////////////////////////////
        //// ACTION EVENT CHANGE STATE FXML METHODS
        ///////////////////////////////////////////////////


        @FXML
        void chooseGameRadio(ActionEvent event) {
                Object source = event.getSource();
                if (!(source instanceof javafx.scene.control.RadioButton)) {
                        System.out.println("Source is not instance of RadioButton");
                        return;
                }
                System.out.println("Source is instance of RadioButton");
                javafx.scene.control.RadioButton radioButton = (javafx.scene.control.RadioButton) source;
                String id = radioButton.getId();
                System.out.println("Controller in choose game radio before switch source  id =" + id);
                switch (id){
                        case "lotto" : {
                                currentGame = Model.dataManager.lotto;
                                break ;}
                        case "miniLotto" : {
                                currentGame = Model.dataManager.miniLotto;
                                break;}
                        case "euroJack" : {
                                currentGame = Model.dataManager.euroJack;
                                break;
                        }
                }
                // setCurrentGame(currentGame);
                setCurrentGameProperty(currentGame.getName() + " Game : Last drawing " + currentGame.getHistory().get(0).getDate());
                currentResults = currentGame.getBallStrategy3();
                createAndSetPredictionGrid(predictionGrid, currentResults);
                createAndSetStatisticTable(currentResults, 1);
        }


        @FXML
        void chooseResultsRadio(ActionEvent event) {
                Object source = event.getSource();

                if (!(source instanceof javafx.scene.control.RadioButton)) {
                        System.out.println("Source is not instance of RadioButton");
                        return;
                }
                System.out.println("Source is instance of RadioButton");
                javafx.scene.control.RadioButton radioButton = (javafx.scene.control.RadioButton) source;
                String id = radioButton.getId();
                System.out.println("Controller in choose results radio before switch source  id =" + id);
                switch (id){

                        case "strat1" : {
                                currentResults = currentGame.getBallStrategy1();
                                System.out.println("In Controller choose Results case  : strat1");
                                DataManager.printArray(currentResults);
                                createAndSetStatisticTable(currentResults, 1);
                                createAndSetPredictionGrid(predictionGrid, currentResults);
                                break;
                        }
                        case "strat2" : {
                                currentResults = currentGame.getBallStrategy2();
                                System.out.println("In Controller choose Results case : strat2");
                                System.out.println("Current results : ");
                                DataManager.printArray(currentResults);
                                createAndSetStatisticTable(currentResults, 2);
                                createAndSetPredictionGrid(predictionGrid, currentResults);
                                break;
                        }
                        case "strat3" : {
                                currentResults = currentGame.getBallStrategy3();
                                System.out.println("In Controller choose Results case : strat3");
                                System.out.println("Current results : ");
                                DataManager.printArray(currentResults);
                                createAndSetStatisticTable(currentResults, 3);
                                createAndSetPredictionGrid(predictionGrid, currentResults);
                                break;
                        }
                        case "strat4" : {
                                currentResults = currentGame.getBallStrategy4();
                                System.out.println("In Controller choose Results case : strat4");
                                System.out.println("Current results : ");
                                DataManager.printArray(currentResults);
                                createAndSetStatisticTable(currentResults, 4);
                                createAndSetPredictionGrid(predictionGrid, currentResults);
                                break;
                        }
                        case "stat5" : {
                                System.out.println("In Controller choose Results case : stat5");
                                createAndSetStatisticTable(currentGame.getTwentyMostFrequentlyWinning(), 5);
                                break;
                        }
                        case "stat6" : {
                                System.out.println("In Controller choose Results case : stat6");
                                createAndSetStatisticTable(currentGame.twentyLeastFrequentlyWinning(), 6);
                                break;
                        }
                        case "stat7" : {
                                System.out.println("In Controller choose Results case : stat7");
                                createAndSetStatisticTable(currentGame.getBallStatistics(), 7);
                                break;
                        }
                }
        }

        @FXML
        void refreshDataButton(ActionEvent event) {
        }


        ///////////////////////////////////////////////////
        //// PRIVATE METHODS
        ///////////////////////////////////////////////////
        private void setCurrentGame(Game currentGame){
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                                System.out.println("In Controller in private function set current game :"+ currentGame.getName());
                                currentGameLabel = new Label(currentGame.getName() + " Game : Last drawing " + currentGame.getHistory().get(0).getDate());
                                System.out.println("In Controller in private function set current game after new label :"+ currentGame.getName() + " Game : Last drawing " + currentGame.getHistory().get(0).getDate());
                                //currentLogo = new ImageView(currentGame.getUrl());
                        }
                });
        }
        private void createAndSetPredictionGrid(SwingNode swingNode, List<Ball> results) {
                System.out.println("In Controller in private function create prediction grid, before run :");
                List<Integer> listeNombres = new ArrayList<Integer>();
                for (Ball ball : results){
                        listeNombres.add(Integer.parseInt(ball.getNumber()));
                }
                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                //swingNode.setContent(new JButton("Click me!"));
                                //swingNode.setContent(new HTMLButton());
                                swingNode.setContent(new StrategyTableComponent(listeNombres));
                                JComponent content = swingNode.getContent();
                                content.revalidate();
                                content.repaint();
                                System.out.println("In Controller in private function in run after creating prediction grid :"+ listeNombres.toString());
                        }
                });
        }

        private void createAndSetStatisticTable(List<Ball> results, int code){
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                                System.out.println("\nIn Controller in create stats table started run:");
                                statsTable = new TableView();
                                if (results == null)
                                        System.out.println("null");
                                else
                                        System.out.println("size :" + results.size() + " Results from parameter : ");
                                for (Ball ball : results) {
                                        System.out.println(ball.toString());
                                }

                                //statsTable.getItems().clear();
                                statsTable.getItems().addAll(results);
                                //statsTable.setItems(FXCollections.observableArrayList(this.results));

                                col1 = new TableColumn("Ball number");
                                col1.setMinWidth(100);
                                col1.setCellValueFactory(new PropertyValueFactory<Ball, String>("number"));

                                col2 = new TableColumn("Winning times");
                                col2.setMinWidth(100);
                                col2.setCellValueFactory(new PropertyValueFactory<Ball, String>("totalNumberOfWinning"));

                                col3 = new TableColumn("Winning percent");
                                col3.setMinWidth(100);
                                col3.setCellValueFactory(new PropertyValueFactory<Ball, String>("totalPercentOfWinning"));

                                col4 = new TableColumn("Last 15 winning");
                                col4.setMinWidth(100);
                                col4.setCellValueFactory(new PropertyValueFactory<Ball, String>("last15drawsWinning"));

                                col5 = new TableColumn("Last 10 winning");
                                col5.setMinWidth(100);
                                col5.setCellValueFactory(new PropertyValueFactory<Ball, String>("last10drawsWinning"));

                                col6 = new TableColumn("Last 5 winning");
                                col6.setMinWidth(100);
                                col6.setCellValueFactory(new PropertyValueFactory<Ball, String>("last5drawsWinning"));

                                col7 = new TableColumn("Index of acceleration");
                                col7.setMinWidth(120);
                                col7.setCellValueFactory(new PropertyValueFactory<Ball, String>("indexOfAcceleration"));

                                statsTable.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
                                statsTable.setVisible(true);
                                //statsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                                /*
                                if (source < 5){
                                     col4.setVisible(false);
                                     col5.setVisible(false);
                                     col6.setVisible(false);
                                }
                                if (source < 3){
                                     col7.setVisible(false);
                                }
                                */
                                System.out.println("In Controller in create stats table in run after creating table:");
                                System.out.println("In Controller in create stats table in run before switch code to create strategy label. Code = "+ code);
                                switch(code) {
                                        case 1: {setCurrentStrategyProperty("Prediction according to Strategy 1 : Random choice from most frequent"); break;}
                                        case 2: {setCurrentStrategyProperty("Prediction according to Strategy 2 : Random choice from least frequent"); break;}
                                        case 3: {setCurrentStrategyProperty("Prediction according to Strategy 3 : Best IOA from most frequent"); break;}
                                        case 4: {setCurrentStrategyProperty("Prediction according to Strategy 4 : Best IOA from least frequent"); break;}
                                        case 5: {setCurrentStrategyProperty("Prediction according to Strategy 5 : Statistics for 20 most frequent"); break;}
                                        case 6: {setCurrentStrategyProperty("Prediction according to Strategy 6 : Statistics for 20 least frequent"); break;}
                                        case 7: {setCurrentStrategyProperty("Prediction according to Strategy 7 : Statistics for all balls"); break;}
                                }
                        }
                });

        }

         /*
        private void createSwingContent(final Node swingNode, DataManager dataManager) {
                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                //swingNode.setContent(new JButton("Click me!"));
                                //swingNode.setContent(new HTMLButton());
                        }

                });
        }
        */

}





