package de.uni_hannover.igem.actions;

import java.util.ArrayList;
import java.util.List;

import de.uni_hannover.igem.util.Misc;
import de.uni_hannover.igem.util.ScanResult;

public class ExactScan {

	public static List<ScanResult> getResults(String sequence) {
		List<ScanResult> resultList = new ArrayList<ScanResult>();

		String taleSeuqnce = Misc.getTaleSequence2DNASequence(sequence);

		ScanResult result = new ScanResult(taleSeuqnce, 0);
		resultList.add(result);

		return resultList;
	}
}
