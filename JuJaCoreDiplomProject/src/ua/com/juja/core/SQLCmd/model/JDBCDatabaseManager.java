package ua.com.juja.core.SQLCmd.model;

import java.sql.*;
import java.util.Arrays;

public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;

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

    @Override
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
            //e.printStackTrace(); //TODO: Debug mode
            throwSQLException(e);

            return null;
        }
    }

    @Override
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
            //e.printStackTrace();
            throwSQLException(e);

            return null;
        }
    }

    @Override
    public void connect(String database, String userName, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/" + database, userName,
                    password);
        } catch (SQLException e) {
            connection = null;

            throw new RuntimeException(String.format("Cant get connection for database:%s user:%s", database, userName), e);
        }
    }

    @Override
    public void clear(String tableName) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE from public." + tableName);
            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            throwSQLException(e);
        }
    }

    @Override
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
            //e.printStackTrace();
            throwSQLException(e);
        }
    }

    @Override
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
            //e.printStackTrace();
            throwSQLException(e);
        }
    }

    @Override
    public String[] getTableColumns(String tableName) {
        try {
            Statement statement = connection.createStatement();
            String[] result = new String[100];
            int index = 0;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM information_schema.columns WHERE table_schema='public' AND table_name='" + tableName + "'");

            while (resultSet.next()) {
                result[index] = resultSet.getString("column_name");
                index += 1;
            }

            resultSet.close();
            statement.close();

            result = Arrays.copyOf(result, index, String[].class);

            return result;
        } catch (SQLException e) {
            //e.printStackTrace(); //TODO: Debug mode
            throwSQLException(e);

            return null;
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    private void throwSQLException(SQLException e) {
        throw new IllegalArgumentException(e);
    }
}
