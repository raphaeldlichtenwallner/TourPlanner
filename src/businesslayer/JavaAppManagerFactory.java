package businesslayer;

public class JavaAppManagerFactory {
    private static JavaAppManager manager;

    public static JavaAppManager getManager() {
        if (manager == null) {
            manager = new JavaAppManagerImpl();
        }
        return manager;
    }
}
