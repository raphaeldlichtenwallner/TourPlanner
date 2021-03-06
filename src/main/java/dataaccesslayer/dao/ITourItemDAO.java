package dataaccesslayer.dao;

import models.TourItem;
import java.sql.SQLException;
import java.util.List;

public interface ITourItemDAO {
    TourItem FindById(Integer itemId) throws SQLException;
    void DeleteById(Integer itemId) throws SQLException;
    void UpdateTourById(Integer itemId, String name, String description, String distance, String start, String end) throws SQLException;
    void AddNewItem(String name, String description, String distance, String start, String end) throws SQLException;
    List<TourItem> GetItems() throws SQLException;
}
