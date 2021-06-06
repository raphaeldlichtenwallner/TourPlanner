package businesslayer;

import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;
import org.json.JSONException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TourPlannerManager {

    List<TourItem> GetItems() throws SQLException;
    List<TourItem> Search(String itemName, boolean caseSensitive) throws SQLException;
    TourItem GetItem(Integer id) throws SQLException;
    void CreateTourItem(String name, String description, String distance, String start, String end) throws SQLException;
    void UpdateTourItem(Integer id, String name, String description, String distance, String start, String end) throws SQLException;
    void saveTourToPdf(TourItem value, ObservableList<TourLog> tourLogs);
    void DeleteTour(TourItem item);
    void DeleteTourImage(String imagePath);

    List<TourLog> GetLogs(TourItem newValue) throws SQLException;
    void CreateTourLog(TourLog log, TourItem item) throws SQLException;

    boolean validateAddressInput(String start, String finish);

    void UpdateLogItem(TourLog genLog, Integer id) throws SQLException;
    void DeleteLog(TourLog log);

    String GetMapQuest(String name, String from, String to) throws JSONException, IOException, InterruptedException;
}
