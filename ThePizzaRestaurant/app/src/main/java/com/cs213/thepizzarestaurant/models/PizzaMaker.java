/**
 * model package contains all the classes used to add functionalities to the gui
 */
package com.cs213.thepizzarestaurant.models;

/**
 * PizzaMaker class to create instances of pizzas
 *
 * @author 
 */
public class PizzaMaker {

    /**
     * Create pizza of the desired flavor
     *
     * @param flavor flavor of the pizza as a string
     * @return Pizza of the requested flavor
     */
    public static Pizza createPizza(String flavor) {
        if (flavor.equals("Deluxe"))
            return new Deluxe();

        else if (flavor.equals("Hawaiian"))
            return new Hawaiian();

        else if (flavor.equals("Pepperoni"))
            return new Pepperoni();

        else
            return null;
    }
}
