package dataaccesslayer;

import models.TourItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccess implements DataAccess {

    private String filePath;

    public FileAccess() {
        // get info from config file
        filePath = "...";
    }

    @Override
    public List<TourItem> GetItems() {
        // read media items from file system
        TourItem[] tourItems = {
                new TourItem("Item1"),
                new TourItem("Item2"),
                new TourItem("Another"),
                new TourItem("SWE1"),
                new TourItem("FHTW")
        };
        return new ArrayList<TourItem>(Arrays.asList(tourItems));
    }
}
