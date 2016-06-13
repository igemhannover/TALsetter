package de.uni_hannover.igem.util;


import de.uni_hannover.igem.util.Constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstantsTest {
	@Test
	public void testConstants() {
		Constants.initialize();
		assertEquals(Constants.getMaxNucleaseDistance(), Integer.valueOf(16));
	}
}
