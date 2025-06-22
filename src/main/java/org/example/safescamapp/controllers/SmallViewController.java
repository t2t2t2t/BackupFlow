package org.example.safescamapp.controllers;

import javafx.event.ActionEvent;
import org.example.safescamapp.Copy;

import java.io.IOException;

public class SmallViewController {

    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void onBackupButtonClick(ActionEvent actionEvent) {
        mainController.onBackupButtonClick(actionEvent);
    }

    public void onReturnButtonClick(ActionEvent actionEvent) {
        mainController.onReturnButtonClick(actionEvent);
    }
}
