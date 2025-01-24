/**
 * model package contains all the classes used to add functinalities to the gui
 */
package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This is the Main java class where, upon running this we can run the application.
 *
 * @author 
 */
public class Main extends Application
{
	/**
	 * Constructor
	 */
	public Main()
	{

	}

	/**
	 * This method helps us start and get resources from FXML file
	 *
	 * @param primaryStage Primary Stage for the UI
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("view/MainView.fxml"));
			Scene scene = new Scene(root,680,733);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Student Roster Management System (SRMS)");
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Java main method to launch the GUI program
	 *
	 * @param args System arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
