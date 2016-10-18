package de.uni_hannover.igem.actions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.uni_hannover.igem.model.Base2Tale;
import de.uni_hannover.igem.model.TaleToCSV;

import de.uni_hannover.igem.util.Constants;
import de.uni_hannover.igem.util.Misc;
import de.uni_hannover.igem.util.ScanResult;

public class TaleToCSVTest {
	public static String sequence = "tatagcgc";

	public void initializeNucleaseScanTest() {

		/*
		 * define small variation ranges, so we have to specify less expected
		 * values later on
		 */
		Constants.setMinTALLength(2);
		Constants.setMaxTALLength(3);
		Constants.setMinNucleaseDistance(0);
		Constants.setMaxNucleaseDistance(1);
	}

	@Test
	public void testConversion() throws Exception {
		ArrayList<ArrayList<String>> expectedResult = new ArrayList<ArrayList<String>>();
		expectedResult.add(new ArrayList<String>(Arrays.asList(
				          "target", "Name", "Enzymes", "H2O", "LA", "AB", "BC", "CD", "LR", "AR", "BR", "CR", "DR", "NI1", "NI2", "NI3", "NI4", 
		                  "NI5", "NI6", "HD1", "HD2", "HD3", "HD4", "HD5", "HD6", "NN1", "NN2", "NN3", "NN4", "NN5", "NN6", "NG1", "NG2", "NG3", "NG4", "NG5", "NG6", "NH1", 
		                  "NH2", "NH3", "NH4", "NH5", "NH6", "sNI1", "sNI2", "sNI3", "sNI4", "sNI5", "sHD1", "sHD2", "sHD3", "sHD4", "sHD5", "sNN1", "sNN2",
		                  "sNN3", "sNN4", "sNN5", "sNG1", "sNG2", "sNG3", "sNG4", "sNG5", "sNH1", "sNH2", "sNH3", "sNH4", "sNH5")));
		expectedResult.add(new ArrayList<String>(Arrays.asList(
				"11.A1", "Somename", "13", "0", /* target, name, enzymes, h2o */
				"1", "0", "0", "0", "0", "0", "0", "0", "0", /* termini */
				"0", "1", "0", "0", "1", "0", /* NI */
				"0", "0", "0", "0", "0", "1", /* HD */
				"1", "0", "0", "0", "0", "0", /* NN */
				"0", "0", "1", "1", "0", "0", /* NG */
				"0", "0", "0", "0", "0", "0", /* NH */
				"0", "0", "0", "0", "0", /* sNI */
				"0", "0", "0", "0", "0", /* sHD */
				"0", "0", "0", "0", "0", /* sNN */
				"0", "0", "0", "0", "0", /* sNG */
				"0", "0", "0", "0", "0"  /* sNH */
		)));
		expectedResult.add(new ArrayList<String>(Arrays.asList(
				"11.A2", "Somename", "13", "5", /* target, name, enzymes, h2o */
				"0", "0", "0", "0", "0", "1", "0", "0", "0", /* termini */
				"0", "0", "0", "0", "0", "0", /* NI */
				"0", "0", "0", "0", "0", "0", /* HD */
				"0", "0", "0", "0", "0", "0", /* NN */
				"0", "0", "0", "0", "0", "0", /* NG */
				"0", "0", "0", "0", "0", "0", /* NH */
				"1", "0", "0", "0", "0", /* sNI */
				"0", "0", "0", "0", "0", /* sHD */
				"0", "0", "0", "0", "0", /* sNN */
				"0", "0", "0", "0", "0", /* sNG */
				"0", "0", "0", "0", "0"  /* sNH */
		)));

		ArrayList<ArrayList<String>> obtainedResult = TaleToCSV.makeTable(String.join("", Base2Tale.nucleotides2rvds("gattaca")));
		TaleToCSV.table2csv(obtainedResult, "/tmp/out_produced.csv");
		TaleToCSV.table2csv(expectedResult, "/tmp/out_expected.csv");
		assertEquals(obtainedResult, expectedResult);
	}
}
