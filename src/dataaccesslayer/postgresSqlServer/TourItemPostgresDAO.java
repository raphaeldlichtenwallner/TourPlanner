package dataaccesslayer.postgresSqlServer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.common.IDatabase;
import dataaccesslayer.dao.ITourItemDAO;
import models.TourItem;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TourItemPostgresDAO implements ITourItemDAO {

    private final String SQL_FIND_BY_ID = "SELECT * FROM public.\"TourItems\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_DELETE_BY_ID = "DELETE FROM public.\"TourItems\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_UPDATE_BY_ID = "UPDATE public.\"TourItems\" set \"Name\" = ?, \"Description\" = ?, \"Distance\" = ?, \"Start\" = ?, \"End\" = ? WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_GET_ALL_ITEMS = "SELECT * FROM public.\"TourItems\";";
    private final String SQL_INSERT_NEW_ITEM = "INSERT INTO public.\"TourItems\" (\"Name\", \"Description\", \"Distance\", \"Start\", \"End\") VALUES (?, ?, ?, ?, ?);";

    private IDatabase database;

    public TourItemPostgresDAO() throws FileNotFoundException {
        database = DALFactory.GetDatabase();
    }

    @Override
    public TourItem FindById(Integer itemId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(itemId);

        List<TourItem> tourItems = database.TourReader(SQL_FIND_BY_ID, parameters, TourItem.class);
        return tourItems.stream().findFirst().get();
    }

    @Override
    public void DeleteById(Integer itemId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(itemId);
        database.delete(SQL_DELETE_BY_ID, itemId);
    }

    @Override
    public TourItem UpdateTourById(Integer itemId, String name, String description, String distance, String start, String end) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(name);
        parameters.add(description);
        parameters.add(distance);
        parameters.add(start);
        parameters.add(end);
        parameters.add(itemId);

        database.UpdateItem(SQL_UPDATE_BY_ID, parameters);
        return FindById(itemId);
    }

    @Override
    public TourItem AddNewItem(String name, String description, String distance, String start, String end) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(name);
        parameters.add(description);
        parameters.add(distance);
        parameters.add(start);
        parameters.add(end);

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEM, parameters);
        return FindById(resultId);
    }

    @Override
    public List<TourItem> GetItems() throws SQLException {
        return database.TourReader(SQL_GET_ALL_ITEMS, TourItem.class);
    }
}
