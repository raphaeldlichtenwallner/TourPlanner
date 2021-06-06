package businesslayer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TourPlannerManagerImpl implements TourPlannerManager {
    Logger logger = LogManager.getLogger(TourPlannerManager.class);

    @Override
    public String GetMapQuest(String name, String from, String to) throws IOException, JSONException, InterruptedException {
        MapQuest mapquest = new MapQuest();
        return mapquest.startMapQuest(name, from, to);
    }

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
    public void saveTourToPdf(TourItem tour, ObservableList<TourLog> tourLogs) {
        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.CreatePdfForSingleTour(tour, tourLogs);
        logger.info("Tour info successfully saved to PDF file.");
    }

    @Override
    public void DeleteTourImage(String imagePath) {
        Path path = Paths.get(imagePath);
        try {
            Files.delete(path);
            logger.info("Tour image was successfully removed.");
        } catch (IOException e) {
            logger.error("Tour image couldn't be deleted.");
        }
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
    public void CreateTourItem(String name, String description, String distance, String start, String end) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        tourItemDAO.AddNewItem(name, description, distance, start, end);
        logger.info("Tour was successfully created.");
    }

    @Override
    public void UpdateTourItem(Integer id, String name, String description, String distance, String start, String end) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        tourItemDAO.UpdateTourById(id, name, description, distance, start, end);
        logger.info("Tour was successfully changed.");
    }

    @Override
    public void CreateTourLog(TourLog log, TourItem item) throws SQLException {
        InputValidator inputValidation = new InputValidator();
        if(!inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(log.getCalories()) || !inputValidation.containsOnlyNumbersOrIsEmpty(log.getDifficulty())
                || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(log.getAltitude()) || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(log.getSpeed())
                || !inputValidation.containsOnlyNumbersOrIsEmpty(log.getRating()) || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(log.getDistance())
                || !inputValidation.containsOnlyLettersOrIsEmpty(log.getWeather()) || !inputValidation.checksIfTimeFormatIsCorrect(log.getTime())
                || !inputValidation.checksIfDateFormatIsCorrect(log.getDate())){
            logger.info("Log couldn't be created because the inputs contained wrong characters.");
            return;
        }
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        tourLogDAO.AddNewItemLog(log, item);
        logger.info("Log was successfully created.");
    }

    @Override
    public boolean validateAddressInput(String start, String finish) {
        InputValidator inputValidation = new InputValidator();
        return inputValidation.containsLettersThatAreOptionallyFollowedByNumbers(start, finish);
    }

    @Override
    public void UpdateLogItem(TourLog genLog, Integer id) throws SQLException {
        InputValidator inputValidation = new InputValidator();
        if(!inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(genLog.getCalories()) || !inputValidation.containsOnlyNumbersOrIsEmpty(genLog.getDifficulty())
                || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(genLog.getAltitude()) || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(genLog.getSpeed())
                || !inputValidation.containsOnlyNumbersOrIsEmpty(genLog.getRating()) || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(genLog.getDistance())
                || !inputValidation.containsOnlyLettersOrIsEmpty(genLog.getWeather()) || !inputValidation.checksIfTimeFormatIsCorrect(genLog.getTime())
                || !inputValidation.checksIfDateFormatIsCorrect(genLog.getDate())){
            logger.info("Log couldn't be changed because the inputs contained wrong characters.");
            return;
        }
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        tourLogDAO.UpdateLogById(genLog, id);
        logger.info("Log was successfully changed.");
    }

    @Override
    public void DeleteTour(TourItem item) {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        try {
            tourItemDAO.DeleteById(item.getId());
            logger.info("Tour was deleted.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Some error occurred trying to delete a Tour.");
        }
        DeleteTourImage("Images/" + item.getName() + ".jpg");
    }

    @Override
    public void DeleteLog(TourLog log) {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        try {
            tourLogDAO.DeleteById(log.getId());
            logger.info("Log was deleted.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Some error occurred trying to delete a Log.");
        }
    }

    @Override
    public TourItem GetItem(Integer id) throws SQLException {
        return DALFactory.CreateTourItemDAO().FindById(id);
    }
}
