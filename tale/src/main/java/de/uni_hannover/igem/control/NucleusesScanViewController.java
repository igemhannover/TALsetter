/**
 * Sample Skeleton for "ScanView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/
package de.uni_hannover.igem.control;

import de.uni_hannover.igem.actions.NucleaseScan;
import de.uni_hannover.igem.actions.ScanResultPair;
import de.uni_hannover.igem.model.Actions;
import de.uni_hannover.igem.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class NucleusesScanViewController {

	@FXML // fx:id="actionResultView"
	private TableView<ScanResultPair> actionResultView;

	private ObservableList<ScanResultPair> resultList;

	@FXML // fx:id="basesLbl"
	private Label basesLbl; 

	@FXML // fx:id="basesTxt"
	private Text basesTxt; 

	@FXML // fx:id="scanHeaderLbl"
	private Label scanHeaderLbl; 

	@FXML // fx:id="seq1Col"
	private TableColumn<ScanResultPair, String> seq1Col; 

	@FXML // fx:id="seq2Col"
	private TableColumn<ScanResultPair, String> seq2Col;

	@FXML // fx:id="ratingCol"
	private TableColumn<ScanResultPair, Double> ratingCol;

	@FXML // fx:id="posCol"
	private TableColumn<ScanResultPair, Integer> posCol;

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		seq1Col.setCellValueFactory(new PropertyValueFactory<ScanResultPair, String>("sequence1"));
		seq2Col.setCellValueFactory(new PropertyValueFactory<ScanResultPair, String>("sequence2"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<ScanResultPair, Double>("rating"));
		posCol.setCellValueFactory(new PropertyValueFactory<ScanResultPair, Integer>("position"));
	}

	public void initData(Actions action, String sequence) {
		this.scanHeaderLbl.setText(action.name());
		this.basesTxt.setText(sequence);

		Constants.initialize();
		resultList = FXCollections.observableArrayList(NucleaseScan.scanSequence(sequence));
		actionResultView.setItems(resultList);
	}

}
