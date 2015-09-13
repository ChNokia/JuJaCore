package model;

import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.model.InMemoryDatabaseManager;

public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    public DatabaseManager getDatabaseManager() {
        return new InMemoryDatabaseManager();
    }
}
