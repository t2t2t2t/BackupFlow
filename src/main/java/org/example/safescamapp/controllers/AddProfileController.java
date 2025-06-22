package org.example.safescamapp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.safescamapp.utils.Setting;
import org.example.safescamapp.utils.SettingKey;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AddProfileController implements Initializable {

    public Label PathName;
    public TextField textFiledPath;
    public Label saveLabel;
    public CheckBox checkReplace;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    @FXML
    private TextField nameProfileTextField;

    public void onSaveProfile(ActionEvent actionEvent) {
        Setting.outputSetting(nameProfileTextField.getText(), SettingKey.PROFILE_NAME,nameProfileTextField.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close(); // Закрываем окно
    }
}