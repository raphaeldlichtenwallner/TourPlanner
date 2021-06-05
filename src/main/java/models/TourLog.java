package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TourLog {
    public TourLog(String date, String report, String distance, String time, String rating, String weather, String speed, String altitude, String difficulty, String calories) {
        this(null, date, report, distance, time, rating, weather, speed, altitude, difficulty, calories);
    }

    public Integer Id;
    public String Date;
    public String Report;
    public String Distance;
    public String Time;
    public String Rating;
    public String Weather;
    public String Speed;
    public String Altitude;
    public String Difficulty;
    public String Calories;

}
