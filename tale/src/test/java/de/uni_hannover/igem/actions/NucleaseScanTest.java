package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.Constants;
import de.uni_hannover.igem.util.ScanResult;

import static org.junit.Assert.assertEquals;

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
		HashSet<List<ScanResult>> calculated;
		calculated = new HashSet<List<ScanResult>>(NucleaseScan.getPossibleTALes(sequence, 1));
		Set<List<ScanResult>> expected = new HashSet<List<ScanResult>>();

		/* first length = 2, second length = 2, distance = 0 or 1 */
		expected.add(Arrays.asList(new ScanResult("at", 1), new ScanResult("ag", 3)));
		expected.add(Arrays.asList(new ScanResult("at", 1), new ScanResult("gc", 4)));
		/* first length = 3, second length = 2, distance = 0 or 1 */
		expected.add(Arrays.asList(new ScanResult("ata", 1), new ScanResult("gc", 4)));
		expected.add(Arrays.asList(new ScanResult("ata", 1), new ScanResult("cg", 5)));
		/* first length = 2, second length = 3, distance = 0 or 1 */
		expected.add(Arrays.asList(new ScanResult("at", 1), new ScanResult("agc", 3)));
		expected.add(Arrays.asList(new ScanResult("at", 1), new ScanResult("gcg", 4)));
		/* first length = 3, second length = 3, distance = 0 or 1 */
		expected.add(Arrays.asList(new ScanResult("ata", 1), new ScanResult("gcg", 4)));
		expected.add(Arrays.asList(new ScanResult("ata", 1), new ScanResult("cgc", 5)));

		assertEquals(expected, calculated);
	}

	@Test
	public void testNucleaseScanTooShort() {
		initializeNucleaseScanTest();

		/* Start at position 3 this time, so our sequence is "agcgc" */
		HashSet<List<ScanResult>> calculated = new HashSet<List<ScanResult>>(
				NucleaseScan.getPossibleTALes(sequence, 3));
		Set<List<ScanResult>> expected = new HashSet<List<ScanResult>>();

		expected.add(Arrays.asList(new ScanResult("ag", 3), new ScanResult("cg", 5)));
		expected.add(Arrays.asList(new ScanResult("ag", 3), new ScanResult("gc", 6)));
		expected.add(Arrays.asList(new ScanResult("ag", 3), new ScanResult("cgc", 5)));
		expected.add(Arrays.asList(new ScanResult("agc", 3), new ScanResult("gc", 6)));

		assertEquals(expected, calculated);
	}
}
