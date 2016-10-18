/**
 * Sample Skeleton for "ScanView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/
package de.uni_hannover.igem.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import de.uni_hannover.igem.actions.NucleaseScan;
import de.uni_hannover.igem.actions.ScanResultPair;
import de.uni_hannover.igem.model.Actions;
import de.uni_hannover.igem.model.Base2Tale;
import de.uni_hannover.igem.model.TaleToCSV;
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

public class NucleaseScanViewController {

	@FXML // fx:id="actionResultView"
	private TableView<ScanResultPair> actionResultView;

	@FXML // fx:id="basesLbl"
	private Label basesLbl;

	@FXML // fx:id="basesTxt"
	private TextArea basesTxt;

	@FXML // fx:id="exportButton"
	private Button exportButton;

	@FXML // fx:id="posCol"
	private TableColumn<ScanResultPair, Integer> posCol;

	@FXML // fx:id="ratingCol"
	private TableColumn<ScanResultPair, Double> ratingCol;

	private ObservableList<ScanResultPair> resultList;

	@FXML // fx:id="scanHeaderLbl"
	private Label scanHeaderLbl;

	@FXML // fx:id="seq1Col"
	private TableColumn<ScanResultPair, String> seq1Col;

	@FXML // fx:id="seq2Col"
	private TableColumn<ScanResultPair, String> seq2Col;

	// Handler for Button[fx:id="exportButton"] onAction
	@FXML
	void export(ActionEvent event) {
		ScanResultPair exportSelection = actionResultView.getSelectionModel().getSelectedItem();
		String filename_pipetting_instructions1 = "TALEN_First.csv";
		String filename_pipetting_instructions2 = "TALEN_Second.csv";
		String filename_sequence1 = "TALEN_First.txt";
		String filename_sequence2 = "TALEN_Second.txt";
				
		ArrayList<ArrayList<String>> csv_table;
		String sequence;

		/* export the pipetting instructions */
		try {
			sequence = String.join("", Base2Tale.nucleotides2rvds(exportSelection.getSequence1()));
			csv_table = TaleToCSV.makeTable(sequence);
			TaleToCSV.table2csv(csv_table, filename_pipetting_instructions1);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		try {
			sequence = String.join("", Base2Tale.nucleotides2rvds(exportSelection.getSequence2()));
			csv_table = TaleToCSV.makeTable(sequence);
			TaleToCSV.table2csv(csv_table, filename_pipetting_instructions2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* export the TALEN sequences */
		try {
			PrintWriter pw = new PrintWriter(new File(filename_sequence1));
			pw.write(String.join("-", Base2Tale.nucleotides2rvds(exportSelection.getSequence1())));
	        pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		try {
			PrintWriter pw = new PrintWriter(new File(filename_sequence2));
			pw.write(String.join("-", Base2Tale.nucleotides2rvds(exportSelection.getSequence2())));
	        pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}


	}

	public void initData(Actions action, String sequence) {
		this.scanHeaderLbl.setText(action.name());
		this.basesTxt.setText(sequence);

		resultList = FXCollections.observableArrayList(NucleaseScan.scanSequence(sequence));
		actionResultView.setItems(resultList);
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		seq1Col.setCellValueFactory(new PropertyValueFactory<ScanResultPair, String>("sequence1"));
		seq2Col.setCellValueFactory(new PropertyValueFactory<ScanResultPair, String>("sequence2"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<ScanResultPair, Double>("rating"));
		posCol.setCellValueFactory(new PropertyValueFactory<ScanResultPair, Integer>("position"));

		seq1Col.prefWidthProperty().bind(actionResultView.widthProperty().divide(4));
		seq2Col.prefWidthProperty().bind(actionResultView.widthProperty().divide(4));
		ratingCol.prefWidthProperty().bind(actionResultView.widthProperty().divide(4));
		posCol.prefWidthProperty().bind(actionResultView.widthProperty().divide(4));

		exportButton.disableProperty().bind(Bindings.isEmpty(actionResultView.getSelectionModel().getSelectedItems()));
		actionResultView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
