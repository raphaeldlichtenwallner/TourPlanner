package dataaccesslayer;

import models.TourItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database implements DataAccess {

    private String connectionString;

    public Database() {
        // get info from config file
        connectionString = "...";
        // establish DB connection
    }

    @Override
    public List<TourItem> GetItems() {
        // SQL Statement SELECT * FROM items ...
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
