package views;

import businesslayer.TourPlannerManager;
import businesslayer.TourPlannerManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import models.TourItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import java.io.IOException;
import java.sql.SQLException;

public class TourViewModel {

    private final TourPlannerManager manager = TourPlannerManagerFactory.getManager();
    private Integer id = null;
    private String oldName;
    @Getter private final StringProperty name = new SimpleStringProperty("");
    @Getter private final StringProperty start = new SimpleStringProperty("");
    @Getter private final StringProperty end = new SimpleStringProperty("");
    @Getter private final StringProperty description = new SimpleStringProperty("");
    Logger log = LogManager.getLogger(TourViewModel.class);


    public void addAction() throws SQLException, IOException, JSONException, InterruptedException {
        String descriptionWithDistance;
        if(!manager.validateAddressInput(start.getValue(), end.getValue())){
            log.info("Address input contained wrong values and no tour was added or changed.");
            return;
        }
        if(id == null){
            String distance = manager.GetMapQuest(name.getValue(), start.getValue(), end.getValue());
            descriptionWithDistance = ("This Tour has a total length of "+distance+"km\n" +description.getValue());
            manager.CreateTourItem(name.getValue(), descriptionWithDistance, distance, start.getValue(), end.getValue());
        } else {
            if(!name.getValue().equals(oldName)){
                manager.DeleteTourImage("Images/"+oldName+".jpg");
            }
            String distance = manager.GetMapQuest(name.getValue(), start.getValue(), end.getValue());
            descriptionWithDistance = ("This Tour has a total length of "+distance+"km\n" +description.getValue());
            manager.UpdateTourItem(id, name.getValue(), description.getValue(), descriptionWithDistance, start.getValue(), end.getValue());
        }
    }

    public void setTour(TourItem value) {
        id = value.getId();
        name.set(value.getName());
        description.set(value.getDescription());
        start.set(value.getStart());
        end.set(value.getEnd());
        oldName = name.getValue();
    }
}
