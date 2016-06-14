package de.uni_hannover.igem.util;

public class ScanResult {
	public String sequence;
	public Integer position;
	public Double rating;

	public ScanResult(String sequence, Integer position) {
		this.sequence = sequence.toLowerCase();
		this.position = position;
	}

	@Override
	public String toString() {
		return sequence + "@" + position.toString();
	}

	@Override
	public int hashCode() {
		return sequence.toLowerCase().hashCode() + position.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other instanceof ScanResult) {
			return sequence.equals(((ScanResult) other).getSequence())
					&& position == ((ScanResult) other).getPosition();
		}
		return false;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence.toLowerCase();
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Double getRating() {
		if (rating == null) {
			rating = 1.0; /* TODO: insert rating function here */
		}
		return rating;
	}
}
