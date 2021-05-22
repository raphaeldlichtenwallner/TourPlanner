package businesslayer;

public class TourPlannerManagerFactory {
    private static TourPlannerManager manager;

    public static TourPlannerManager getManager() {
        if (manager == null) {
            manager = new TourPlannerManagerImpl();
        }
        return manager;
    }
}
