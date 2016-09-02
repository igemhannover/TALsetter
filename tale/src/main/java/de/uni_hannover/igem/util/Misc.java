package de.uni_hannover.igem.util;

import java.util.ArrayList;
import java.util.List;

import de.uni_hannover.igem.model.Base;
import de.uni_hannover.igem.model.Base2Tale;
import de.uni_hannover.igem.model.Tale;

public class Misc {
	/**
	 * Gets the counter-part of a dna-sequence
	 * 
	 * @param sequence
	 * @return the counter-part
	 * @throws Exception
	 */
	public static String getCounterSequence(String sequence) throws Exception {
		StringBuffer baseBuffer = new StringBuffer();

		for (int i = 0; i < sequence.length(); i++) {
			char currChar = sequence.charAt(i);
			Base base = Base.valueOf(String.valueOf(currChar).toUpperCase());
			baseBuffer.append(base.getOpposite().toString());

		}
		return baseBuffer.toString();
	}

	public static List<Tale> getTale2DNASequence(String sequence) {
		List<Tale> taleSequence = new ArrayList<Tale>();
		for (int i = 0; i < sequence.length(); i++) {
			char currChar = sequence.charAt(i);
			try {
				Base2Tale base2tale = Base2Tale.valueOf(String.valueOf(currChar).toUpperCase());
				taleSequence.add(base2tale.getTale());
			} catch (Exception e) {
				taleSequence.add(Tale.NS);
			}
		}
		return taleSequence;
	}

	public static String getTaleSequence2DNASequence(String sequence) {

		List<Tale> resultList = getTale2DNASequence(sequence);

		StringBuffer sb = new StringBuffer();

		for (Tale tale : resultList) {
			sb.append(tale.name());
		}

		return sb.toString();

	}

	public static List<Base> getSequenceList2Tale(String tale) {
		List<Base> baseSequence = new ArrayList<Base>();
		String[] taleSequence = tale.split("(?<=\\G..)");

		for (String currTale : taleSequence) {
			Tale taleEnum = Tale.valueOf(currTale.toUpperCase());
			Base base = Base2Tale.getBase2Tale(taleEnum);
			baseSequence.add(base);
		}
		return baseSequence;
	}

	public static String getSequence2Tale(String tale) {
		StringBuffer sb = new StringBuffer();
		String[] taleSequence = tale.split("(?<=\\G..)");

		for (String currTale : taleSequence) {
			Tale taleEnum = Tale.valueOf(currTale.toUpperCase());
			Base base = Base2Tale.getBase2Tale(taleEnum);
			sb.append(base.name());
		}
		return sb.toString();
	}

}
