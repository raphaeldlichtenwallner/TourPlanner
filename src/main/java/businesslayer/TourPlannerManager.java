package businesslayer;

import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TourPlannerManager {
    String GetMapQuest(String name, String from, String to) throws FileNotFoundException, JSONException, IOException, InterruptedException;

    List<TourItem> GetItems() throws SQLException;
    List<TourItem> Search(String itemName, boolean caseSensitive) throws SQLException;
    TourItem CreateTourItem(String name, String description, String distance, String start, String end) throws SQLException;
    TourItem UpdateTourItem(Integer id, String name, String description, String distance, String start, String end) throws SQLException;
    TourLog CreateTourLog(TourLog log, TourItem item) throws SQLException;
    TourLog UpdateLogItem(TourLog genLog, Integer id) throws SQLException;
    void DeleteTour(TourItem item);
    void DeleteLog(TourLog log);
    TourItem GetItem(Integer id) throws SQLException;

    List<TourLog> GetLogs(TourItem newValue) throws SQLException;

    void saveTourToPdf(TourItem value, ObservableList<TourLog> tourLogs);

    void DeleteTourImage(String imagePath);
}
