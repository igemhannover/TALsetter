package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.Misc;
import de.uni_hannover.igem.util.ScanResult;

public class ExactScan {

	public static ScanResult getResult(String sequence) {

		String taleSeuqnce = Misc.getTaleSequence2DNASequence(sequence);

		ScanResult result = new ScanResult(taleSeuqnce, 0);

		return result;
	}
}
