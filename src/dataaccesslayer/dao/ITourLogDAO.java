package dataaccesslayer.dao;

import models.TourItem;
import models.TourLog;

import java.sql.SQLException;
import java.util.List;

public interface ITourLogDAO {
    TourLog FindById(Integer logId) throws SQLException;
    void DeleteById(Integer itemId) throws SQLException;
    TourLog UpdateLogById(TourLog genLog, Integer id) throws SQLException;
    TourLog AddNewItemLog(TourLog log, TourItem logItem) throws SQLException;
    List<TourLog> GetLogsForItem(TourItem item) throws SQLException;
}
