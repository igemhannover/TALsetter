package de.uni_hannover.igem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view/MainWindowView.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("resources/styles.css").toExternalForm());

		stage.setTitle("TALsetter");
		stage.setScene(scene);
		Image logo = new Image(getClass().getResourceAsStream("resources/images/hannover_logo.png"));
		stage.getIcons().add(logo);
		stage.show();
	}

}