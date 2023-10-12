package Database;

public class JDBCConnectionException extends Exception {
    public JDBCConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JDBCConnectionException(String message) {
        super(message);
    }
}
