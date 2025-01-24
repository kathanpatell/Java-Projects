/**
 * model package contains all the classes used to add functionalities to the gui
 */
package models;

/**
 * Necessary Imports
 */

import java.util.ArrayList;

/**
 * Order class has objects that holds customer number and their list of pizza orders
 *
 * @author 
 */
public class Order {

    /**
     * Phone Number
     */
    private String phoneNum;
    /**
     * Array List for Pizzas Ordered
     */
    private ArrayList<Pizza> pizzasOrdered;
    /**
     * ID number for orders
     */
    private int orderId;
    /**
     * ID = 0
     */
    private static int id = 0;

    /**
     * Constructor to create an Order object
     *
     * @param phoneNum Phone Number of Customer
     */
    public Order(String phoneNum) {
        this.phoneNum = phoneNum;
        this.pizzasOrdered = new ArrayList<Pizza>();
        this.orderId = id++;
    }

    /**
     * returns phone number of current order customer
     *
     * @return phone number
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * returns orderid associated with the order
     *
     * @return orderid associated with the order
     */
    public int getOrderId() {
        return this.orderId;
    }

    /**
     * returns all pizzas in the current order
     *
     * @return pizza list
     */
    public ArrayList<String> getPizzasOrdered() {
        ArrayList<String> pizzas = new ArrayList<String>();
        for (int i = 0; i < this.pizzasOrdered.size(); i++) {
            pizzas.add(this.pizzasOrdered.get(i).toString());
        }
        return pizzas;
    }

    /**
     * add a new pizza to current order
     *
     * @param pizza pizza to add
     */
    public void addToOrder(Pizza pizza) {
        this.pizzasOrdered.add(pizza);
        //this.orderId.add(id++);
    }


    /**
     * remove an existing pizza from the order
     *
     * @param index index of the pizza to remove
     */
    public void removeFromOrder(int index) {
        this.pizzasOrdered.remove(index);
    }

    /**
     * Returns the total cost of the order pre tax
     *
     * @return total cost of the order
     */
    public double orderCost() {
        double cost = 0;
        for (int i = 0; i < this.pizzasOrdered.size(); i++) {
            cost += this.pizzasOrdered.get(i).price();
        }

        return cost;
    }

    /**
     * to string method for an order. Depicts an order id and the phone number associated with the order
     *
     * @return string representation of order
     */
    @Override
    public String toString() {
        return "Order id-> " + (this.orderId) + " :: Phone number-> " + this.phoneNum;
    }


}
