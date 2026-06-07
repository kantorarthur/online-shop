

package DataAccess;

import Model.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import Connection.*;

public class LogDataAccess {

    /**
     * this class it's used to make use of bills table in the data base
     */
    public static void insert(Bill bill)
    {
        String insertStatementString = "INSERT INTO log (order_id, client_name, product_name, quantity, total_cost) VALUES (?,?,?,?,?)";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try
        {

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setInt(1, bill.orderId());
            insertStatement.setString(2, bill.clientName());
            insertStatement.setString(3, bill.productName());
            insertStatement.setInt(4, bill.quantity());
            insertStatement.setDouble(5, bill.totalCost());

            insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();
        }
        catch (SQLException e)
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

    public static String delete(int orderId)
    {
        String deleteStatementString = "DELETE FROM log WHERE order_id = ?";

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

    public static List<Bill> findAll()
    {
        String query = "SELECT * FROM log";
        List<Bill> bills = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())
            {
                Bill bill = new Bill(resultSet.getInt("order_id"), resultSet.getString("client_name"), resultSet.getString("product_name"), resultSet.getInt("quantity"), resultSet.getDouble("total_cost"));
                bills.add(bill);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return bills.stream()
                .toList();
    }

}