package de.uni_hannover.igem.model;

public enum Actions {
	EXACT_SCAN("Exact Scan"), NUCLEUS_SCAN("Nucleus Scan");
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
