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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Store Orders View Controller controls the GUI elements for store orders
 *
 * @author 
 */
public class Store_Orders_View_Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="export_store_orders_button"
    private Button export_store_orders_button; // Value injected by FXMLLoader

    @FXML // fx:id="list_view_for_store_orders"
    private ListView<String> list_view_for_store_orders; // Value injected by FXMLLoader

    @FXML // fx:id="remove_order_from_store_orders_button"
    private Button remove_order_from_store_orders_button; // Value injected by FXMLLoader

    /**
     * Exporting the store orders into a file
     *
     * @param event Action Event
     */
    @FXML
    void export_store_orders(ActionEvent event) throws IOException {
        export();
    }

    private void export() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);
        ObservableList<String> storeOrderData =  list_view_for_store_orders.getItems();
        try {
            FileWriter mywriter = new FileWriter(targetFile);
            for (int i = 0; i < storeOrderData.size(); i++) {
                mywriter.write(storeOrderData.get(i));
                mywriter.write("\n");
            }
            mywriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert PopUp");
        alert.setHeaderText("Data Exported");
        alert.setContentText("All store orders have been successfully exported");
        alert.setContentText("Please Confirm!");
        alert.showAndWait();
    }

    /**
     * Removing the order from store order
     *
     * @param event Action Event
     */
    @FXML
    void remove_order_from_store_order(ActionEvent event) {
        String row = list_view_for_store_orders.getSelectionModel().getSelectedItem();
        if (row == null)
            return;
        String[] tokens = row.split(" ");
        int orderIdToRemove = Integer.parseInt(tokens[2]);

        Main_View_Controller.storeOrders.removeOrder(orderIdToRemove);
        list_view_for_store_orders.getItems().removeAll();
        displayAllOrders();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remove From Store Orders");
        alert.setHeaderText("Order successfully removed");
        alert.setContentText("Please Confirm!");
        alert.showAndWait();


    }

    /**
     * Initializing the GUI elements
     */
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert export_store_orders_button != null : "fx:id=\"export_store_orders_button\" was not injected: check your FXML file 'store_orders_view.fxml'.";
        assert list_view_for_store_orders != null : "fx:id=\"list_view_for_store_orders\" was not injected: check your FXML file 'store_orders_view.fxml'.";
        assert remove_order_from_store_orders_button != null : "fx:id=\"remove_order_from_store_orders_button\" was not injected: check your FXML file 'store_orders_view.fxml'.";

        displayAllOrders();
    }

    /**
     * display all the order there exists
     */
    private void displayAllOrders() {
        ArrayList<Order> orders = Main_View_Controller.storeOrders.getOrders();
        ObservableList<String> orderDetails = FXCollections.observableArrayList();
        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder = orders.get(i);
            if (currentOrder == null)
                return;
            ArrayList<String> pizzas = currentOrder.getPizzasOrdered();
            for (int j = 0; j < pizzas.size(); j++) {
                String result = currentOrder + " :: " + pizzas.get(j);
                orderDetails.add(result);
            }

        }

        list_view_for_store_orders.setItems(orderDetails);
    }

}
