package dataaccesslayer.dao;

import models.TourItem;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ITourItemDAO {
    TourItem FindById(Integer itemId) throws SQLException;
    void DeleteById(Integer itemId) throws SQLException;
    TourItem UpdateTourById(Integer itemId, String name, String description, String distance, String start, String end) throws SQLException;
    TourItem AddNewItem(String name, String description, String distance, String start, String end) throws SQLException;
    List<TourItem> GetItems() throws SQLException;
}
