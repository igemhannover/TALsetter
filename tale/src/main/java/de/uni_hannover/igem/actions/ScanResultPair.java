package de.uni_hannover.igem.actions;

import de.uni_hannover.igem.util.ScanResult;

public class ScanResultPair {
	private ScanResult result1;
	private ScanResult result2;

	public String getSequence1() {
		return result1.getSequence();
	}

	public String getSequence2() {
		return result2.getSequence();
	}

	public Integer getPosition() {
		return result1.getPosition();
	}

	public Double getRating() {
		return result1.getRating();
	}

	public ScanResultPair(ScanResult resultA, ScanResult resultB) {
		this.result1 = resultA;
		this.result2 = resultB;
		System.out.println("A:" + resultA + ", B:" + resultB);
	}

	public ScanResult getResult1() {
		return result1;
	}

	public ScanResult getResult2() {
		return result2;
	}
}
