package de.uni_hannover.igem.util;

import java.util.ArrayList;
import java.util.List;

import de.uni_hannover.igem.model.Base;
import de.uni_hannover.igem.model.Base2Tale;
import de.uni_hannover.igem.model.Tale;

public class Misc {

	public static String getCounterSequence(String sequence) throws Exception {
		StringBuffer baseBuffer = new StringBuffer();

		for (int i = 0; i < sequence.length(); i++) {
			char currChar = sequence.charAt(i);
			Base base = Base.valueOf(String.valueOf(currChar).toUpperCase());
			baseBuffer.append(base.getOpposite().toString());

		}
		return baseBuffer.toString();
	}

	public static List<Tale> getTale2Sequence(String sequence) {
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

}
