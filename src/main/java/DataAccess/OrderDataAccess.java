
package DataAccess;

import Model.*;
import java.lang.reflect.Field;
import java.sql.*;
import Connection.*;

public class OrderDataAccess {

    /**
     * this class it's used to make use of order table in the data base
     */

    private final static String insertStatementString = "INSERT INTO Orders (product_id, client_id, total_cost, number_of_products) VALUES (?,?,?,?)";

    public static void insert(Order order)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try
        {

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setInt(1, order.getProductId());
            insertStatement.setInt(2, order.getClientId());
            insertStatement.setDouble(3, order.getTotalCost());
            insertStatement.setInt(4, order.getNumberOfProducts());

            insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();
            if (rs.next())
            {
                int generatedId = rs.getInt(1);
                Field idField = order.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(order, generatedId);
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

    public static void updateProductId(int orderId, int newProductId)
    {
        String updateStatementString = "UPDATE orders SET product_id = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setInt(1, newProductId);
            updateStatement.setInt(2, orderId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Order " + orderId + " was updated with new product ID: " + newProductId);
            }
            else
            {
                System.out.println("Update failed: Order ID not found.");
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
    }

    public static void updateClientId(int orderId, int newClientId)
    {
        String updateStatementString = "UPDATE orders SET client_id = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setInt(1, newClientId);
            updateStatement.setInt(2, orderId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Order " + orderId + " was updated with new client ID: " + newClientId);
            }
            else
            {
                System.out.println("Update failed: Order ID not found.");
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
    }

    public static void updateNumberOfProducts(int orderId, int newNumberOfProducts)
    {
        String updateStatementString = "UPDATE orders SET number_of_products = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setInt(1, newNumberOfProducts);
            updateStatement.setInt(2, orderId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Order " + orderId + " was updated with new number of products: " + newNumberOfProducts);
            }
            else
            {
                System.out.println("Update failed: Order ID not found.");
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
    }

    public static void updateTotalCost(int orderId, double newTotalCost)
    {
        String updateStatementString = "UPDATE orders SET total_cost = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setDouble(1, newTotalCost);
            updateStatement.setInt(2, orderId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Order " + orderId + " was updated with new total cost: " + newTotalCost);
            }
            else
            {
                System.out.println("Update failed: Order ID not found.");
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
    }

    public static String delete(int orderId)
    {
        String deleteStatementString = "DELETE FROM orders WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try
        {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, orderId);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return("Order with ID " + orderId + " was successfully deleted from database");
            }
            else
            {
                return("Delete failed: Order ID " + orderId + " not found");
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
        return("Delete failed: Order ID " + orderId + " not found");
    }
}
