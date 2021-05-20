package businesslayer;

import models.TourItem;

import java.util.List;

public interface JavaAppManager {
    public List<TourItem> GetItems();
    public List<TourItem> Search(String itemName, boolean caseSensitive);
}
