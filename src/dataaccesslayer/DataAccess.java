package dataaccesslayer;

import models.TourItem;

import java.util.List;

public interface DataAccess {
    public List<TourItem> GetItems();
}
