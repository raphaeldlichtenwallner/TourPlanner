package businesslayer;

import javafx.collections.ObservableList;
import models.TourItem;
import models.TourLog;

public interface IPDFCreator {
    void CreatePdfForSingleTour (TourItem tour, ObservableList<TourLog> tourLogs);
}
