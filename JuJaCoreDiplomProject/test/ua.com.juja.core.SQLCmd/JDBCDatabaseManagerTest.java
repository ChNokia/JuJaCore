import ua.com.juja.core.SQLCmd.DatabaseManager;
import ua.com.juja.core.SQLCmd.JDBCDatabaseManager;

public class JDBCDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    public DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }
}
