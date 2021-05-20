package dataaccesslayer;

import models.TourItem;

import java.util.List;

public class TourItemDAO {

    private DataAccess dataAccess;

    public TourItemDAO() {
        dataAccess = new Database();
        //dataAccess = new FileAccess();
    }

    public List<TourItem> GetItems() {
        return dataAccess.GetItems();
    }
}
