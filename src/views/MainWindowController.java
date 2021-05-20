package views;

import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.TourItem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    public TextField searchField;
    public ListView<TourItem> listTourItems;

    private ObservableList<TourItem> tourItems;
    private TourItem currentItem;
    private JavaAppManager manager;

    public void searchAction(ActionEvent actionEvent) {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField.textProperty().getValue(), false);
        tourItems.addAll(items);
    }

    public void clearAction(ActionEvent actionEvent) {
        tourItems.clear();
        searchField.textProperty().setValue("");

        List<TourItem> items = manager.GetItems();
        tourItems.addAll(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager = JavaAppManagerFactory.getManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();
    }

    private void SetupListView() {
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