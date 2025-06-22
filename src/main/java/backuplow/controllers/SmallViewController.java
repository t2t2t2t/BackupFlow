package backuplow.controllers;

import javafx.event.ActionEvent;

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
