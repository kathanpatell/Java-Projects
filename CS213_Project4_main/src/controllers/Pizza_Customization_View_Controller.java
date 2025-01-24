/**
 * controllers package contains all the classes used to control functionalities to the gui elements
 */
package controllers;

/**
 * Necessary Imports
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import models.Order;
import models.Pizza;
import models.PizzaMaker;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Pizza Customization View controller controls the GUI elements for pizza customization
 *
 * @author 
 */
public class Pizza_Customization_View_Controller {

    /**
     * Current Pizza
     */
    private static Pizza currentPizza;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="customization_pizza_image"
    private ImageView customization_pizza_image; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="add_topping_button"
    private Button add_topping_button; // Value injected by FXMLLoader

    @FXML // fx:id="additional_toppings_list"
    private ListView<String> additional_toppings_list; // Value injected by FXMLLoader

    @FXML // fx:id="checkout_button"
    private Button add_to_order_button; // Value injected by FXMLLoader

    @FXML // fx:id="remove_topping_button"
    private Button remove_topping_button; // Value injected by FXMLLoader

    @FXML // fx:id="running_price_of_pizza_text_area"
    private TextArea running_price_of_pizza_text_area; // Value injected by FXMLLoader

    @FXML // fx:id="select_pizza_size_label"
    private Label select_pizza_size_label; // Value injected by FXMLLoader

    @FXML // fx:id="select_toppings_label"
    private Label select_toppings_label; // Value injected by FXMLLoader

    @FXML // fx:id="selected_pizza_label"
    private Label selected_pizza_label; // Value injected by FXMLLoader

    @FXML // fx:id="selected_toppings_list"
    private ListView<String> selected_toppings_list; // Value injected by FXMLLoader

    @FXML // fx:id="selection_of_pizza_size"
    private ComboBox<String> selection_of_pizza_size; // Value injected by FXMLLoader

    @FXML // fx:id="total_running_price_of_pizza_label"
    private Label total_running_price_of_pizza_label; // Value injected by FXMLLoader

    /**
     * Adding toppings to the pizza
     *
     * @param event Action Event
     */
    @FXML
    void add_topping_to_pizza(ActionEvent event) {
        String currTopping = additional_toppings_list.getSelectionModel().getSelectedItem();

        if (currTopping == null)
            return;
        if (selected_toppings_list.getItems().size() >= 7)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Maximum Toppings reached");
            alert.setHeaderText("You can only add upto 7 toppings");
            alert.setContentText("Please Confirm!");
            alert.showAndWait();
            return;
        }

        additional_toppings_list.getItems().remove(currTopping);
        selected_toppings_list.getItems().add(currTopping);
        currentPizza.addTopping(currTopping);
        running_price_of_pizza_text_area.setText(String.format("%,.2f", currentPizza.price()));

    }

    /**
     * Selected Size of the pizza
     *
     * @param event ActionEvent
     */
    @FXML
    void selectedSize(ActionEvent event) {
        String pizzaSize = selection_of_pizza_size.getSelectionModel().getSelectedItem();

        if (pizzaSize == null)
            return;
        currentPizza.setSize(pizzaSize);
        running_price_of_pizza_text_area.setText(String.format("%,.2f", currentPizza.price()));
    }

    /**
     * Adding pizza to the order
     *
     * @param event ActionEvent
     */
    @FXML
    void add_pizza_to_order(ActionEvent event) {
        Order currorder = Main_View_Controller.currOrder;
        if (currorder != null) {
            Main_View_Controller.currOrder.addToOrder(currentPizza);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Added to Cart");
        alert.setHeaderText("Your Pizza Has been Added to your Cart");
        alert.setContentText("Please Confirm!");
        alert.showAndWait();
        
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * removing the toppings from the pizza
     *
     * @param event ActionEvent
     */
    @FXML
    void remove_topping_from_pizza(ActionEvent event) {
        String currTopping = selected_toppings_list.getSelectionModel().getSelectedItem();

        if (currTopping == null)
            return;
        additional_toppings_list.getItems().add(currTopping);
        selected_toppings_list.getItems().remove(currTopping);
        currentPizza.removeTopping(currTopping);
        running_price_of_pizza_text_area.setText(String.format("%,.2f", currentPizza.price()));
    }

    /**
     * Initializing the GUI elements
     */
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert add_topping_button != null : "fx:id=\"add_topping_button\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert additional_toppings_list != null : "fx:id=\"additional_toppings_list\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert add_to_order_button != null : "fx:id=\"checkout_button\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert remove_topping_button != null : "fx:id=\"remove_topping_button\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert running_price_of_pizza_text_area != null : "fx:id=\"running_price_of_pizza_text_area\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert select_pizza_size_label != null : "fx:id=\"select_pizza_size_label\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert select_toppings_label != null : "fx:id=\"select_toppings_label\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert selected_pizza_label != null : "fx:id=\"selected_pizza_label\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert selected_toppings_list != null : "fx:id=\"selected_toppings_list\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert selection_of_pizza_size != null : "fx:id=\"selection_of_pizza_size\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert total_running_price_of_pizza_label != null : "fx:id=\"total_running_price_of_pizza_label\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        assert customization_pizza_image != null : "fx:id=\"customization_pizza_image\" was not injected: check your FXML file 'pizza_customization_view.fxml'.";
        populatePizzaSizeComboBox();
        populateToppingsLists();
        setInitialPizza();
    }

    /**
     * Adds the sizes to the size comboBox
     */
    private void populatePizzaSizeComboBox() {
        ObservableList<String> items =
                FXCollections.observableArrayList("Small", "Medium", "Large");
        selection_of_pizza_size.setItems(items);
        selection_of_pizza_size.setValue("Small");
    }

    /**
     * sets the toppings list based on the flavor of the pizza
     */
    private void populateToppingsLists() {
        ObservableList<String> availableToppings =
                FXCollections.observableArrayList("Pepperoni", "Ham", "Pineapple", "Onion",
                        "Pepper", "Olives", "Chicken", "Sausage", "Beef", "Mushroom");
        ObservableList<String> addedToppings = FXCollections.observableArrayList();

        String flavor = Main_View_Controller.pizzaType;
        if (flavor.equals("Deluxe")) {
            availableToppings.remove("Chicken");
            availableToppings.remove("Pepper");
            availableToppings.remove("Ham");
            availableToppings.remove("Olives");
            availableToppings.remove("Onion");
            addedToppings.add("Chicken");
            addedToppings.add("Pepper");
            addedToppings.add("Ham");
            addedToppings.add("Olives");
            addedToppings.add("Onion");
        } else if (flavor.equals("Hawaiian")) {
            availableToppings.remove("Pineapple");
            availableToppings.remove("Ham");
            addedToppings.add("Pineapple");
            addedToppings.add("Ham");
        } else if (flavor.equals("Pepperoni")) {
            availableToppings.remove("Pepperoni");
            addedToppings.add("Pepperoni");
        }
        additional_toppings_list.setItems(availableToppings);
        selected_toppings_list.setItems(addedToppings);
    }

    /**
     * Setting the initial Pizza
     */
    private void setInitialPizza() {
        currentPizza = PizzaMaker.createPizza(Main_View_Controller.pizzaType);
        running_price_of_pizza_text_area.setText(String.format("%,.2f", currentPizza.price()));
    }
}
