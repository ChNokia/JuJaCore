package ua.com.juja.core.SQLCmd;

import java.sql.*;
import java.util.Arrays;

public class DatabaseManager {
    private Connection connection;

   /* public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
        /*String tableName = "user";
        DataSet[] tableData = databaseManager.getTableData(tableName);

        System.out.println(Arrays.toString(tableData));

        // table names
        String[] tables = databaseManager.getTableNames();

        System.out.println(Arrays.toString(tables));
    }*/

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

    private int getSize(String tableName) throws SQLException {
        int size = 0;
        Statement statement = connection.createStatement();
        ResultSet resultCount = statement.executeQuery("SELECT COUNT(*) FROM public." + tableName);

        resultCount.next();

        size = resultCount.getInt(1);

        resultCount.close();

        return size;
    }

    public DataSet[] getTableData(String tableName) {
        try {
            int size = getSize(tableName);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.user");
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
            statement.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();

            return new DataSet[0];
        }
    }

    public String[] getTableNames() {
        try {
            Statement statement = connection.createStatement();
            String[] tables = new String[100];
            int index = 0;
            ResultSet resultSet = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'");

            while (resultSet.next()) {
                tables[index] = resultSet.getString("table_name");
                index += 1;
            }

            resultSet.close();
            statement.close();

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

    public void clear(String tableName) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE from public." + tableName);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(String tableNmae, DataSet input) {
        try {
            String[] names = input.getNames();
            Object[] data = input.getValues();
            int last = names.length - 1;
            Statement statement = connection.createStatement();
            String columnsName = getNameFormated(input, "%s,");
            String values = getValuesFormated(input, "'%s',");

            statement.executeUpdate("INSERT INTO public." + tableNmae + "(" + columnsName +
                    ") VALUES (" + values + ")");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String tableName, int id, DataSet newValue) {
        try {
            String tableNames = getNameFormated(newValue, "%s = ?,");

            //UPDATE public.user set password = 12345 where user_id=1;
            String sql = "UPDATE public." + tableName + " SET " + tableNames + " WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object value : newValue.getValues()) {
                preparedStatement.setObject(index, value);
                index++;
            }
            preparedStatement.setObject(index, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
