package views;

import businesslayer.NameGenerator;
import businesslayer.TourPlannerManager;
import businesslayer.TourPlannerManagerFactory;
import dataaccesslayer.common.DALFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import models.TourItem;
import models.TourLog;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    public TextField searchField;
    public ListView<TourItem> listTourItems;

    private ObservableList<TourItem> tourItems;
    private TourItem currentItem;
    private TourPlannerManager manager;

    public void searchAction(ActionEvent actionEvent) throws SQLException {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField.textProperty().getValue(), false);
        tourItems.addAll(items);
    }

    public void clearAction(ActionEvent actionEvent) throws SQLException {
        tourItems.clear();
        searchField.textProperty().setValue("");

        List<TourItem> items = manager.GetItems();
        tourItems.addAll(items);
    }

    public void genItemAction(ActionEvent actionEvent) throws SQLException {
        TourItem genItem = manager.CreateTourItem(NameGenerator.GenerateName(4), NameGenerator.GenerateName(8), LocalDateTime.now());
        tourItems.add(genItem);
    }

    public void genLogAction(ActionEvent actionEvent) throws SQLException {
        TourLog genLog = manager.CreateTourLog(NameGenerator.GenerateName(40), currentItem);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager = TourPlannerManagerFactory.getManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();
    }

    private void SetupListView() throws SQLException {
        tourItems = FXCollections.observableArrayList();
        tourItems.addAll(manager.GetItems());
        listTourItems.setItems(tourItems);
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

    private void SetCurrentItem() {
        listTourItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((newValue != null) && (oldValue) != (newValue)) {
                currentItem = newValue;
            }
        }));
    }
}