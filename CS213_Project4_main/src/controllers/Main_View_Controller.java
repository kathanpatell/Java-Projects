/**
 * controllers package contains all the classes used to control functionalities to the gui elements
 */
package controllers;

/**
 * Necessary Imports
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Order;
import models.StoreOrders;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main View Controller controls the GUI elements for the main screen of the program
 *
 * @author 
 */
public class Main_View_Controller {

    /**
     * Pizza Type
     */
    public static String pizzaType;
    /**
     * Current Customer Phone Number
     */
    public static String currCustPhoneNum;
    /**
     * Current Order
     */
    public static Order currOrder;
    /**
     * Store Orders
     */
    public static StoreOrders storeOrders;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="current_oder_button"
    private Button current_oder_button; // Value injected by FXMLLoader

    @FXML // fx:id="customer_phone_number"
    private TextField customer_phone_number; // Value injected by FXMLLoader

    @FXML // fx:id="customer_phone_number_label"
    private Label customer_phone_number_label; // Value injected by FXMLLoader

    @FXML // fx:id="deluxe_pizza_button"
    private Button deluxe_pizza_button; // Value injected by FXMLLoader

    @FXML // fx:id="deluxe_pizza_image"
    private ImageView deluxe_pizza_image; // Value injected by FXMLLoader

    @FXML // fx:id="deluxe_pizza_label"
    private Label deluxe_pizza_label; // Value injected by FXMLLoader

    @FXML // fx:id="hawaiian_pizza_image"
    private ImageView hawaiian_pizza_image; // Value injected by FXMLLoader

    @FXML // fx:id="hawaiian_pizza_label"
    private Label hawaiian_pizza_label; // Value injected by FXMLLoader

    @FXML // fx:id="hawaiian_pizza_button"
    private Button hawaiian_pizza_button; // Value injected by FXMLLoader

    @FXML // fx:id="pepperoni_pizza_button"
    private Button pepperoni_pizza_button; // Value injected by FXMLLoader

    @FXML //fx:id="enter_number_button"
    private Button enterNumberButton; //Value injected by FXMLLoader

    @FXML // fx:id="pepperoni_pizza_image"
    private ImageView pepperoni_pizza_image; // Value injected by FXMLLoader

    @FXML // fx:id="pepperoni_pizza_label"
    private Label pepperoni_pizza_label; // Value injected by FXMLLoader

    @FXML // fx:id="pizzaCompanyLogo"
    private AnchorPane pizzaCompanyLogo; // Value injected by FXMLLoader

    @FXML // fx:id="pizza_company_logo"
    private ImageView pizza_company_logo; // Value injected by FXMLLoader

    @FXML // fx:id="store_orders_button"
    private Button store_orders_button; // Value injected by FXMLLoader

    /**
     * Initiating order from phone number
     *
     * @param event Action Event
     */
    @FXML
    void initiate_order_for_number(ActionEvent event) {
        currCustPhoneNum = customer_phone_number.getText();

        if (isNumeric(currCustPhoneNum))
            currOrder = new Order(currCustPhoneNum);
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert PopUp");
            alert.setHeaderText("Invalid Phone Number");
            alert.setContentText("Enter a correct 10 - digit Numeric Number only");
            alert.showAndWait();
        }
    }

    /**
     * check if a string is numeric
     *
     * @param str input
     * @return boolean
     */
    private boolean isNumeric(String str) {
        if (str == null)
            return false;
        if (str.length() != 10)
            return false;

        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checking current order
     *
     * @param event ActionEvent
     */
    @FXML
    void check_current_order(ActionEvent event) {
        if (!isNumeric(currCustPhoneNum))
            return;
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("views/current_order_view.fxml"));
            Scene scene = new Scene(root, 600, 690);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Current Order");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Managing the store orders
     *
     * @param event ActionEvent
     */
    @FXML
    void manage_store_orders(ActionEvent event) {

        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("views/store_orders_view.fxml"));
            Scene scene = new Scene(root, 550, 680);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Manage Store Orders");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Order Pepperoni Pizza
     *
     * @param event ActionEvent
     */
    @FXML
    void oder_pepperoni_pizza(ActionEvent event) {
        if (!isNumeric(currCustPhoneNum))
            return;
        try {
            pizzaType = "Pepperoni";
            displayNewWindow("file:src/resources/pepperoniPizzaImage.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * order Deluxe Pizza
     *
     * @param event actionEvent
     */
    @FXML
    void order_deluxe_pizza(ActionEvent event) {
        if (!isNumeric(currCustPhoneNum))
            return;
        try {
            pizzaType = "Deluxe";
            displayNewWindow("file:src/resources/deluxePizzaImage.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Order Hawaiian Pizza
     *
     * @param event ActionEvent
     */
    @FXML
    void order_hawaiian_pizza(ActionEvent event) {
        if (!isNumeric(currCustPhoneNum))
            return;
        try {
            pizzaType = "Hawaiian";
            displayNewWindow("file:src/resources/hawaiianPizzaImage.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to create a new window for custom order
     */
    private void displayNewWindow(String imagePath) throws IOException {
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("views/pizza_customization_view.fxml"));
        Scene scene = new Scene(root, 600, 600);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customize Your Pizza");
        Image image = new Image(imagePath);
        HBox hbox = (HBox) root.getChildren().get(0);
        ImageView imageview = (ImageView) hbox.getChildren().get(0);
        imageview.setImage(image);
        primaryStage.show();
    }

    /**
     * Initializing GUI elements
     */
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert current_oder_button != null : "fx:id=\"current_oder_button\" was not injected: check your FXML file 'main_view.fxml'.";
        assert customer_phone_number != null : "fx:id=\"curstomer_phone_number\" was not injected: check your FXML file 'main_view.fxml'.";
        assert customer_phone_number_label != null : "fx:id=\"customer_phone_number_label\" was not injected: check your FXML file 'main_view.fxml'.";
        assert deluxe_pizza_button != null : "fx:id=\"deluxe_pizza_button\" was not injected: check your FXML file 'main_view.fxml'.";
        assert deluxe_pizza_image != null : "fx:id=\"deluxe_pizza_image\" was not injected: check your FXML file 'main_view.fxml'.";
        assert deluxe_pizza_label != null : "fx:id=\"deluxe_pizza_label\" was not injected: check your FXML file 'main_view.fxml'.";
        assert hawaiian_pizza_image != null : "fx:id=\"hawaiian_pizza_image\" was not injected: check your FXML file 'main_view.fxml'.";
        assert hawaiian_pizza_label != null : "fx:id=\"hawaiian_pizza_label\" was not injected: check your FXML file 'main_view.fxml'.";
        assert hawaiian_pizza_button != null : "fx:id=\"hawaiian_pizza_button\" was not injected: check your FXML file 'main_view.fxml'.";
        assert pepperoni_pizza_button != null : "fx:id=\"pepperoni_pizza_button\" was not injected: check your FXML file 'main_view.fxml'.";
        assert pepperoni_pizza_image != null : "fx:id=\"pepperoni_pizza_image\" was not injected: check your FXML file 'main_view.fxml'.";
        assert pepperoni_pizza_label != null : "fx:id=\"pepperoni_pizza_label\" was not injected: check your FXML file 'main_view.fxml'.";
        assert pizzaCompanyLogo != null : "fx:id=\"pizzaCompanyLogo\" was not injected: check your FXML file 'main_view.fxml'.";
        assert pizza_company_logo != null : "fx:id=\"pizza_company_logo\" was not injected: check your FXML file 'main_view.fxml'.";
        assert store_orders_button != null : "fx:id=\"store_orders_button\" was not injected: check your FXML file 'main_view.fxml'.";
        assert enterNumberButton != null : "fx:id=\"enterNumberButton\" was not injected: check your FXML file 'main_view.fxml'.";
        storeOrders = new StoreOrders();
    }

}

