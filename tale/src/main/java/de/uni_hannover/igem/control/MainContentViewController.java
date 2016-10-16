package de.uni_hannover.igem.control;

import java.io.IOException;
import java.util.ArrayList;

import de.uni_hannover.igem.model.Actions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainContentViewController {

	@FXML // fx:id="actionCbx"
	private ComboBox<String> actionCbx;
	@FXML // fx:id="basesTxf"
	private TextField basesTxf;

	@FXML // fx:id="contentResultSplitPane"
	private SplitPane contentResultSplitPane; // Value injected by FXMLLoader

	@FXML // fx:id="nextBtn"
	private Button nextBtn;

	private ObservableList<String> observableActionList = FXCollections.observableList(new ArrayList<String>());

	@FXML // fx:id="resultViewPane"
	private AnchorPane resultViewPane;

	@FXML // fx:id="resultViewTabPane"
	private TabPane resultViewTabPane;

	@FXML // fx:id="selectActionPane"
	private GridPane selectActionPane;
	
    @FXML // fx:id="targetOrganismHuman"
    private RadioButton targetOrganismHuman;

    @FXML // fx:id="targetOrganismInvitro"
    private RadioButton targetOrganismInvitro;

    @FXML // fx:id="targetOrganismPlant"
    private RadioButton targetOrganismPlant;
    
    final ToggleGroup targetOrganismGroup = new ToggleGroup();

	private SingleSelectionModel<Tab> selectionModel;

	/**
	 * style TextField red and show warning/error
	 * 
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

	/**
	 * Default ExactScan
	 * 
	 * @return selected Action of ComboBox actionCbx
	 */
	private Actions getSelectedAction() {
		if (actionCbx.getSelectionModel().getSelectedItem().equals(Actions.NUCLEUS_SCAN.toString())) {
			return Actions.NUCLEUS_SCAN;
		}
		return Actions.EXACT_SCAN; // DEFAULT
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
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

		targetOrganismHuman.setToggleGroup(targetOrganismGroup);
		targetOrganismInvitro.setToggleGroup(targetOrganismGroup);
		targetOrganismPlant.setToggleGroup(targetOrganismGroup);
		
		//TODO function of target organisms
		targetOrganismHuman.setDisable(true);
		targetOrganismInvitro.setDisable(true);
		targetOrganismPlant.setDisable(true);

		selectionModel = resultViewTabPane.getSelectionModel();
	}

	/**
	 * The default is a ScanView with the ExactScan.
	 * 
	 * @return Scene to the selected Action
	 */
	private AnchorPane loadSelectedAction() {
		AnchorPane result = new AnchorPane();
		FXMLLoader loader;
		switch (getSelectedAction()) {
		case NUCLEUS_SCAN:
			loader = new FXMLLoader(getClass().getResource("/de/uni_hannover/igem/view/NucleusesScanView.fxml"));
			try {
				AnchorPane nucleaseScan = (AnchorPane) loader.load();
				NucleusesScanViewController nucleaseScanController = loader
						.<NucleusesScanViewController> getController();
				nucleaseScanController.initData(getSelectedAction(), basesTxf.getText());
				return nucleaseScan;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			loader = new FXMLLoader(getClass().getResource("/de/uni_hannover/igem/view/ScanView.fxml"));
			try {
				AnchorPane scan = (AnchorPane) loader.load();
				ScanViewController scanController = loader.<ScanViewController> getController();
				scanController.initData(getSelectedAction(), basesTxf.getText());
				return scan;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}

		return result;
	}

	// Handler for Button[fx:id="nextBtn"] onAction
	@FXML
	void startAction(ActionEvent event) {
		if (checkInputValide()) {
			Tab resultTab = new Tab(actionCbx.getSelectionModel().getSelectedItem());
			resultViewTabPane.getTabs().add(resultTab);
			resultTab.setContent(loadSelectedAction());
			selectionModel.select(resultTab);
		}
	}

}
