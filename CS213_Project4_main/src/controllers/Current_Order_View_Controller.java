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

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.Main_View_Controller.currOrder;
import static controllers.Main_View_Controller.storeOrders;

/**
 * Current Order class controls GUI for check current order
 *
 * @author 
 */
public class Current_Order_View_Controller {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="customer_phone_number_label_2"
    private Label customer_phone_number_label_2; // Value injected by FXMLLoader

    @FXML // fx:id="customer_phone_number_text_area_2"
    private TextArea customer_phone_number_text_area_2; // Value injected by FXMLLoader

    @FXML // fx:id="final_amount_label"
    private Label final_amount_label; // Value injected by FXMLLoader

    @FXML // fx:id="final_amount_text_area"
    private TextArea final_amount_text_area; // Value injected by FXMLLoader

    @FXML // fx:id="order_subtotal_label"
    private Label order_subtotal_label; // Value injected by FXMLLoader

    @FXML // fx:id="order_subtotal_text_area"
    private TextArea order_subtotal_text_area; // Value injected by FXMLLoader

    @FXML // fx:id="place_order_button"
    private Button place_order_button; // Value injected by FXMLLoader

    @FXML // fx:id="remove_item_button"
    private Button remove_item_button; // Value injected by FXMLLoader

    @FXML // fx:id="taxes_label"
    private Label taxes_label; // Value injected by FXMLLoader

    @FXML // fx:id="taxes_text_area"
    private TextArea taxes_text_area; // Value injected by FXMLLoader

    @FXML // fx:id="your_cart_label"
    private Label your_cart_label; // Value injected by FXMLLoader

    @FXML // fx:id="your_cart_list_view"
    private ListView<String> your_cart_list_view; // Value injected by FXMLLoader

    /**
     * Method for button place current order
     *
     * @param event ActionEvent
     */
    @FXML
    void place_current_order(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert PopUp");
        alert.setHeaderText("Order Placed");
        alert.setContentText("You are about to submit your order.");
        alert.setContentText("Please Confirm!");
        alert.showAndWait();
        storeOrders.addOrder(currOrder);
        currOrder = null;
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Method for removing item from the current order
     *
     * @param event Actionevent
     */
    @FXML
    void remove_item_from_current_order(ActionEvent event) {
        int index = your_cart_list_view.getSelectionModel().getSelectedIndex();
        if (index < 0)
            return;

        currOrder.removeFromOrder(index);
        your_cart_list_view.getItems().remove(index);
        calculateCost();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert PopUp");
        alert.setHeaderText("Pizza Removed");
        alert.setContentText("Item removed from cart");
        alert.setContentText("Please Confirm!");
        alert.showAndWait();
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert customer_phone_number_label_2 != null : "fx:id=\"customer_phone_number_label_2\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert customer_phone_number_text_area_2 != null : "fx:id=\"customer_phone_number_text_area_2\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert final_amount_label != null : "fx:id=\"final_amount_label\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert final_amount_text_area != null : "fx:id=\"final_amount_text_area\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert order_subtotal_label != null : "fx:id=\"order_subtotal_label\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert order_subtotal_text_area != null : "fx:id=\"order_subtotal_text_area\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert place_order_button != null : "fx:id=\"place_order_button\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert remove_item_button != null : "fx:id=\"remove_item_button\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert taxes_label != null : "fx:id=\"taxes_label\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert taxes_text_area != null : "fx:id=\"taxes_text_area\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert your_cart_label != null : "fx:id=\"your_cart_label\" was not injected: check your FXML file 'current_order_view.fxml'.";
        assert your_cart_list_view != null : "fx:id=\"your_cart_list_view\" was not injected: check your FXML file 'current_order_view.fxml'.";
        displayCurrentOrder();
        calculateCost();
    }

    /**
     * helper method to display initial state of the order
     */
    private void displayCurrentOrder() {
        if (currOrder == null)
            return;
        customer_phone_number_text_area_2.setText(currOrder.getPhoneNum());
        ObservableList<String> pizzasInOrder = FXCollections.observableArrayList();
        pizzasInOrder.addAll(currOrder.getPizzasOrdered());
        your_cart_list_view.setItems(pizzasInOrder);
    }

    /**
     * display the cost related to the order
     */
    private void calculateCost() {
        if (currOrder == null)
            return;
        double netOrderCost = currOrder.orderCost();
        order_subtotal_text_area.setText(String.format("%,.2f", netOrderCost));
        double taxes = (6.625 / 100) * netOrderCost;
        taxes_text_area.setText(String.format("%,.2f", taxes));
        double total = taxes + netOrderCost;
        final_amount_text_area.setText(String.format("%,.2f", total));
    }

}
