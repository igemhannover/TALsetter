/**
 * Sample Skeleton for "ScanView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/
package de.uni_hannover.igem.control;

import de.uni_hannover.igem.actions.ExactScan;
import de.uni_hannover.igem.model.Actions;
import de.uni_hannover.igem.util.ScanResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ScanViewController {

	@FXML // fx:id="actionResultView"
	private TableView<ScanResult> actionResultView;

	private ObservableList<ScanResult> resultList;

	@FXML // fx:id="basesLbl"
	private Label basesLbl;

	@FXML // fx:id="basesTxt"
	private Text basesTxt;

	@FXML // fx:id="scanHeaderLbl"
	private Label scanHeaderLbl;

	@FXML // fx:id="seqCol"
	private TableColumn<ScanResult, String> seqCol;

	@FXML // fx:id="ratingCol"
	private TableColumn<ScanResult, Double> ratingCol;

	@FXML // fx:id="posCol"
	private TableColumn<ScanResult, Integer> posCol;

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		seqCol.setCellValueFactory(new PropertyValueFactory<ScanResult, String>("sequence"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<ScanResult, Double>("rating"));
		posCol.setCellValueFactory(new PropertyValueFactory<ScanResult, Integer>("position"));
	}

	/**
	 * initialize the result view
	 * @param action ExactScan as default 
	 * NUCLEASE_SCAN has own controller
	 * (TODO use own controller for NUCLEASE_SCAN)
	 * @param sequence which will be used for the action
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

}
