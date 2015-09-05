package ua.com.juja.core.SQLCmd;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DatabaseManagerTest {
    public static final String TABLE_NAME = "user";

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
        //given
        manager.clear(TABLE_NAME);

        //when
        DataSet input = new DataSet();
        input.put("user_id", 13);
        input.put("name", "Stiven");
        input.put("password", "12345");
        manager.create(TABLE_NAME, input);

        //then
        DataSet[] dataSets = manager.getTableData(TABLE_NAME);
        assertEquals(1, dataSets.length);

        DataSet dataSet = dataSets[0];
        assertEquals("[user_id, name, password]", Arrays.toString(dataSet.getNames()));
        assertEquals("[13, Stiven, 12345]", Arrays.toString(dataSet.getValues()));
    }

    @Test
    public void testUpdateTableData() {
        //given
        manager.clear(TABLE_NAME);

        DataSet input = new DataSet();
        input.put("user_id", 13);
        input.put("name", "Stiven");
        input.put("password", "12345");
        manager.create(TABLE_NAME, input);

        //when
        DataSet newValue = new DataSet();
        newValue.put("password", "123456");
        manager.update(TABLE_NAME, 13, newValue);

        //then
        DataSet[] dataSets = manager.getTableData(TABLE_NAME);
        assertEquals(1, dataSets.length);

        DataSet dataSet = dataSets[0];
        assertEquals("[user_id, name, password]", Arrays.toString(dataSet.getNames()));
        assertEquals("[13, Stiven, 123456]", Arrays.toString(dataSet.getValues()));
    }
}
