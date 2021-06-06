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
    private final String SQL_DELETE_BY_ID = "DELETE FROM public.\"TourLogs\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_UPDATE_BY_ID = "UPDATE public.\"TourLogs\" set \"Date\" = ?, \"Report\" = ?, \"Distance\" = ?, \"Time\" = ?, \"Rating\" = ?," +
            " \"Weather\" = ?, \"Speed\" = ?, \"Altitude\" = ?, \"Difficulty\" = ?, \"Calories\" = ? WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_FIND_BY_TOURITEM = "SELECT * FROM public.\"TourLogs\" WHERE \"fk_TourId\"=CAST(? AS INTEGER);";
    private final String SQL_INSERT_NEW_ITEMLOG = "INSERT INTO public.\"TourLogs\" (\"fk_TourId\", \"Date\", \"Report\", \"Distance\", " +
            "\"Time\", \"Rating\", \"Weather\", \"Speed\", \"Altitude\", \"Difficulty\", \"Calories\") VALUES (CAST(? AS INTEGER), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final IDatabase database;

    public TourLogPostgresDAO() throws FileNotFoundException {
        this.database = DALFactory.GetDatabase();
    }

    @Override
    public void FindById(Integer logId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logId);

        List<TourLog> tourItems = database.TourReader(SQL_FIND_BY_ID, parameters, TourLog.class);
        tourItems.stream().findFirst().get();
    }

    @Override
    public void DeleteById(Integer itemId) throws SQLException {
        database.delete(SQL_DELETE_BY_ID, itemId);
    }

    @Override
    public void UpdateLogById(TourLog genLog, Integer id) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(genLog.getDate());
        parameters.add(genLog.getReport());
        parameters.add(genLog.getDistance());
        parameters.add(genLog.getTime());
        parameters.add(genLog.getRating());
        parameters.add(genLog.getWeather());
        parameters.add(genLog.getSpeed());
        parameters.add(genLog.getAltitude());
        parameters.add(genLog.getDifficulty());
        parameters.add(genLog.getCalories());
        parameters.add(id);

        database.UpdateLog(SQL_UPDATE_BY_ID, parameters);
        FindById(id);
    }

    @Override
    public void AddNewItemLog(TourLog log, TourItem tourItem) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(tourItem.getId());
        parameters.add(log.getDate());
        parameters.add(log.getReport());
        parameters.add(log.getDistance());
        parameters.add(log.getTime());
        parameters.add(log.getRating());
        parameters.add(log.getWeather());
        parameters.add(log.getSpeed());
        parameters.add(log.getAltitude());
        parameters.add(log.getDifficulty());
        parameters.add(log.getCalories());

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEMLOG, parameters);
        FindById(resultId);
    }

    @Override
    public List<TourLog> GetLogsForItem(TourItem item) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(item.getId());

        return database.TourReader(SQL_FIND_BY_TOURITEM, parameters, TourLog.class);
    }
}
