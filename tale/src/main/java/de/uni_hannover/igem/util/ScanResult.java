package de.uni_hannover.igem.util;

import de.uni_hannover.igem.model.Base2Tale;

public class ScanResult {
	private String sequence;
	private Integer position;
	private Double rating;

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
			try {
				rating = TALRater.getRating(String.join("", Base2Tale.nucleotides2rvds(sequence)));
			} catch (Exception e) {
				/* invalid nucleotide used */
				e.printStackTrace();
			}
		}
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
