package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TourItem {

    @Getter @Setter public Integer Id;
    @Getter @Setter public String Name;
    @Getter @Setter public String Url;
    @Getter @Setter public LocalDateTime CreationTime;

    public TourItem(Integer id, String name, String url, LocalDateTime creationTime){
        Id = id;
        Name = name;
        Url = url;
        CreationTime = creationTime;
    }
}
