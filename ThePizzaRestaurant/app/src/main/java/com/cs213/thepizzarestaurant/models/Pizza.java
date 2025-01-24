/**
 * model package contains all the classes used to add functionalities to the gui
 */
package com.cs213.thepizzarestaurant.models;

/**
 * Necessary Imports
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract pizza class to define specific pizza types.
 *
 * @author 
 */
public abstract class Pizza implements Serializable {

    /**
     * Array List for toppings
     */
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();

    /**
     * Size of the pizza
     */
    protected Size size;

    /**
     * Price of the pizza
     * @return price with the selections
     */
    public abstract double price();

    /**
     * Setting size of the pizza
     * @param newsize new size
     */
    public abstract void setSize(String newsize);

    /**
     * Adding toppings to the pizza
     * @param currTopping topping to add
     */
    public abstract void addTopping(String currTopping);

    /**
     * Removing toppings from the pizza
     * @param topping topping to remove
     */
    public abstract void removeTopping(String topping);

    /**
     * To string method
     */
    public abstract String toString();
}
