package dataaccesslayer.dao;

import models.TourItem;
import models.TourLog;

import java.sql.SQLException;
import java.util.List;

public interface ITourLogDAO {
    TourLog FindById(Integer logId) throws SQLException;
    TourLog AddNewItemLog(String logText, TourItem logItem) throws SQLException;
    List<TourLog> GetLogsForItem(TourItem item) throws SQLException;
}
