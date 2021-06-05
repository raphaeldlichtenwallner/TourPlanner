package views;

import businesslayer.TourPlannerManager;
import businesslayer.TourPlannerManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import models.TourItem;

import java.sql.SQLException;

public class TourViewModel {
    private final TourPlannerManager manager = TourPlannerManagerFactory.getManager();

    private Integer id = null;
    @Getter private final StringProperty name = new SimpleStringProperty("");
    @Getter private final StringProperty start = new SimpleStringProperty("");
    @Getter private final StringProperty end = new SimpleStringProperty("");
    @Getter private final StringProperty description = new SimpleStringProperty("");

    public void addAction() throws SQLException {

        if(id == null){
            manager.CreateTourItem(name.getValue(), description.getValue(), "distance", start.getValue(), end.getValue());
        } else {
            manager.UpdateTourItem(id, name.getValue(), description.getValue(), "distance", start.getValue(), end.getValue());
        }
    }

    public void setTour(TourItem value) {
        id = value.getId();
        name.set(value.getName());
        description.set(value.getDescription());
        start.set(value.getStart());
        end.set(value.getEnd());
    }
}
