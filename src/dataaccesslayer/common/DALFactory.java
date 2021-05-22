package dataaccesslayer.common;

import businesslayer.ConfigurationManager;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import dataaccesslayer.postgresSqlServer.Database;
import dataaccesslayer.postgresSqlServer.TourItemPostgresDAO;
import dataaccesslayer.postgresSqlServer.TourLogPostgresDAO;

import java.io.FileNotFoundException;

public class DALFactory {

    private static IDatabase database;

    public static IDatabase GetDatabase() throws FileNotFoundException {
        if (database == null) {
            database = CreateDatabase();
        }
        return database;
    }

    private static IDatabase CreateDatabase() throws FileNotFoundException {
        String connectionString = ConfigurationManager.GetConfigProperty("PostgresSQLConnectionString");
        return CreateDatabase(connectionString);
    }

    private static IDatabase CreateDatabase(String connectionString) {
        try {
            Class<Database> cls = (Class<Database>) Class.forName(Database.class.getName());
            return cls.getConstructor(String.class).newInstance(connectionString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ITourItemDAO CreateTourItemDAO() {
        try {
            Class<TourItemPostgresDAO> cls = (Class<TourItemPostgresDAO>) Class.forName(TourLogPostgresDAO.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ITourLogDAO CreateTourLogDAO() {
        try {
            Class<TourLogPostgresDAO> cls = (Class<TourLogPostgresDAO>) Class.forName(TourLogPostgresDAO.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
