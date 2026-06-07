

package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.Validators.*;
import DataAccess.ClientDataAccess;
import Model.Product;

import DataAccess.ProductDataAccess;


public class ProductBusinessLogic {

    /**
     * This class it's used for products operations between the database.
     */

    private List<Validator<Product>> validators;

    public ProductBusinessLogic()
    {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new QuantityInStockValidator());
        validators.add(new PriceValidator());
    }

    public Product findProductById(int id)
    {
        Product product = ProductDataAccess.findById(id);
        if(product == null)
        {
            throw new NoSuchElementException("The product with id " + id + " does not exist in the data base!");
        }
        return product;
    }

    public String insertProduct(Product product)
    {
        for(Validator<Product> validator : validators)
        {
            validator.validate(product);
        }
        return "Product added succesfully";
    }

    public void deleteProduct(int productId)
    {
        ProductDataAccess.delete(productId);
    }

}
