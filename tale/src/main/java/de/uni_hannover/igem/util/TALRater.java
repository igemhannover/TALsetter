package de.uni_hannover.igem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import de.jstacs.data.AlphabetContainer;
import de.jstacs.data.sequences.Sequence;
import de.jstacs.io.FileManager;
import de.jstacs.io.XMLParser;
import de.uni_hannover.igem.model.Base;
import de.uni_hannover.igem.model.BindingStrength;
import projects.talen.ScanForTALENCLI;
import projects.tals.TALgetterDiffSM;

public class TALRater {

	private final static Logger logger = Logger.getLogger(TALRater.class);

	/**
	 * This method provides a rating based on the length of a TAL and the
	 * binding-strength. The length is weighted with 1, the binding-strength
	 * with 2 and the offsite-targets with 3.
	 * 
	 * @param taleSequence
	 *            the TAL which should be rated
	 * @return a rating between 0 (not good) and 1 (best)
	 */
	public static double getRating(String taleSequence) {
		double overallRating = 0.0;

		double lengthRating = getLengthRating(taleSequence);

		double weakBindingRating = getWeakBindingRating(taleSequence);

		double strongBindingRating = getStrongBindingRating(taleSequence);
		// binding-strength is more important than length
		// TODO implement function to rate the tal regarding strong bindings
		// (see phabricator)

		overallRating = (lengthRating + (weakBindingRating * 2) + (strongBindingRating * 2)) / 5;

		return overallRating;

	}

	private static double getLengthRating(String sequence) {
		String[] taleSequence = sequence.split("(?<=\\G..)");
		if (taleSequence.length < Constants.getMinTALLength() || taleSequence.length > Constants.getMaxTALLength()) {
			return 0;
		}

		else {
			int optiDist = Math.abs(taleSequence.length - Constants.getOptimalTALLength());

			return 1 - (optiDist / taleSequence.length);

		}
	}

	private static double getStrongBindingRating(String sequence) {
		String originSequence = Misc.getSequence2Tale(sequence);
		double penalty = 0.0;
		int optimalCount = Constants.getOptimalStrongBinding();

		int originSequencePartLength = originSequence.length() / optimalCount;

		String[] originSequenceParts = originSequence.split("(?<=\\G.{" + originSequencePartLength + "})");
		List<List<Integer>> bindingPos = new ArrayList<List<Integer>>();
		for (String originSequencePart : originSequenceParts) {
			List<Integer> bindings = getStrongBindingPos(originSequencePart);
			if (!bindings.isEmpty()) {
				bindingPos.add(getStrongBindingPos(originSequencePart));
			}
		}

		// if length not equals there have to be at least one part without
		// strong binding
		if (bindingPos.size() == originSequenceParts.length) {
			List<Integer> referencePos = bindingPos.get(0);

			for (int i = 0; i < referencePos.size(); i++) {
				int searchingPos = referencePos.get(i);

				for (int j = 1; j < bindingPos.size(); j++) {
					List<Integer> counterPos = bindingPos.get(j);

					// if contains, no penalty, if not, try to find in near
					// distance
					if (!counterPos.contains(searchingPos)) {
						boolean found = false;
						for (int k = 1; k < optimalCount; k++) {
							if (counterPos.contains(k)) {
								penalty += k;
								found = true;
							}
						}
						// nothing found in near distance, so to bad to get
						// points
						if (!found) {
							return 0.0;
						}
					}

				}
			}

		}

		else {
			return 0.0;
		}

		return 1 - penalty / sequence.length();

	}

	private static List<Integer> getStrongBindingPos(String sequence) {

		List<Integer> bindingPos = new ArrayList<Integer>();

		for (int i = 0; i < sequence.length(); i++) {
			char c = sequence.charAt(i);
			String cString = String.valueOf(c);
			Base b = Base.valueOf(cString);

			if (b.getStrength() == BindingStrength.STRONG) {
				bindingPos.add(i);
			}

		}

		return bindingPos;

	}

	private static double getWeakBindingRating(String sequence) {
		List<Base> baseList = Misc.getSequenceList2Tale(sequence);
		TreeMap<Integer, Integer> weakRowLength2Counter = new TreeMap<Integer, Integer>();
		double overallRating = 0;
		int weakBindingRow = 0;
		for (Base currBase : baseList) {

			if (currBase.getStrength() == BindingStrength.WEAK) {
				weakBindingRow++;
			}

			else if (currBase.getStrength() == BindingStrength.STRONG) {
				if (weakRowLength2Counter.get(weakBindingRow) == null) {
					weakRowLength2Counter.put(new Integer(weakBindingRow), 1);
				}

				else {
					int oldValue = weakRowLength2Counter.get(weakBindingRow);
					weakRowLength2Counter.put(new Integer(weakBindingRow), oldValue + 1);
				}

				weakBindingRow = 0;
			}

		}

		int minWeakBindingRow = Constants.getMinWeakBinding();
		int maxWeakBindingRow = Constants.getMaxWeakBinding();
		int smallestRow = weakRowLength2Counter.isEmpty() ? 0 : weakRowLength2Counter.firstKey();
		int highestRow = weakRowLength2Counter.isEmpty() ? 0 : weakRowLength2Counter.lastKey();

		if (smallestRow >= minWeakBindingRow && highestRow <= maxWeakBindingRow) {
			for (Integer weakRowLength : weakRowLength2Counter.keySet()) {
				Integer currRowCount = weakRowLength2Counter.get(weakRowLength);

				if (weakRowLength == minWeakBindingRow) {
					if (currRowCount > 1) {
						overallRating += weakRowLength * currRowCount;
					}
				}

				else {
					overallRating += weakRowLength * currRowCount;
				}

			}

		} else {
			return 0;
		}

		return 1 - (overallRating / baseList.size());
	}

	/**
	 * Rates a tal depending on its off-site-targets. TODO implement with
	 * FASTA-Sequence (see TALENOffer)
	 * 
	 * @param sequence
	 *            - the TAL which should be rated
	 * @return the best possible value from TALENOffer
	 */
	public static double getOffsetRating(String sequence) {
		try {
			TALgetterDiffSM model = (TALgetterDiffSM) XMLParser
					.extractObjectForTags(FileManager.readInputStream(ScanForTALENCLI.class.getClassLoader()
							.getResourceAsStream("projects/tals/talfinder_obg2_hyp_bg.xml")), "model");
			model.fix();
			AlphabetContainer alphabetsRVD = model.getRVDAlphabet();
			Sequence tal = Sequence.create(alphabetsRVD, formatToJstacsFormat(sequence), "-");
			return model.getBestPossibleScore(tal, null);
		} catch (Exception e) {
			logger.error("Konnte Wertung für Offsites nicht berechnen.", e);
		}

		return 0.0;

	}

	// TODO maybe move to util if needed more than once?
	private static String formatToJstacsFormat(String input) {
		String[] talSequence = input.split("(?<=\\G..)");

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < talSequence.length; i++) {
			String tal = talSequence[i];
			sb.append(tal);

			if (i < talSequence.length - 1) {
				sb.append("-");
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
