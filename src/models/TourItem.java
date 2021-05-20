package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TourItem {
    @Getter @Setter public String Name;
    @Getter @Setter public String Url;
    @Getter @Setter public LocalDateTime CreationTime;
    public TourItem(String name){
    Name = name;
    }
}
