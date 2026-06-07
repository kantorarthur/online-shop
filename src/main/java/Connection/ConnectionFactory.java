
package Connection;

import java.util.logging.*;
import java.sql.*;

public class ConnectionFactory {
    /**
     *this class it's used to make the connection between the DataBase and the application
     */

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());


    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "22036119KaN";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory()
    {
        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private Connection createConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.SEVERE, "Error connecting at the data base!", e);
        }
        return connection;
    }

    public static Connection getConnection()
    {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Error at closing the connection!", e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Error at closing statement!", e);
            }
        }
    }

    public static void close(ResultSet resultSet)
    {
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                LOGGER.log(Level.WARNING, "Error at closing result set!", e);
            }
        }
    }
}