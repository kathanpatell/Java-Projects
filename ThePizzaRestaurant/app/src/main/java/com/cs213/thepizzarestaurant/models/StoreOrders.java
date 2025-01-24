/**
 * model package contains all the classes used to add functionalities to the gui
 */
package com.cs213.thepizzarestaurant.models;

/**
 * Necessary Imports
 */

import java.util.ArrayList;

/**
 * Class to store all orders made by the user
 */
public class StoreOrders {
    /**
     * Array List to store orders
     */
    private ArrayList<Order> orders;

    /**
     * Initializing Array List to store orders
     */
    public StoreOrders() {
        orders = new ArrayList<Order>();
    }

    /**
     * Gives back orders stored in the array list
     *
     * @return Orders in the Array List
     */
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    /**
     * Adding orders to the Array list
     *
     * @param order Array List Orders
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Removing an order from the array list
     *
     * @param orderId ID of the order in the array list
     */
    public void removeOrder(int orderId) {

        for (int i = 0; i < this.orders.size(); i++) {
            Order tempOrder = orders.get(i);
            if (tempOrder.getOrderId() == orderId)
                this.orders.remove(tempOrder);

        }

    }

}
