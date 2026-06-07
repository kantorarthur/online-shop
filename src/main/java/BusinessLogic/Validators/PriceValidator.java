

package BusinessLogic.Validators;

import Model.Product;

public class PriceValidator implements Validator<Product>{
    /**
     * This class it's used to validate the Product when trying to insert in database. we are checking the price
     */


    @Override
    public void validate(Product product)
    {
        if(product.getPrice() <= 0)
        {
            throw new IllegalArgumentException("The price should be greater than 0!");
        }
    }

}
