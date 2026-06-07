

package BusinessLogic.Validators;

import Model.Product;

public class QuantityInStockValidator implements Validator<Product> {

    /**
     * This class it's used to validate the Product when trying to insert in database. we are checking the
     * quantity in stock
     */

    @Override
    public void validate(Product product)
    {
        if(product.getQuantityInStock() < 0)
        {
            throw new IllegalArgumentException("The quantity in stock can't be negative!");
        }
    }
}
