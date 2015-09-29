package ua.com.juja.core.SQLCmd.model;

public interface DatabaseManager {
    public DataSet[] getTableData(String tableName);

    public String[] getTableNames();

    public void connect(String database, String userName, String password);

    public void clear(String tableName);

    public void create(String tableNmae, DataSet input);

    public void update(String tableName, int id, DataSet newValue);

    public String[] getTableColumns(String tableName);

    boolean isConnected();
}
