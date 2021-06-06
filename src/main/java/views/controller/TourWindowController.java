package views.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.TourItem;
import org.json.JSONException;
import views.TourViewModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TourWindowController implements Initializable {
    public TourViewModel tourViewModel = new TourViewModel();
    public Button confirmButton;
    public TextField nameField;
    public TextField startField;
    public TextField endField;
    public TextArea descriptionField;


    public void addAction() throws SQLException, IOException, JSONException, InterruptedException {
        tourViewModel.addAction();
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(nameField.textProperty(), tourViewModel.getName());
        Bindings.bindBidirectional(startField.textProperty(), tourViewModel.getStart());
        Bindings.bindBidirectional(endField.textProperty(), tourViewModel.getEnd());
        Bindings.bindBidirectional(descriptionField.textProperty(), tourViewModel.getDescription());
        confirmButton.disableProperty().bind(nameField.textProperty().isEmpty().or(startField.textProperty().isEmpty().or(endField.textProperty().isEmpty()))
        );
    }

    public void setTour(TourItem value) {
        tourViewModel.setTour(value);
    }
}
