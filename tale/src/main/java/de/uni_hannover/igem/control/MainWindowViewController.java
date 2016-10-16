package de.uni_hannover.igem.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class MainWindowViewController {

    @FXML // fx:id="aboutMenuItem"
    private MenuItem aboutMenuItem;

    @FXML // fx:id="contentPane"
    private Pane contentPane;

    @FXML // fx:id="mainStage"
    private AnchorPane mainStage;

    @FXML
    void initialize() {
    }

    // Handler for MenuItem[fx:id="aboutMenuItem"] onAction
    @FXML
    void openAbout(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("About");
    	alert.setHeaderText("iGEM Team Hannover");
    	alert.setContentText("Institut für Pflanzengenetik \n"+
    			"Herrenhäuser Straße 2 \n"+
    			"30419 Hannover \n"+
    			"Germany \n\n"+
    			"info@igem.uni-hannover.de");
    	alert.show();    	
    }

}
