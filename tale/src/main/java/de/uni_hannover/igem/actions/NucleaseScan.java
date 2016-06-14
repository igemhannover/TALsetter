package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.Constants;
import de.uni_hannover.igem.util.ScanResult;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NucleaseScan {
	/**
	 * Returns pairs of ScanResults, sorted by their rating.
	 *
	 * Each pair of ScanResult objects corresponds to two TALe nucleases which
	 * attach at the given DNA sequence at a distance that is optimal for
	 * cutting by the nuclease domains.
	 */
	public static List<List<ScanResult>> scanSequence(String sequence) {
		Set<List<ScanResult>> results = new HashSet<List<ScanResult>>();
		List<List<ScanResult>> sortedResults;

		for (int i = 0; i < sequence.length(); i++) {
			results.addAll(getPossibleTALes(sequence, i));
		}

		Comparator<List<ScanResult>> comp = new Comparator<List<ScanResult>>() {
			public int compare(List<ScanResult> a, List<ScanResult> b) {
				Double ratingA = a.get(0).getRating() + a.get(1).getRating();
				Double ratingB = b.get(0).getRating() + b.get(1).getRating();
				return ratingA.compareTo(ratingB);
			}
		};

		sortedResults = new ArrayList<List<ScanResult>>(results);
		Collections.sort(sortedResults, comp);

		return sortedResults;
	}

	public static List<List<ScanResult>> getPossibleTALes(String sequence, Integer offset) {
		List<List<ScanResult>> TALes = new ArrayList<List<ScanResult>>();
		List<ScanResult> entry;

		int TALLengthMax = Constants.getMaxTALLength();
		int TALLengthMin = Constants.getMinTALLength();
		int NucleaseDistanceMin = Constants.getMinNucleaseDistance();
		int NucleaseDistanceMax = Constants.getMaxNucleaseDistance();
		int secondTALStart;

		entry = new ArrayList<ScanResult>();
		for (int firstTALLength = TALLengthMin; firstTALLength <= TALLengthMax; firstTALLength++) {
			for (int secondTALLength = TALLengthMin; secondTALLength <= TALLengthMax; secondTALLength++) {
				for (int distance = NucleaseDistanceMin; distance <= NucleaseDistanceMax; distance++) {
					secondTALStart = offset + firstTALLength + distance;
					if (secondTALStart + secondTALLength > sequence.length())
						continue; /* TALes extend beyond DNA sequence */
					entry = new ArrayList<ScanResult>();
					entry.add(new ScanResult(sequence.substring(offset, offset + firstTALLength), offset));
					entry.add(new ScanResult(sequence.substring(secondTALStart, secondTALStart + secondTALLength),
							secondTALStart));
					TALes.add(entry);
				}
			}
		}

		return TALes;
	}
}
