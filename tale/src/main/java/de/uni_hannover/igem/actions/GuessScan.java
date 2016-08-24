package de.uni_hannover.igem.actions;

import java.util.ArrayList;
import java.util.List;

import de.uni_hannover.igem.util.Misc;
import de.uni_hannover.igem.util.ScanResult;
import de.uni_hannover.igem.util.TALRater;

public class GuessScan {

	/**
	 * Scans a given DNA-sequence for best possible TALEs
	 * 
	 * @param sequence
	 *            - the dna-sequence
	 * @return a list of rated ScanResults
	 */
	public static List<ScanResult> getResults(String sequence) {
		List<ScanResult> resultList = new ArrayList<ScanResult>();
		for (int i = 0; i < sequence.length(); i++) {

			for (int j = sequence.length(); j >= 0; j--) {
				String possibleSequence = sequence.substring(i, j);
				String taleSequence = Misc.getTaleSequence2DNASequence(possibleSequence);
				double rating = TALRater.getRating(taleSequence);
				ScanResult result = new ScanResult(taleSequence, i);
				result.setRating(rating);
				resultList.add(result);
			}
		}

		return resultList;
	}
}
