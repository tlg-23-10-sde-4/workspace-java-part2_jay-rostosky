/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.product;

import com.javatunes.billing.Location;
import com.javatunes.billing.TaxCalculator;
import com.javatunes.billing.TaxCalculatorFactory;
import java.util.Collection;

public class Order {
    private final String id;
    private final Location location;
    private double cost;

    public Order(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    /*
     * The TaxCalculator needed is a function of Location.
     * We'll fetch it from a factory instead of "new OnlineTax()," etc., in here.
     *
     * After the fetch, we can simply delegate to its taxAmount() method to do the actual work.
     */
    public double getTax() {
        TaxCalculator calc = TaxCalculatorFactory.getTaxCalculator(getLocation());

        return calc.taxAmount(getCost());
    }

    /**
     * DONE:
     * get the items from the cart and iterate over them, print each item's product code
     * get cart total and print
     */
    public void processCart(ShoppingCart<? extends Product> cart) {
        System.out.println("Order: " + getId() + " contains the following:");

        Collection<? extends Product> cartItems = cart.allItems();
        for (Product product : cartItems) {
            System.out.println(product.getCode());
        }
        this.cost = cart.total();  // assign to field at top, needed for getTax()
        System.out.println("Order Total: " + getCost());
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public double getCost() {
        return cost;
    }
}