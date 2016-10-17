/**
 * Sample Skeleton for "ScanView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/
package de.uni_hannover.igem.control;

import de.uni_hannover.igem.actions.ExactScan;
import de.uni_hannover.igem.model.Actions;
import de.uni_hannover.igem.util.ScanResult;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScanViewController {

	@FXML // fx:id="actionResultView"
	private TableView<ScanResult> actionResultView;

	@FXML // fx:id="basesLbl"
	private Label basesLbl;

	@FXML // fx:id="basesTxt"
	private TextArea basesTxt;

	@FXML // fx:id="exportButton"
	private Button exportButton;

	@FXML // fx:id="posCol"
	private TableColumn<ScanResult, Integer> posCol;

	@FXML // fx:id="ratingCol"
	private TableColumn<ScanResult, Double> ratingCol;

	private ObservableList<ScanResult> resultList;

	@FXML // fx:id="scanHeaderLbl"
	private Label scanHeaderLbl;

	@FXML // fx:id="seqCol"
	private TableColumn<ScanResult, String> seqCol;

	// Handler for Button[fx:id="exportButton"] onAction
	@FXML
	void export(ActionEvent event) {
		ScanResult exportSelection = actionResultView.getSelectionModel().getSelectedItem();
		// TODO handle the event here
	}

	/**
	 * initialize the result view
	 * 
	 * @param action
	 *            ExactScan as default NUCLEASE_SCAN has own controller (TODO
	 *            use own controller for NUCLEASE_SCAN)
	 * @param sequence
	 *            which will be used for the action
	 */
	public void initData(Actions action, String sequence) {
		this.scanHeaderLbl.setText(action.name());
		this.basesTxt.setText(sequence);

		switch (action) {
		case EXACT_SCAN:
			resultList = FXCollections.observableArrayList(ExactScan.getResults(sequence));
			break;
		case NUCLEUS_SCAN:
			System.out.println("NUCLEUS_SCAN has own controller");
			break;
		default:
			resultList = FXCollections.observableArrayList(ExactScan.getResults(sequence));
			break;
		}
		actionResultView.setItems(resultList);
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		seqCol.setCellValueFactory(new PropertyValueFactory<ScanResult, String>("sequence"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<ScanResult, Double>("rating"));
		posCol.setCellValueFactory(new PropertyValueFactory<ScanResult, Integer>("position"));

		seqCol.prefWidthProperty().bind(actionResultView.widthProperty().divide(3));
		ratingCol.prefWidthProperty().bind(actionResultView.widthProperty().divide(3));
		posCol.prefWidthProperty().bind(actionResultView.widthProperty().divide(3));

		exportButton.disableProperty().bind(Bindings.isEmpty(actionResultView.getSelectionModel().getSelectedItems()));
	}

}
