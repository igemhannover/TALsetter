package de.uni_hannover.igem.control;

import java.io.IOException;
import java.util.ArrayList;

import de.uni_hannover.igem.model.Actions;
import de.uni_hannover.igem.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainContentViewController {

	@FXML // fx:id="actionCbx"
	private ComboBox<String> actionCbx;

	@FXML // fx:id="basesTxf"
	private TextField basesTxf;

	@FXML // fx:id="nextBtn"
	private Button nextBtn;

	private ObservableList<String> observableActionList = FXCollections.observableList(new ArrayList<String>());

	// Handler for Button[fx:id="nextBtn"] onAction
	@FXML
	void startAction(ActionEvent event) {
		if (checkInputValide()) {
			Stage stage = new Stage();
			stage.setTitle(actionCbx.getSelectionModel().getSelectedItem());
			stage.setScene(loadSelectedAction());
			stage.show();
		}
	}

	/**
	 * The default is a ScanView with the ExactScan.
	 * @return Scene to the selected Action
	 */
	private Scene loadSelectedAction() {
		Scene result = new Scene(new AnchorPane());
		FXMLLoader loader;
		switch (getSelectedAction()) {
		case NUCLEUS_SCAN:
			loader = new FXMLLoader(getClass().getResource("/de/uni_hannover/igem/view/NucleusesScanView.fxml"));
			try {
				Parent p = (Parent) loader.load();
				NucleusesScanViewController nucleaseScanController = loader.<NucleusesScanViewController> getController();
				nucleaseScanController.initData(getSelectedAction(), basesTxf.getText());
				return new Scene(p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			loader = new FXMLLoader(getClass().getResource("/de/uni_hannover/igem/view/ScanView.fxml"));
			try {
				Parent p = (Parent) loader.load();
				ScanViewController scanController = loader.<ScanViewController> getController();
				scanController.initData(getSelectedAction(), basesTxf.getText());
				return new Scene(p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}

		return result;
	}

	/**
	 * Default ExactScan
	 * @return selected Action of ComboBox actionCbx
	 */
	private Actions getSelectedAction() {
		if (actionCbx.getSelectionModel().getSelectedItem().equals(Actions.NUCLEUS_SCAN.toString())) {
			return Actions.NUCLEUS_SCAN;
		}
		return Actions.EXACT_SCAN; // DEFAULT
	}

	/**
	 * style TextField red and show warning/error
	 * @return true if input length >= 20 and an action selected
	 */
	private boolean checkInputValide() {
		if (basesTxf.getText().length() < 20) {
			basesTxf.getStyleClass().add("invalid");
			return false;
		}
		if (actionCbx.getSelectionModel().getSelectedItem() == null) {
			actionCbx.getStyleClass().add("invalid");
			return false;
		}
		// TODO more checks?
		return true;
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		Constants.initialize();
		for (Actions action : Actions.values()) {
			observableActionList.add(action.toString());
		}
		actionCbx.setItems(observableActionList);

		basesTxf.setOnKeyTyped(new EventHandler<Event>() {
			public void handle(Event arg0) {
				if (basesTxf.getText().length() >= 20) {
					basesTxf.getStyleClass().remove("invalid");
				}
			}
		});
	}

}
