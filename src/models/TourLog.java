package models;

import lombok.Getter;
import lombok.Setter;

public class TourLog {

    @Getter @Setter public Integer Id;
    @Getter @Setter public String LogText;
    @Getter @Setter public TourItem LogTourItem;

    public TourLog(Integer id, String logText, TourItem logTourItem) {
        Id = id;
        LogText = logText;
        LogTourItem = logTourItem;
    }
}
