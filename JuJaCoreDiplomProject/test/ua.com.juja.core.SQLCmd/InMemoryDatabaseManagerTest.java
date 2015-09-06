import ua.com.juja.core.SQLCmd.DatabaseManager;
import ua.com.juja.core.SQLCmd.InMemoryDatabaseManager;

public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    public DatabaseManager getDatabaseManager() {
        return new InMemoryDatabaseManager();
    }
}
