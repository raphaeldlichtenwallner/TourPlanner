package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TourItem {

    public Integer Id;
    public String Name;
    public String Description;
    public String Distance;
    public String Start;
    public String End;

}
