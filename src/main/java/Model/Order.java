

package Model;

import DataAccess.OrderDataAccess;
import DataAccess.ProductDataAccess;

public class Order {
    /**
     * Model class of Order. We are storing the ID(which we receive from the dataBase), product ID, client ID,
     * number of products and the total cost of the order.
     */
    private int id;
    private int productId;
    private int clientId;
    private int numberOfProducts;
    private double totalCost;

    public Order(){};

    public Order(int clientId, int productId, int numberOfProducts, double totalCost)
    {
        this.clientId = clientId;
        this.productId = productId;
        this.numberOfProducts = numberOfProducts;
        this.totalCost = totalCost;
    }

    public int getProductId()
    {
        return this.productId;
    }

    public int getClientId()
    {
        return this.clientId;
    }

    public int getId()
    {
        return this.id;
    }

    public double getTotalCost()
    {
        return this.totalCost;
    }

    public int getNumberOfProducts()
    {
        return this.numberOfProducts;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
        OrderDataAccess.updateProductId(this.getId(),productId);
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
        OrderDataAccess.updateClientId(this.getId(),clientId);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNumberOfProducts(int numberOfProducts)
    {
        this.numberOfProducts = numberOfProducts;
        OrderDataAccess.updateNumberOfProducts(this.getId(),numberOfProducts);
    }

    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
        OrderDataAccess.updateTotalCost(this.getId(),totalCost);
    }

    @Override
    public String toString()
    {
        return "Order with ID: " + this.getId() + " ,product ID: " + this.getProductId() +
                " ,client id: " +  this.getClientId() + " ,number of products: " + this.getNumberOfProducts() +
                " with total cost: " + this.getTotalCost();
    }


}