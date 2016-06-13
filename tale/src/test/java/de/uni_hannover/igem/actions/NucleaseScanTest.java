package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.Constants;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class NucleaseScanTest {
	public static String sequence = "tatagcgc";

	public void initializeNucleaseScanTest() {
		Constants.initialize();
		/*
		 * define small variation ranges, so we have to specify less expected
		 * values later on
		 */
		Constants.setMinTALLength(2);
		Constants.setMaxTALLength(3);
		Constants.setMinNucleaseDistance(0);
		Constants.setMaxNucleaseDistance(1);
	}

	@Test
	public void testNucleaseScan() {
		initializeNucleaseScanTest();

		/* Start at position 1, so our sequence is "atagcgc" */
		HashSet<List<String>> calculated = new HashSet<List<String>>(NucleaseScan.getPossibleTALes(sequence, 1));
		Set<List<String>> expected = new HashSet<List<String>>();

		/* first length = 2, second length = 2, distance = 0 or 1 */
		expected.add(Arrays.asList("at", "ag"));
		expected.add(Arrays.asList("at", "gc"));
		/* first length = 3, second length = 2, distance = 0 or 1 */
		expected.add(Arrays.asList("ata", "gc"));
		expected.add(Arrays.asList("ata", "cg"));
		/* first length = 2, second length = 3, distance = 0 or 1 */
		expected.add(Arrays.asList("at", "agc"));
		expected.add(Arrays.asList("at", "gcg"));
		/* first length = 3, second length = 3, distance = 0 or 1 */
		expected.add(Arrays.asList("ata", "gcg"));
		expected.add(Arrays.asList("ata", "cgc"));

		assertEquals(expected, calculated);
	}

	@Test
	public void testNucleaseScanTooShort() {
		initializeNucleaseScanTest();

		/* Start at position 3 this time, so our sequence is "agcgc" */
		HashSet<List<String>> calculated = new HashSet<List<String>>(NucleaseScan.getPossibleTALes(sequence, 3));
		Set<List<String>> expected = new HashSet<List<String>>();

		expected.add(Arrays.asList("ag", "cg"));
		expected.add(Arrays.asList("ag", "gc"));
		expected.add(Arrays.asList("ag", "gc"));
	}
}
