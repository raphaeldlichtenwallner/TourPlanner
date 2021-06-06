package views;

import businesslayer.TourPlannerManager;
import businesslayer.TourPlannerManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import models.TourItem;
import models.TourLog;
import java.sql.SQLException;

public class LogViewModel {

    private final TourPlannerManager manager = TourPlannerManagerFactory.getManager();
    private Integer id = null;
    @Setter private TourItem tour = null;
    @Getter private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
    @Getter private final StringProperty date = new SimpleStringProperty("");
    @Getter private final StringProperty report = new SimpleStringProperty("");
    @Getter private final StringProperty distance = new SimpleStringProperty("");
    @Getter private final StringProperty time = new SimpleStringProperty("");
    @Getter private final StringProperty rating = new SimpleStringProperty("");
    @Getter private final StringProperty weather = new SimpleStringProperty("");
    @Getter private final StringProperty speed = new SimpleStringProperty("");
    @Getter private final StringProperty altitude = new SimpleStringProperty("");
    @Getter private final StringProperty difficulty = new SimpleStringProperty("");
    @Getter private final StringProperty calories = new SimpleStringProperty("");

    public void addLog() throws SQLException {
        TourLog genLog = new TourLog(date.getValue(), report.getValue(), distance.getValue(), time.getValue(), rating.getValue(), weather.getValue(), speed.getValue(), altitude.getValue(), difficulty.getValue(), calories.getValue());
        if(id == null){
            manager.CreateTourLog(genLog, tour);
        } else {
            manager.UpdateLogItem(genLog, id);
        }
    }

    public void setLog(TourLog value) {
        id = value.getId();
        date.set(value.getDate());
        report.set(value.getReport());
        distance.set(value.getDistance());
        time.set(value.getTime());
        rating.set(value.getRating());
        weather.set(value.getWeather());
        speed.set(value.getSpeed());
        altitude.set(value.getAltitude());
        difficulty.set(value.getDifficulty());
        calories.set(value.getCalories());
    }
}
