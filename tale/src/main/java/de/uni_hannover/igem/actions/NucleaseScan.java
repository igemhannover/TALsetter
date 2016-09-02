package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.Constants;
import de.uni_hannover.igem.util.ScanResult;
import de.uni_hannover.igem.util.Misc;

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
	public static List<ScanResultPair> scanSequence(String sequence) {
		Set<ScanResultPair> results = new HashSet<ScanResultPair>();
		List<ScanResultPair> sortedResults;

		for (int i = 0; i < sequence.length(); i++) {
			results.addAll(getPossibleTALes(sequence, i));
		}

		Comparator<ScanResultPair> comp = new Comparator<ScanResultPair>() {
			public int compare(ScanResultPair a, ScanResultPair b) {
				Double ratingA = a.getResult1().getRating() + a.getResult2().getRating();
				Double ratingB = b.getResult1().getRating() + b.getResult2().getRating();
				return ratingA.compareTo(ratingB);
			}
		};

		sortedResults = new ArrayList<ScanResultPair>(results);
		Collections.sort(sortedResults, comp);

		return sortedResults;
	}

	public static List<ScanResultPair> getPossibleTALes(String sequence, Integer offset) {
		List<ScanResultPair> TALes = new ArrayList<ScanResultPair>();
		ScanResultPair entry;

		int TALLengthMax = Constants.getMaxTALLength();
		int TALLengthMin = Constants.getMinTALLength();
		int NucleaseDistanceMin = Constants.getMinNucleaseDistance();
		int NucleaseDistanceMax = Constants.getMaxNucleaseDistance();
		int secondTALStart;
		String seq1, seq2, seq1complement, seq2complement;

		for (int firstTALLength = TALLengthMin; firstTALLength <= TALLengthMax; firstTALLength++) {
			for (int secondTALLength = TALLengthMin; secondTALLength <= TALLengthMax; secondTALLength++) {
				for (int distance = NucleaseDistanceMin; distance <= NucleaseDistanceMax; distance++) {
					secondTALStart = offset + firstTALLength + distance;
					if (secondTALStart + secondTALLength > sequence.length())
						continue; /* TALes extend beyond DNA sequence */
					seq1 = sequence.substring(offset, offset + firstTALLength);
					seq2 = sequence.substring(secondTALStart, secondTALStart + secondTALLength);

					/*
					 * One of the TALEN sequences needs to be inverted, because
					 * they bind on opposite strands on the double helix. This
					 * results in 2 possible solutions per set of TAL positions.
					 */
					try {
						entry = entry = new ScanResultPair(new ScanResult(Misc.getCounterSequence(seq1), offset),
								new ScanResult(seq2, secondTALStart));
						TALes.add(entry);
					} catch (Exception e) {
					}
					;
					try {
						entry = new ScanResultPair(new ScanResult(seq1, offset),
								new ScanResult(Misc.getCounterSequence(seq2), secondTALStart));
						TALes.add(entry);
					} catch (Exception e) {
					}
					;
				}
			}
		}

		return TALes;
	}
}
