package views.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;
import models.TourItem;
import models.TourLog;
import views.MainViewModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {

    public MainViewModel mainViewModel = new MainViewModel();
    public TableView<TourLog> table;
    public ListView<TourItem> listTourItems;
    public TextField searchField;
    public Label titleName;
    public Label description;
    public Label adjacentTour;
    public ImageView route;
    public Button editLog;
    public Button addLog;
    public Button removeTour;
    public Button editTour;
    public Button removeLog;
    public MenuItem saveTourFile;
    public TableColumn<TourLog,String> date;
    public TableColumn<TourLog,String> report;
    public TableColumn<TourLog,String> distance;
    public TableColumn<TourLog,String> time;
    public TableColumn<TourLog,String> rating;
    public TableColumn<TourLog,String> weather;
    public TableColumn<TourLog,String> speed;
    public TableColumn<TourLog,String> altitude;
    public TableColumn<TourLog,String> difficulty;
    public TableColumn<TourLog,String> calories;



    public MainWindowController() throws SQLException {
    }

    public void searchAction() throws SQLException {
        mainViewModel.searchAction();
    }

    public void clearAction() throws SQLException {
        mainViewModel.clearAction();
    }

    public void genItemAction() throws IOException {
        mainViewModel.genItemAction();
    }

    public void genLogAction() throws IOException {
        mainViewModel.genLogAction();
    }

    public void removeItemAction() throws SQLException {
        mainViewModel.removeItemAction();
    }

    public void editItemAction() throws IOException {
        mainViewModel.editItemAction();
    }

    public void removeLogAction() throws SQLException {
        mainViewModel.removeLogAction(table.getSelectionModel().getSelectedItem());
    }

    public void editLogAction() throws IOException {
        mainViewModel.editLogAction(table.getSelectionModel().getSelectedItem());
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(searchField.textProperty(), mainViewModel.getSearch());
        Bindings.bindBidirectional(titleName.textProperty(), mainViewModel.getTitle());
        Bindings.bindBidirectional(description.textProperty(), mainViewModel.getDescription());
        Bindings.bindBidirectional(adjacentTour.textProperty(), mainViewModel.getAdjacentTour());
        Bindings.bindBidirectional(route.imageProperty(), mainViewModel.getRoute());
        table.setItems(mainViewModel.getTourLogs());
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        report.setCellValueFactory(new PropertyValueFactory<>("Report"));
        distance.setCellValueFactory(new PropertyValueFactory<>("Distance"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        weather.setCellValueFactory(new PropertyValueFactory<>("Weather"));
        speed.setCellValueFactory(new PropertyValueFactory<>("Speed"));
        altitude.setCellValueFactory(new PropertyValueFactory<>("Altitude"));
        difficulty.setCellValueFactory(new PropertyValueFactory<>("Difficulty"));
        calories.setCellValueFactory(new PropertyValueFactory<>("Calories"));
        listTourItems.setItems(mainViewModel.getTourItems());
        listTourItems.getSelectionModel().selectedItemProperty().addListener(mainViewModel.getChangeListener());
        editTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        removeTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        saveTourFile.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        addLog.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        editLog.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        removeLog.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        FormatCells();
    }

    private void FormatCells() {
        // format cells to show name
        listTourItems.setCellFactory((param -> new ListCell<TourItem>() {
            @Override
            protected void updateItem(TourItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || (item == null) || (item.getName() == null)) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        }));
    }

    public void saveTour() {
        mainViewModel.saveTour();
    }
}