package de.uni_hannover.igem.model;

public enum Actions {
	EXACT_SCAN("Exact Scan"), NUCLEASE_SCAN("Nuclease Scan"), GUESS_SCAN("Guess Scan");
	private final String name;

	/**
	 * @param s
	 */
	private Actions(String s) {
		name = s;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.name;
	}
}
