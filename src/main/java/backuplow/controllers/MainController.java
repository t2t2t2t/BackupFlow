package backuplow.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import backuplow.Copy;
import backuplow.fillter.FileFilterUtil;
import backuplow.utils.Setting;
import backuplow.utils.SettingKey;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MainController {
    @FXML
    private TextField sourcePathField;


    @FXML
    private TextField destinationPathField;

    private Timeline timeline;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label TimerLabel;

    @FXML
    private ComboBox<Integer> timerComboBox;

    @FXML
    private ComboBox<String> fileFilterComboBox;

    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent fxmlAdd;
    private AddProfileController addProfileController;
    private Stage addStage;

    @FXML
    private ComboBox<Integer> fileFilterLastModComboBox;

    @FXML
    private ChoiceBox<String> ChoiceBoxProfile;

    public void initialize() {
        viewSetting();
        timerComboBox.getItems().setAll(1, 5, 10, 15, 30);
        fileFilterLastModComboBox.getItems().setAll(1, 5, 10, 15, 30);

        ChoiceBoxProfile.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Выбран профиль: " + newValue);
                loadProfile(newValue);
            }
        });

    }

    private void loadProfile(String profile) {
        sourcePathField.setText(Setting.inputSetting(profile, SettingKey.SOURCE));
        destinationPathField.setText(Setting.inputSetting(profile, SettingKey.DESTINATION));
        fileFilterComboBox.getItems().setAll(FileFilterUtil.fileExtraction(Setting.inputSetting(profile, SettingKey.SOURCE)));

        fileFilterComboBox.setValue(Setting.inputSetting(profile, SettingKey.FILEFILTEREXT));
        checkFilterExt.setSelected(getSafeBoolean(profile, SettingKey.ISFILEFILTEREXT));

        fileFilterLastModComboBox.setValue(getSafeInt(profile, SettingKey.FILEFILTERLATMOD,1));
        checkFilterLastMod.setSelected(getSafeBoolean(profile, SettingKey.ISFILEFILTERLATMOD));

        timerComboBox.setValue(getSafeInt(profile, SettingKey.TIMER,1));

    }



    private String getSafeString(String profile, SettingKey key) {
        String value = Setting.inputSetting(profile, key);
        return value != null ? value : "";
    }

    private boolean getSafeBoolean(String profile, SettingKey key) {
        String value = Setting.inputSetting(profile, key);
        return "true".equalsIgnoreCase(value);
    }

    private int getSafeInt(String profile, SettingKey key, int defaultValue) {
        try {
            String value = Setting.inputSetting(profile, key);
            return value != null && !value.isEmpty() ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }




    private void viewSetting() {
        clearAllFields();
        Set<String> profiles = Setting.getAppProfiles();
        ChoiceBoxProfile.getItems().setAll(profiles);
        if (!profiles.isEmpty()) {
            String nextProfile = profiles.iterator().next();
            ChoiceBoxProfile.setValue(nextProfile);
            loadProfile(nextProfile);
        }
    }


    @FXML
    public void chooseSourceDirectory(ActionEvent actionEvent) {
        ChoicePath(sourcePathField);
        fileFilterComboBox.getItems().setAll(FileFilterUtil.fileExtraction(sourcePathField.getText()));
    }

    @FXML
    public void chooseDestinationDirectory(ActionEvent actionEvent) {
        ChoicePath(destinationPathField);
    }

    private void ChoicePath(TextField textField) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");

        if (textField.getText() != null && !textField.getText().isEmpty()) {
            directoryChooser.setInitialDirectory(new File(textField.getText()));
        } else
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));


        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            textField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private CheckBox checkFilterExt;
    @FXML
    private CheckBox checkFilterLastMod;

    @FXML
    public void onBackupButtonClick(ActionEvent actionEvent) {
        System.out.println(checkFilterLastMod.isSelected());
        System.out.println(checkFilterLastMod.getText());
        System.out.println(checkFilterLastMod);
        try {
            if (checkFilterLastMod.isSelected()) {
                List<File> nameFilter = FileFilterUtil.fileFilterLastMod(sourcePathField.getText(), fileFilterLastModComboBox.getValue());
                System.out.println("-------");
                for (File file : nameFilter) {
                    System.out.println(file.getAbsolutePath());
                }
                System.out.println("-------");
                Copy.copyFileLasMod(nameFilter, sourcePathField.getText(), destinationPathField.getText());
            } else if (checkFilterExt.isSelected()) {
                Copy.copyFilesFilterExt(fileFilterComboBox.getValue(), sourcePathField.getText(), destinationPathField.getText());
            } else {
                Copy.copyFolder(sourcePathField.getText(), destinationPathField.getText());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void clearFields(ActionEvent actionEvent) {
        clearAllFields();
    }

    private void clearAllFields() {
        sourcePathField.setText("");
        destinationPathField.setText("");
        checkFilterExt.setSelected(false);
        checkFilterLastMod.setSelected(false);
    }

    @FXML
    public void isReplace(ActionEvent actionEvent) {
    }

    @FXML
    public void onReturnButtonClick(ActionEvent actionEvent) {
    }

    public void onSaveSettingClick(ActionEvent actionEvent) {

        Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.SOURCE, sourcePathField.getText());
        Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.DESTINATION, destinationPathField.getText());


        if(fileFilterComboBox.getValue()!=null){
            Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.FILEFILTEREXT, fileFilterComboBox.getValue());
        }

        Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.ISFILEFILTEREXT, String.valueOf(checkFilterExt.isSelected()));

        Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.FILEFILTERLATMOD, String.valueOf(fileFilterLastModComboBox.getValue()));
        Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.ISFILEFILTERLATMOD, String.valueOf(checkFilterLastMod.isSelected()));

        Setting.outputSetting(ChoiceBoxProfile.getValue(), SettingKey.TIMER, String.valueOf(timerComboBox.getValue()));

        //Setting.saveProperty();

    }

    public void onStartTimerClick(ActionEvent actionEvent) {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop(); // Останавливаем текущий, если работает
        }

        Integer TOTAL_SECONDS = timerComboBox.getValue() * 60;
        System.out.println(TOTAL_SECONDS);
        AtomicInteger secondsLeft = new AtomicInteger(TOTAL_SECONDS);
        progressBar.setProgress(0);
        TimerLabel.setText("Timer: " + formatTime(secondsLeft.get()));

        timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), e -> {
                    int left = secondsLeft.decrementAndGet();
                    double progress = 1.0 - ((double) left / TOTAL_SECONDS);
                    progressBar.setProgress(progress);
                    TimerLabel.setText("Timer: " + formatTime(left));

                    if (left <= 0) {
                        onBackupButtonClick(actionEvent);
                        onStartTimerClick( actionEvent);
                        //сохранение
                    }
                })
        );
        timeline.setCycleCount(TOTAL_SECONDS);
        timeline.play();
    }

    private String formatTime(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    public void onStopTimerClick(ActionEvent actionEvent) {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
            progressBar.setProgress(0);
            TimerLabel.setText("Timer: ");
        }
    }

    public void onaddProfileClick(ActionEvent actionEvent) {
        try {
            // Загружаем FXML и получаем контроллер
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/backuplow/fxml/addProfile.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 100);
            Stage stage = new Stage();
            stage.setTitle("Add profile");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();

            clearAllFields();
            viewSetting();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onDeleteProfile(ActionEvent actionEvent) {
        Setting.deleteProfile(ChoiceBoxProfile.getValue());
        viewSetting();

    }


    public void onSmoleViews(ActionEvent actionEvent) {
        try {
            // Загружаем FXML и получаем контроллер
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/backuplow/fxml/saveTopView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 250, 50);
            Stage stage = new Stage();
            stage.setTitle("Small view");
            stage.setScene(scene);

            stage.initModality(Modality.NONE);
            stage.setAlwaysOnTop(true);

            Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            mainStage.setIconified(true);

            // Получаем контроллер маленького окна
            SmallViewController smallController = loader.getController();

            // Передаём ему данные или методы из текущего контроллера
            smallController.setMainController(this);

            stage.setOnCloseRequest(e -> mainStage.setIconified(false));

            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();


            viewSetting();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
