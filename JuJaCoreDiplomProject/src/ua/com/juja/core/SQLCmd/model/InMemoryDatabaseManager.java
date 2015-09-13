package ua.com.juja.core.SQLCmd.model;

import java.util.Arrays;

public class InMemoryDatabaseManager implements DatabaseManager {
    private static final String TABLE_NAME = "user"; // TODO implement multitables

    private DataSet[] dataSets = new DataSet[1000];
    private int currentIndex = 0;

    private void validateTable(String tableName) {
        if ( !tableName.equals(TABLE_NAME) ) {
            throw new UnsupportedOperationException("Only for 'user' table, but you try to work with: " + tableName);
        }
    }

    @Override
    public DataSet[] getTableData(String tableName) {
        validateTable(tableName);

        return Arrays.copyOf(dataSets, currentIndex);
    }

    @Override
    public String[] getTableNames() {
        return new String[]{TABLE_NAME};
    }

    @Override
    public void connect(String database, String userName, String password) {

    }

    @Override
    public void clear(String tableName) {
        validateTable(tableName);

        dataSets = new DataSet[1000];
        currentIndex = 0;
    }

    @Override
    public void create(String tableName, DataSet input) {
        validateTable(tableName);

        dataSets[currentIndex] = input;
        currentIndex += 1;
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        validateTable(tableName);

        for ( int i = 0; i < currentIndex; i++ ) {
            if ( dataSets[i].get("user_id") == id ) {
                dataSets[i].updateFrom(newValue);
            }
        }
    }

    @Override
    public String[] getTableColumns(String tableName) {
        validateTable(tableName);

        return new String[]{"name", "password", "user_id"};
    }
}
