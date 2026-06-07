

package Model;

import DataAccess.ProductDataAccess;

public class Product {
    /**
     * Model class of Product. In this class we are storing the ID(which we receive from the database), name of product.,
     * price of product and the quantity in stock.
     */
    private int quantityInStock;
    private double price;
    private int id;
    private String name;

    public Product(){};

    public Product(int quantityInStock, double price, String name)
    {
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public int getQuantityInStock()
    {
        return this.quantityInStock;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
        ProductDataAccess.updateName(this.getId(),name);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPrice(double price)
    {
        this.price = price;
        ProductDataAccess.updatePrice(this.getId(),price);
    }

    public void setQuantityInStock(int quantityInStock)
    {
        this.quantityInStock = quantityInStock;
        ProductDataAccess.updateStock(this.getId(), quantityInStock);
    }

    @Override
    public String toString()
    {
        return "ID of product: " + this.getId() + " ,name of product: " + this.getName() +
        " ,price of product" + this.getPrice() + " and quantity in stock: " + this.getQuantityInStock();
    }


}

