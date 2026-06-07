/**
 * This class it's used for orders operations between the database.
 */
package BusinessLogic;

import DataAccess.*;
import Model.*;


public class OrdersManagement {


    public OrdersManagement()
    {}


    public String createOrder(int clientId, int productId, int desiredQuantity)
    {

        Product dbProduct = ProductDataAccess.findById(productId);
        Client dbClient = ClientDataAccess.findById(clientId);

        if (dbProduct == null || dbClient == null)
        {
            return("Error: Client or Product not found in database!");
        }

        if (dbProduct.getQuantityInStock() < desiredQuantity)
        {
            return("Error: Insufficient stock!");

        }

        double cost = dbProduct.getPrice() * desiredQuantity;
        Order order = new Order(dbClient.getId(), dbProduct.getId(), desiredQuantity, cost);
        OrderDataAccess.insert(order);

        Bill bill = new Bill(order.getId(), dbClient.getName(), dbProduct.getName(), desiredQuantity, cost);
        LogDataAccess.insert(bill);

        dbProduct.setQuantityInStock(dbProduct.getQuantityInStock() - desiredQuantity);
        return("Order and Bill created successfully with ID: " + order.getId());
    }

    public void deleteOrder(int orderId)
    {
        OrderDataAccess.delete(orderId);
    }




}
