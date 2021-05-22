package dataaccesslayer.postgresSqlServer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.common.IDatabase;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import models.TourItem;
import models.TourLog;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourLogPostgresDAO implements ITourLogDAO {

    private final String SQL_FIND_BY_ID = "SELECT * FROM public.\"TourLogs\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_FIND_BY_TOURITEM = "SELECT * FROM public.\"TourLogs\" WHERE \"TourItemId\"=CAST(? AS INTEGER);";
    private final String SQL_INSERT_NEW_ITEMLOG = "INSERT INTO public.\"TourLogs\" (\"LogText\", \"TourItemId\") VALUES (?, CAST(? AS INTEGER));";

    private IDatabase database;
    private ITourItemDAO tourItemDAO;

    public TourLogPostgresDAO() throws FileNotFoundException {
        this.database = DALFactory.GetDatabase();
        this.tourItemDAO = DALFactory.CreateTourItemDAO();
    }

    @Override
    public TourLog FindById(Integer logId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logId);

        List<TourLog> tourItems = database.TourReader(SQL_FIND_BY_ID, parameters, TourLog.class);
        return tourItems.stream().findFirst().get();
    }

    @Override
    public TourLog AddNewItemLog(String logText, TourItem logItem) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logText);
        parameters.add(logItem.getId());

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEMLOG, parameters);
        return FindById(resultId);
    }

    @Override
    public List<TourLog> GetLogsForItem(TourItem item) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(item.getId());

        return database.TourReader(SQL_FIND_BY_TOURITEM, parameters, TourLog.class);
    }
}
