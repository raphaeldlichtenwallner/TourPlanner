package views.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.TourItem;
import models.TourLog;
import views.LogViewModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogWindowController implements Initializable {
    public LogViewModel logViewModel = new LogViewModel();
    public Button confirmButton;
    public TextField dateField;
    public TextField reportField;
    public TextField distanceField;
    public TextField timeField;
    public TextField ratingField;
    public TextField weatherField;
    public TextField avSpeedField;
    public TextField altitudeField;
    public TextField difficultyField;
    public TextField caloriesField;

    public void addLog() throws SQLException {
        logViewModel.addLog();
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public void setLog(TourLog value) {
        logViewModel.setLog(value);
    }

    public void setTour(TourItem value) {
        logViewModel.setTour(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(dateField.textProperty(), logViewModel.getDate());
        Bindings.bindBidirectional(reportField.textProperty(), logViewModel.getReport());
        Bindings.bindBidirectional(distanceField.textProperty(), logViewModel.getDistance());
        Bindings.bindBidirectional(timeField.textProperty(), logViewModel.getTime());
        Bindings.bindBidirectional(ratingField.textProperty(), logViewModel.getRating());
        Bindings.bindBidirectional(weatherField.textProperty(), logViewModel.getWeather());
        Bindings.bindBidirectional(avSpeedField.textProperty(), logViewModel.getSpeed());
        Bindings.bindBidirectional(altitudeField.textProperty(), logViewModel.getAltitude());
        Bindings.bindBidirectional(difficultyField.textProperty(), logViewModel.getDifficulty());
        Bindings.bindBidirectional(caloriesField.textProperty(), logViewModel.getCalories());
        confirmButton.disableProperty().bind(dateField.textProperty().isEmpty().and(reportField.textProperty().isEmpty().and(
                distanceField.textProperty().isEmpty()).and(timeField.textProperty().isEmpty()).and(
                        ratingField.textProperty().isEmpty()).and(weatherField.textProperty().isEmpty()).and(
                                avSpeedField.textProperty().isEmpty()).and(altitudeField.textProperty().isEmpty().and(
                                        difficultyField.textProperty().isEmpty().and(caloriesField.textProperty().isEmpty())))));
    }
}
