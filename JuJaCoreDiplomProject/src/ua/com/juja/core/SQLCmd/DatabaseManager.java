package ua.com.juja.core.SQLCmd;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by ubuntu on 8/23/15.
 */
public class DatabaseManager {
    private Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String database = "postgres";
        String userName = "postgres";
        String password = "12345";
        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.connect(database, userName, password);

        //Connection connection = databaseManager.getConnection();

        //delete
        databaseManager.clear("user");

        // insert
        DataSet dataSet = new DataSet();
        dataSet.put("user_id", 13);
        dataSet.put("name", "Stiven");
        dataSet.put("password", "12345");
        databaseManager.create(dataSet);

        //update
        /*stmt = connection.createStatement();
        String sql = "UPDATE public.user set password = 12345 where user_id=1;";
        stmt.executeUpdate(sql);
        stmt.close();*/

        // select
        String tableName = "user";
        DataSet[] tableData = databaseManager.getTableData(tableName);

        System.out.println(Arrays.toString(tableData));

        // table names
        String[] tables = databaseManager.getTableNames();

        System.out.println(Arrays.toString(tables));
    }

    public DataSet[] getTableData(String tableName) {
        try {
            int size = getSize(tableName);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM public.user");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            DataSet[] result = new DataSet[size];
            int index = 0;

            while (resultSet.next()) {
                DataSet dataSet = new DataSet();
                result[index] = dataSet;
                index += 1;

                for ( int i = 1; i <= resultSetMetaData.getColumnCount(); i++ ) {
                    dataSet.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
            }

            resultSet.close();
            stmt.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();

            return new DataSet[0];
        }
    }

    public int getSize(String tableName) throws SQLException {
        int size = 0;
        Statement stmt = connection.createStatement();
        ResultSet rsCount = stmt.executeQuery("SELECT COUNT(*) FROM public." + tableName);

        rsCount.next();

        size = rsCount.getInt(1);

        rsCount.close();

        return size;
    }

    public String[] getTableNames() {
        try {
            Statement stmt = connection.createStatement();
            String[] tables = new String[100];
            int index = 0;
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'");

            while (rs.next()) {
                tables[index] = rs.getString("table_name");
                index += 1;
            }

            rs.close();
            stmt.close();

            tables = Arrays.copyOf(tables, index, String[].class);

            return tables;
        } catch (SQLException e) {
            e.printStackTrace();

            return new String[0];
        }
    }

    public void connect(String database, String userName, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please add jdbc jar to project.");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/" + database, userName,
                    password);
        } catch (SQLException e) {
            System.out.println(String.format("Cant get connection for database:%s user:%s", database, userName));
            e.printStackTrace();

            connection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void clear(String tableName) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE from public." + tableName);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(DataSet input) {
        try {
            String[] names = input.getNames();
            Object[] data = input.getValues();
            int last = names.length - 1;
            Statement stmt = connection.createStatement();
            String columnsName = getNameFormated(input, "%s,");
            String values = getValuesFormated(input, "'%s',");

           /* for ( int i = 0; i < last; i++ ) {
                columnsName = columnsName + names[i] + ",";
            }

            columnsName += names[last];*/
            /*last = data.length - 1;

            for ( int i = 0; i < last; i++ ) {
                values = values + "'" + data[i].toString() + "''" + ",";
            }

            values += names[last];*/

            stmt.executeUpdate("INSERT INTO public.user (" + columnsName +
                    ") VALUES (" + values + ")");
            /*stmt.executeUpdate("INSERT INTO public.user (user_id, name, password)" +
                    "VALUES (13, 'Stiven6', 'Pupkin')");*/
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getNameFormated(DataSet newValue, String format) {
        String string = "";
        for (String name : newValue.getNames()) {
            string += String.format(format, name);
        }
        string = string.substring(0, string.length() - 1);
        return string;
    }

    private String getValuesFormated(DataSet input, String format) {
        String values = "";
        for (Object value: input.getValues()) {
            values += String.format(format, value);
        }
        values = values.substring(0, values.length() - 1);
        return values;
    }
}
