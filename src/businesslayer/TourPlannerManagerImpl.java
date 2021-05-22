package businesslayer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import models.TourItem;
import models.TourLog;

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
    public TourItem CreateTourItem(String name, String url, LocalDateTime creationDate) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.AddNewItem(name, url, creationDate);
    }

    @Override
    public TourLog CreateTourLog(String logText, TourItem item) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.AddNewItemLog(logText, item);
    }
}
