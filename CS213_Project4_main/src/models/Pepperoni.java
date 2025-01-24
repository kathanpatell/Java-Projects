/**
 * model package contains all the classes used to add functionalities to the gui
 */
package models;

/**
 * Pepperoni Pizza class for pepperoni order
 *
 * @author 
 */
public class Pepperoni extends Pizza {

    /**
     * Price for small pizza
     */
    private static final double smallPrice = 8.99;
    /**
     * Price for medium pizza
     */
    private static final double mediumPrice = 10.99;
    /**
     * Price for large pizza
     */
    private static final double largePrice = 12.99;
    /**
     * Price for extra toppings on pizza
     */
    private static final double extraToppingPrice = 1.49;
    /**
     * Number of minimum toppings required on a pizza
     */
    private static final int minimumToppings = 1;

    /**
     * Default Constructor to initialize an instance of pepperoni pizza order
     */
    public Pepperoni() {
        this.size = Size.Small;
        this.toppings.add(Topping.Pepperoni);
    }

    /**
     * Constructor to initialize an instance of pepperoni pizza order
     * @param size size of the pizza
     * @param toppings toppinfs to add
     */
    /*
    public Pepperoni(Size size, ArrayList<Topping> toppings)
    {
        this.size = size;
        this.toppings.addAll(toppings);
    }*/

    /**
     * set the size of a pizza
     *
     * @param newsize new size to be set
     */
    public void setSize(String newsize) {
        this.size = Size.valueOf(newsize);
    }

    /**
     * add Topping to the toppings list
     *
     * @param currTopping the  topping to add
     */
    public void addTopping(String currTopping) {
        Topping topping = Topping.valueOf(currTopping);
        if (!this.toppings.contains(topping))
            this.toppings.add(topping);
    }

    /**
     * remove Topping from the toppings list
     *
     * @param currTopping the  topping to add
     */
    public void removeTopping(String currTopping) {
        Topping topping = Topping.valueOf(currTopping);
        if (this.toppings.contains(topping))
            this.toppings.remove(topping);
    }

    /**
     * tostring method for a pizza
     *
     * @return string representation of a pizza
     */
    @Override
    public String toString() {
        StringBuilder toppingsStr = new StringBuilder();

        for (int i = 0; i < this.toppings.size(); i++) {
            toppingsStr.append(toppings.get(i).toString());
            if (i != this.toppings.size() - 1)
                toppingsStr.append(", ");
        }

        String result = "Flavor-> Pepperoni :: Size-> " + this.size.toString()
                + " :: Cost-> " + String.format("%,.2f", this.price()) + " :: Toppings-> " +
                toppingsStr;

        return result;
    }

    /**
     * method to calculate the price of the pizza
     *
     * @return price of the pizza
     */
    @Override
    public double price() {
        double currPrice = 0;
        final int numOfExtraToppings = this.toppings.size() - minimumToppings;
        if (this.size.equals(Size.Small))
            currPrice = smallPrice;
        else if (this.size.equals(Size.Medium))
            currPrice = mediumPrice;
        else
            currPrice = largePrice;

        if (numOfExtraToppings > 0)
            currPrice += numOfExtraToppings * extraToppingPrice;

        return currPrice;
    }
}
