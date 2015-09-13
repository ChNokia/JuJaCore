package model;

import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.model.JDBCDatabaseManager;

public class JDBCDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    public DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }
}
