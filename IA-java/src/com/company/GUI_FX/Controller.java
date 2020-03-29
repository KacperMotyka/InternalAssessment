package com.company.GUI_FX;

import com.company.LOGIC.Ball;
import com.company.LOGIC.Game;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.EventQueue;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

        static Game currentGame;
        static List<Ball> currentResults;

        private StringProperty currentGameProperty = new SimpleStringProperty();
        private StringProperty currentStrategyProperty = new SimpleStringProperty();


        ///////////////////////////////////////////////////
        //// FXML ATTRIBUTES
        ///////////////////////////////////////////////////
        @FXML
        private AnchorPane scrollPane;
        @FXML
        private ImageView logo;
        @FXML
        private ImageView currentLogo;
        @FXML
        private Label currentGameLabel;
        @FXML
        private Label currentStrategyLabel;

        @FXML
        private SwingNode predictionGrid;
        @FXML
        private SwingNode statsJTable;

        ///////////////////////////////////////////////////
        //// SETTERS
        ///////////////////////////////////////////////////
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

        setDefaultResults();

        }



        ///////////////////////////////////////////////////
        //// ACTION EVENT CHANGE STATE FXML METHODS
        ///////////////////////////////////////////////////

        @FXML
        void refreshDataButton(ActionEvent event) {
                //MainApplication.refreshModel();
                setDefaultResults();
        }

        @FXML
        void chooseGameRadio(ActionEvent event) {

                /// IDENTIFY THE CLICK ON RADIO BUTTON TOGGLE GROUP
                Object source = event.getSource();
                if (!(source instanceof RadioButton))
                        return;
                RadioButton radioButton = (RadioButton) source;
                String id = radioButton.getId();
                int code = 49;
                /// UPDATE CURRENT GAME ACCORDING TO ID OF RADIO BUTTON
                switch (id) {
                        case "lotto" :
                                currentGame = MainApplication.dataManager.lotto;
                        break;
                        case "miniLotto" :{
                                currentGame = MainApplication.dataManager.miniLotto;
                                code = 42;}
                        break;
                        case "euroJack" :
                                currentGame = MainApplication.dataManager.euroJack;
                        break;
                }
                /// UPDATE CURRENT GAME LABEL
                setCurrentGameProperty(currentGame.getName() + " Game : Last drawing " + currentGame.getHistory().get(0).getDate());

                /// SET DEFAULT RESULTS FOR NEW CURRENT GAME
                currentResults = currentGame.getBallStrategy1();
                createAndSetPredictionGrid(predictionGrid, currentResults, code);
                createStatisticsJTable(statsJTable, currentResults, 1);
        }


        @FXML
        void chooseResultsRadio(ActionEvent event) {

                /// IDENTIFY THE CLICK ON RADIO BUTTON TOGGLE GROUP
                Object source = event.getSource();
                if (!(source instanceof RadioButton))
                        return;
                RadioButton radioButton = (RadioButton) source;
                String id = radioButton.getId();
                int code = 49;
                if (currentGame.getName().equals("Mini Lotto")) code = 42;
                /// UPDATE CURRENT RESULTS ACCORDING TO ID OF RADIO BUTTON
                switch (id){
                        case "strat1" : {
                                currentResults = currentGame.getBallStrategy1();
                                createStatisticsJTable(statsJTable, currentResults, 1);
                                createAndSetPredictionGrid(predictionGrid, currentResults, code);
                                setCurrentStrategyProperty("Prediction 1 : Random choice from 20 MOST frequent"); } break;
                        case "strat2" : {
                                currentResults = currentGame.getBallStrategy2();
                                createStatisticsJTable(statsJTable, currentResults, 2);
                                createAndSetPredictionGrid(predictionGrid, currentResults, code);
                                setCurrentStrategyProperty("Prediction 2 : Random choice from 20 LEAST frequent"); } break;
                        case "strat3" : {
                                currentResults = currentGame.getBallStrategy3();
                                createStatisticsJTable(statsJTable, currentResults, 3);
                                createAndSetPredictionGrid(predictionGrid, currentResults, code);
                                setCurrentStrategyProperty("Prediction 3 : Best IOA from 20 MOST frequent"); } break;
                        case "strat4" : {
                                currentResults = currentGame.getBallStrategy4();
                                createStatisticsJTable(statsJTable, currentResults, 4);
                                createAndSetPredictionGrid(predictionGrid, currentResults, code);
                                setCurrentStrategyProperty("Prediction 3 : Best IOA from 20 LEAST frequent"); } break;
                        case "stat5" : {
                                createStatisticsJTable(statsJTable, currentGame.getTwentyMostFrequentlyWinning(), 5);
                                setCurrentStrategyProperty("Statistics for 20 MOST frequent numbers"); } break;
                        case "stat6" : {
                                createStatisticsJTable(statsJTable, currentGame.twentyLeastFrequentlyWinning(), 6);
                                setCurrentStrategyProperty("Statistics for 20 LEAST frequent numbers");  } break;
                        case "stat7" : {
                                createStatisticsJTable(statsJTable, currentGame.getBallStatistics(), 7);
                                setCurrentStrategyProperty("Statistics for ALL numbers"); } break;
                }
        }




        ///////////////////////////////////////////////////
        //// PRIVATE METHODS
        ///////////////////////////////////////////////////

        private void setDefaultResults(){
                /// SET DEFAULT GAME : LOTTO ////
                currentGame = MainApplication.dataManager.lotto;

                /// SET LABEL FOR GAME ///
                setCurrentGameProperty(currentGame.getName() + " Game : Last drawing " + currentGame.getHistory().get(0).getDate());
                currentGameLabel.textProperty().bind(currentGameProperty);

                /// SET DEFAULT RESULTS : STRATEGY 1///
                List<Ball> currentResults = currentGame.getBallStrategy1();

                /// SET DEAFAULT LABELS FOR RESULTS ///
                setCurrentStrategyProperty("Prediction 1 : Random choice from 20 MOST frequent");
                currentStrategyLabel.textProperty().bind(currentStrategyProperty);

                /// CREATE DEFAULT RESULT TABLES : STRATEGY 1 ////
                createAndSetPredictionGrid(predictionGrid, currentResults, 49);
                createStatisticsJTable(statsJTable, currentResults, 1);

                //// SET DEFAULT IMMAGE for DEFAULT GAME :  LOTTO.PNG  /////
                File file2 = new File("img/lotto.png");
                Image image2 = new Image(file2.toURI().toString());
                currentLogo.setImage(image2);

                //// SET LOGO IMMAGE  /////
                File file = new File("img/million.png");
                Image image = new Image(file.toURI().toString());
                logo.setImage(image);
        }
        private void createAndSetPredictionGrid(SwingNode swingNode, List<Ball> results, int code) {
                String[][] currentgrid;
                //System.out.println("### prediction grid");
                List<Integer> listeNombres = new ArrayList<Integer>();
                for (Ball ball : results){
                        listeNombres.add(Integer.parseInt(ball.getNumber()));
                }
                if (code == 42)
                        currentgrid= StrategyTable.grid2;
                else
                        currentgrid = StrategyTable.grid1;
                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                swingNode.setContent(new StrategyTable(listeNombres, currentgrid));
                                JComponent content = swingNode.getContent();
                                content.repaint();
                                content.revalidate();
                                //System.out.println("In ControllerOld in private function in run after creating prediction grid :"+ listeNombres.toString());
                        }
                });
        }

       private void createStatisticsJTable(SwingNode swingNode, List<Ball> results, int code) {
                EventQueue.invokeLater(() -> {
                        swingNode.setContent(new JScrollPane(new StatisticsTable(results)));
                });
       }



}





