

package Model;



public record Bill(
        /**
         * this is an immutable class of bill. we are going to store the orderId, client name, product name, quantity and total cost
         */
        int orderId,
        String clientName,
        String productName,
        int quantity,
        double totalCost
) {}