package com.company.GUI_FX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
//import java.awt.ActiveEvent;

public class Controller {

        @FXML
        private ToggleGroup Action1;

        @FXML
        private ToggleGroup Action2;

        private String currentGame;
        @FXML

        void chooseGameRadio(ActionEvent event) {

        if (event.getSource().equals("Lotto")){
            currentGame = "lotto";

            }
        }

        @FXML
        void chooseResultsRadio(ActionEvent event) {

        }

        @FXML
        void refreshDataButton(ActionEvent event) {

        }
}




