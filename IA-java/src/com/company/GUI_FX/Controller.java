package com.company.GUI_FX;

import com.company.GUI_swing.StrategyTableComponent;
import com.company.LOGIC.DataManagerPseudo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.embed.swing.SwingNode;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
//import java.awt.ActiveEvent;

public class Controller implements Initializable {

        @FXML
        private ToggleGroup Action1;

        @FXML
        private ToggleGroup Action2;

        @FXML
        private Node currentLogo;
        @FXML
        private Node currentGameLabel;

        @FXML
        private Node currentStrategyLabel;

        @FXML
        private Node currentResultsPane;

        @FXML
        private Node scrollPane;

        @FXML
        void chooseGameRadio(ActionEvent event) {
        }

        @FXML
        void chooseResultsRadio(ActionEvent event) {
        }

        @FXML
        void refreshDataButton(ActionEvent event) {
        }

        @FXML
        private SwingNode currentTable;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                //SwingNode currentTable = new SwingNode();
                //Pane pane = new Pane();
                createAndSetSwingCurrentTable(currentTable, new DataManagerPseudo());
                //pane.getChildren().add(currentTable);
        }

        public void createAndSetSwingCurrentTable(final SwingNode swingNode, DataManagerPseudo dataManager) {
                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                swingNode.setContent(new StrategyTableComponent(new Integer[]{1,12,3,34,5,46}));
                        }
                });
        }
        private void createSwingContent(final SwingNode swingNode, DataManagerPseudo dataManager) {
                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                //swingNode.setContent(new JButton("Click me!"));
                                //swingNode.setContent(new HTMLButton());
                                swingNode.setContent(new StrategyTableComponent(new Integer[]{1,12,3,34,5,46}));
                        }

                });
        }
}





