package de.uni_hannover.igem;

import java.util.List;

import de.uni_hannover.igem.actions.ExactScan;
import de.uni_hannover.igem.model.Tale;
import de.uni_hannover.igem.util.Misc;

public class Main {

	public static void main(String[] args) {

		List<Tale> talesequence = ExactScan.getTale("ACTACAAGKLSSENF");
		try {
			System.out.println(Misc.getCounterSequence("ACTACAAGPENIS"));
		} catch (Exception e) {
			System.out.println("Du dumm?!");
		}

	}

}
