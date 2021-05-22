package businesslayer;

import models.TourItem;
import models.TourLog;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface TourPlannerManager {
    List<TourItem> GetItems() throws SQLException;
    List<TourItem> Search(String itemName, boolean caseSensitive) throws SQLException;
    TourItem CreateTourItem(String name, String url, LocalDateTime creationDate) throws SQLException;
    TourLog CreateTourLog(String logText, TourItem item) throws SQLException;
}
