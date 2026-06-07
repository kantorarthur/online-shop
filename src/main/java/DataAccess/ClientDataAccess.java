

package DataAccess;

import Model.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import Connection.*;

public class ClientDataAccess {

    /**
     * this class it's used to make use of client table in the data base
     */

    public static void insert(Client client)
    {
        String insertStatementString = "INSERT INTO client (email, name) VALUES (?,?)";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try
        {

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, client.getEmail());
            insertStatement.setString(2, client.getName());

            insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();
            if (rs.next())
            {
                int generatedId = rs.getInt(1);
                Field idField = client.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(client, generatedId);
            }
        }
        catch (SQLException | NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static Client findById(int id)
    {
        String statement = "SELECT * FROM client WHERE id = ?";
        Client client = null;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(statement))
        {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery())
            {
                if (rs.next())
                {
                    client = new Client(rs.getString("email"), rs.getString("name"));
                    client.setId(rs.getInt("id"));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return client;
    }

    public static String updateName(int clientId, String newName)
    {
        String updateStatementString = "UPDATE client SET name = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setString(1, newName);
            updateStatement.setInt(2, clientId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return ("Client " + clientId + " was updated with new name: " + newName);
            }
            else
            {
                return ("Update failed: Client ID not found.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return "Client with ID" + clientId + "not found";
    }

    public static String updateEmail(int clientId, String newEmail)
    {
        String updateStatementString = "UPDATE client SET email = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setString(1, newEmail);
            updateStatement.setInt(2, clientId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return("Client " + clientId + " was updated with new email: " + newEmail);
            }
            else
            {
                return("Update failed: Client ID not found.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return("Update failed: Client ID not found.");
    }

    public static String delete(int clientId)
    {
        String deleteStatementString = "DELETE FROM client WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try
        {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, clientId);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return "Client with ID " + clientId + " was successfully deleted from database";
            }
            else
            {
                return "Delete failed: Client ID " + clientId + " not found";
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return "Delete failed: Client not found";
    }

    public static List<Client> findAll()
    {
        String query = "SELECT * FROM client";
        List<Client> clients = new java.util.ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())
            {
                Client client = new Client(resultSet.getString("email"), resultSet.getString("name"));
                client.setId(resultSet.getInt("id"));
                clients.add(client);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return clients.stream()
                .toList();
    }

}