package de.uni_hannover.igem.actions;

import java.util.List;

import de.uni_hannover.igem.model.Tale;
import de.uni_hannover.igem.util.Misc;

public class ExactScan {

	public static List<Tale> getTale(String sequence) {
		return Misc.getTale2Sequence(sequence);
	}
}
