package transaction;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Main class sets the dimensions of the BorderPane and the characteristics.
 * Main class is the running class of the project.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class Main extends Application {

	/**
	 * Start Method starts the GUI, and sets it's characteristics.
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("gui.fxml"));
			Scene scene = new Scene(root, 620, 620);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Transaction Manager");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Main method launches the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
