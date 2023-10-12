package Database;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "database";
    public static final String DB_URL = "url";
    public static final String DB_DRIVER = "driver";
    public static final String DB_USER = "user";
    public static final String DB_PASSWORD = "password";

    private ConfigurationManager() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    public String getURL() {
        return getProperty(DB_URL);
    }

    public String getDriverName() {
        return getProperty(DB_DRIVER);
    }

    public String getLogin() {
        return getProperty(DB_USER);
    }

    public String getPassword() {
        return getProperty(DB_PASSWORD);
    }
}
