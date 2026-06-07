

package DataAccess;

import Model.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import Connection.*;

public class ProductDataAccess {


    /**
     * this class it's used to make use of product table in the data base
     */
    private final static String insertStatementString = "INSERT INTO Product (name, price, quantity_in_stock) VALUES (?,?,?)";

    public static void insert(Product product)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try
        {

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, product.getName());
            insertStatement.setDouble(2, product.getPrice());
            insertStatement.setInt(3, product.getQuantityInStock());

            insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();
            if (rs.next())
            {
                int generatedId = rs.getInt(1);
                Field idField = product.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(product, generatedId);
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



    public static String updateStock(int productId, int newStock)
    {
        String updateStatementString = "UPDATE product SET quantity_in_stock = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setInt(1, newStock);
            updateStatement.setInt(2, productId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return("Stock updated successfully in database for product ID: " + productId);
            }
            else
            {
                return("Update failed: Product with ID " + productId + " not found.");
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
        return("Update failed: Product ID not found.");
    }

    public static Product findById(int id)
    {
        String statement = "SELECT * FROM product WHERE id = ?";
        Product product = null;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(statement))
        {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery())
            {
                if (rs.next())
                {
                    product = new Product(rs.getInt("quantity_in_stock"), rs.getDouble("price"), rs.getString("name"));
                    product.setId(rs.getInt("id"));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return product;
    }

    public static String updatePrice(int productId, double newPrice)
    {
        String updateStatementString = "UPDATE product SET price = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setDouble(1, newPrice);
            updateStatement.setInt(2, productId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return("Product " + productId + " was updated with new price " + newPrice);
            }
            else
            {
                return("Update failed: Product ID not found.");
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
        return("Update failed: Product ID not found.");
    }

    public static String updateName(int productId, String newName)
    {
        String updateStatementString = "UPDATE product SET name = ? WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementString);

            updateStatement.setString(1, newName);
            updateStatement.setInt(2, productId);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return("Product " + productId + " was updated with new name: " + newName);
            }
            else
            {
                return("Update failed: Product ID not found.");
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
        return("Update failed: Product ID not found.");
    }
    public static String delete(int productId)
    {
        String deleteStatementString = "DELETE FROM product WHERE id = ?";

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;

        try
        {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, productId);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                return("Product with ID " + productId + " was successfully deleted from database");
            }
            else
            {
                return("Delete failed: Product ID " + productId + " not found");
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
        return("Delete failed: Product ID " + productId + " not found");
    }

    public static List<Product> findAll()
    {
        String query = "SELECT * FROM product";
        List<Product> products = new java.util.ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())
            {
                Product product = new Product(resultSet.getInt("quantity_in_stock"),resultSet.getDouble("price"),resultSet.getString("name"));
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return products.stream()
                .toList();
    }
}
