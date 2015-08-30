package ua.com.juja.core.SQLCmd;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DatabaseManagerTest {
    private DatabaseManager manager;
    private String database = "postgres";
    private String userName = "postgres";
    private String password = "12345";

    @Before
    public void setup() {
        manager = new DatabaseManager();
        manager.connect(database, userName, password);
    }

    @Test
    public void testGetAllTablesName() {
        String tablesName[] = manager.getTableNames();

        assertEquals("[user]", Arrays.toString(tablesName));
    }

    @Test
    public void testGetTableData() {
        String tableName = "user";
        //given
        manager.clear(tableName);

        //when
        DataSet input = new DataSet();
        input.put("user_id", 13);
        input.put("name", "Stiven");
        input.put("password", "12345");
        manager.create(input);

        //then
        DataSet[] dataSets = manager.getTableData(tableName);
        assertEquals(1, dataSets.length);

        DataSet dataSet = dataSets[0];
        assertEquals("[user_id, name, password]", Arrays.toString(dataSet.getNames()));
        assertEquals("[13, Stiven, 12345]", Arrays.toString(dataSet.getValues()));
    }

    @Test
    public void testUpdateTableData() {
        String tableName = "user";
        //given
        manager.clear(tableName);

        DataSet input = new DataSet();
        input.put("user_id", 13);
        input.put("name", "Stiven");
        input.put("password", "12345");
        manager.create(input);

        //when
        DataSet newValue = new DataSet();
        input.put("password", "123456");
        manager.update(13, newValue);

        //then
        DataSet[] dataSets = manager.getTableData(tableName);
        assertEquals(1, dataSets.length);

        DataSet dataSet = dataSets[0];
        assertEquals("[user_id, name, password]", Arrays.toString(dataSet.getNames()));
        assertEquals("[13, Stiven, 123456]", Arrays.toString(dataSet.getValues()));
    }
}
