package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.Constants;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class NucleaseScan {
	public static Map<Integer, Integer> scanSequence(String sequence) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		for (int i = 0; i < sequence.length(); i++) {
			for (List<String> TALes : getPossibleTALes(sequence, i)) {
				/* TODO */
			}
		}

		return result;
	}

	public static List<List<String>> getPossibleTALes(String sequence, Integer offset) {
		List<List<String>> TALes = new ArrayList<List<String>>();
		List<String> entry;

		int TALLengthMax = Constants.getMaxTALLength();
		int TALLengthMin = Constants.getMinTALLength();
		int NucleaseDistanceMin = Constants.getMinNucleaseDistance();
		int NucleaseDistanceMax = Constants.getMaxNucleaseDistance();
		int secondTALStart;

		entry = new ArrayList<String>();
		for (int firstTALLength = TALLengthMin; firstTALLength <= TALLengthMax; firstTALLength++) {
			for (int secondTALLength = TALLengthMin; secondTALLength <= TALLengthMax; secondTALLength++) {
				for (int distance = NucleaseDistanceMin; distance <= NucleaseDistanceMax; distance++) {
					secondTALStart = offset + firstTALLength + distance;
					if (secondTALStart + secondTALLength > sequence.length())
						continue; /* TALes extend beyond DNA sequence */
					entry = new ArrayList<String>();
					entry.add(sequence.substring(offset, offset + firstTALLength));
					entry.add(sequence.substring(secondTALStart, secondTALStart + secondTALLength));
					TALes.add(entry);
				}
			}
		}

		return TALes;
	}
}
