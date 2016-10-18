package de.uni_hannover.igem.actions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.uni_hannover.igem.util.Constants;
import de.uni_hannover.igem.util.Misc;
import de.uni_hannover.igem.util.ScanResult;

public class NucleaseScanTest {
	public static String sequence = "tatagcgc";

	public void initializeNucleaseScanTest() {

		/*
		 * define small variation ranges, so we have to specify less expected
		 * values later on
		 */
		Constants.setMinTALLength(2);
		Constants.setMaxTALLength(3);
		Constants.setMinNucleaseDistance(0);
		Constants.setMaxNucleaseDistance(1);
	}

	public void addResult(Set<List<ScanResult>> set, String seq1, Integer pos1, String seq2, Integer pos2) {
		String seq1complement, seq2complement;
		try {
			seq1complement = Misc.getCounterSequence(seq1);
			seq2complement = Misc.getCounterSequence(seq2);
		} catch (Exception e) {
			return;
		}
		/*
		 * The sequence of the second TALEN needs to be inverted, because
		 * it binds on the opposite strand of the DNA double helix.
		 */
		set.add(Arrays.asList(new ScanResult(seq1, pos1), new ScanResult(seq2complement, pos2)));
	}

	@Test
	public void testNucleaseScan() {
		initializeNucleaseScanTest();

		/* Start at position 1, so our sequence is "atagcgc" */
		HashSet<List<ScanResult>> calculated;
		calculated = new HashSet<List<ScanResult>>(NucleaseScan.getPossibleTALes(sequence, 1));
		Set<List<ScanResult>> expected = new HashSet<List<ScanResult>>();

		/* first length = 2, second length = 2, distance = 0 or 1 */
		addResult(expected, "at", 1, "ag", 3);
		addResult(expected, "at", 1, "gc", 4);
		/* first length = 3, second length = 2, distance = 0 or 1 */
		addResult(expected, "ata", 1, "gc", 4);
		addResult(expected, "ata", 1, "cg", 5);
		/* first length = 2, second length = 3, distance = 0 or 1 */
		addResult(expected, "at", 1, "agc", 3);
		addResult(expected, "at", 1, "gcg", 4);
		/* first length = 3, second length = 3, distance = 0 or 1 */
		addResult(expected, "ata", 1, "gcg", 4);
		addResult(expected, "ata", 1, "cgc", 5);

		assertEquals(expected, calculated);
	}

	@Test
	public void testNucleaseScanTooShort() {
		initializeNucleaseScanTest();

		/* Start at position 3 this time, so our sequence is "agcgc" */
		HashSet<List<ScanResult>> calculated = new HashSet<List<ScanResult>>(
				NucleaseScan.getPossibleTALes(sequence, 3));
		Set<List<ScanResult>> expected = new HashSet<List<ScanResult>>();

		addResult(expected, "ag", 3, "cg", 5);
		addResult(expected, "ag", 3, "gc", 6);
		addResult(expected, "ag", 3, "cgc", 5);
		addResult(expected, "agc", 3, "gc", 6);

		assertEquals(expected, calculated);
	}
}
