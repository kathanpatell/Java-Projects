/**
 * model package contains all the classes used to add functionalities to the gui
 */
package models;

/**
* Necessary Imports
*/
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testing for Deluxe
 *
 * @author 
 */
public class DeluxeTest {
    
    /**Price for Small Pizza*/
    private static final double smallPrice = 12.99;
    /**Price for Medium Pizza*/
    private static final double mediumPrice = 14.99;
    /**Price for Large Pizza*/
    private static final double largePrice = 16.99;
    /**Price for Extra toppings on the pizza*/
    private static final double extraToppingPrice = 1.49;
    /**Minimum number of toppings on the pizza*/
    private static final int minimumToppings = 5;
    /**deluxePizza of type Deluxe*/
    Deluxe deluxePizza;
    
    @BeforeEach
    void setUp() {
        deluxePizza = new Deluxe();
    }

    @AfterEach
    void tearDown() {
       deluxePizza = null;
    }

    /**
     * Small pizza with basic 5 toppings
     */
    @Test
    void small_5Toppings()
    {
        assertTrue(deluxePizza.price() == smallPrice);
    }

    /**
     * Medium pizza with basic 5 toppings
     */
    @Test
    void medium_5Toppings()
    {
        deluxePizza.setSize("Medium");
        assertTrue(deluxePizza.price() == mediumPrice);
    }

    /**
     * Large pizza with 5 toppings of users choice
     */
    @Test
    void large_5Toppings_edited()
    {
        deluxePizza.setSize("Large");
        deluxePizza.removeTopping("Chicken");
        deluxePizza.addTopping("Pepperoni");
        assertTrue(deluxePizza.price() == largePrice);
    }

    /**
     * Small pizza with 4 toppings
     */
    @Test
    void small_lessThan5Toppings()
    {
        deluxePizza.removeTopping("Chicken");
        assertTrue(deluxePizza.price() == smallPrice);
    }

    /**
     * Medium pizza with 4 toppings
     */
    @Test
    void medium_lessThan5Toppings()
    {
        deluxePizza.setSize("Medium");
        deluxePizza.removeTopping("Chicken");
        assertTrue(deluxePizza.price() == mediumPrice);
    }

    /**
     * Large pizza with 4 toppings
     */
    @Test
    void large_lessThan5Toppings()
    {
        deluxePizza.setSize("Large");
        deluxePizza.removeTopping("Chicken");
        assertTrue(deluxePizza.price() == largePrice);
    }

    /**
     * small pizza with extra 1 topping
     */
    @Test
    void small_moreThan5Toppings()
    {
        deluxePizza.addTopping("Pepperoni");
        assertTrue(deluxePizza.price() == smallPrice + extraToppingPrice);
    }

    /**
     * medium pizza with extra 2 topping
     */
    @Test
    void medium_moreThan5Toppings()
    {
        deluxePizza.setSize("Medium");
        deluxePizza.addTopping("Pepperoni");
        deluxePizza.addTopping("Pineapple");
        assertTrue(deluxePizza.price() == mediumPrice + 2*extraToppingPrice);
    }

    /**
     * large pizza with extra 3 topping. 1 topping added twice
     */
    @Test
    void large_moreThan5Toppings_adding_same_topping_twice()
    {
        deluxePizza.setSize("Large");
        deluxePizza.addTopping("Pepperoni");
        deluxePizza.addTopping("Pepperoni");
        deluxePizza.addTopping("Pineapple");
        assertTrue(deluxePizza.price() == largePrice + 2*extraToppingPrice);
    }
}
