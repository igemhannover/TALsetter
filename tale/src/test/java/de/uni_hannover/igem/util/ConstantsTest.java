package de.uni_hannover.igem.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstantsTest {
	@Test
	public void testConstants() {

		assertEquals(Constants.getMaxNucleaseDistance(), Integer.valueOf(16));
	}
}
