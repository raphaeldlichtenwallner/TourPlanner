package businesslayer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import models.TourItem;
import models.TourLog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TourPlannerManagerImpl implements TourPlannerManager {

    @Override
    public List<TourItem> GetItems() throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.GetItems();
    }

    public List<TourLog> GetLogs(TourItem tourItem) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.GetLogsForItem(tourItem);
    }

    @Override
    public List<TourItem> Search(String itemName, boolean caseSensitive) throws SQLException {
        List<TourItem> items = GetItems();

        if (caseSensitive) {
            return items
                    .stream()
                    .filter(x -> x.getName().contains(itemName))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getName().toLowerCase().contains(itemName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public TourItem CreateTourItem(String name, String description, String distance, String start, String end) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.AddNewItem(name, description, distance, start, end);
    }

    @Override
    public TourItem UpdateTourItem(Integer id, String name, String description, String distance, String start, String end) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.UpdateTourById(id, name, description, distance, start, end);
    }

    @Override
    public TourLog CreateTourLog(TourLog log, TourItem item) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.AddNewItemLog(log, item);
    }

    @Override
    public TourLog UpdateLogItem(TourLog genLog, Integer id) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.UpdateLogById(genLog, id);
    }

    @Override
    public void DeleteTour(TourItem item) {
        File file = new File("Images/" + item.getName() + ".jpg");
        if(!file.delete()) {
            System.out.println("delete did not work");
        }
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        try {
            tourItemDAO.DeleteById(item.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void DeleteLog(TourLog log) {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        try {
            tourLogDAO.DeleteById(log.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public TourItem GetItem(Integer id) throws SQLException {
        return DALFactory.CreateTourItemDAO().FindById(id);
    }
}
