package Database;

import java.sql.*;

public class JdbcConnector {
    private Connection conn;

    public Connection getConnection() throws JDBCConnectionException {
        ConfigurationManager cfg = ConfigurationManager.getInstance();
        try {
            Class.forName(cfg.getDriverName());
            conn = DriverManager.getConnection(cfg.getURL(), cfg.getLogin(), cfg.getPassword());
        } catch (ClassNotFoundException e) {
            throw new JDBCConnectionException("Can't load database driver.", e);
        } catch (SQLException e) {
            throw new JDBCConnectionException("Can't connect to database.", e);
        }
        if (conn == null) {
            throw new JDBCConnectionException("Driver type is not correct in URL " + cfg.getProperty(ConfigurationManager.DB_URL) + ".");
        }
        return conn;
    }
    public void close() throws JDBCConnectionException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new JDBCConnectionException("Can't close connection", e);
            }
        }
    }
}

