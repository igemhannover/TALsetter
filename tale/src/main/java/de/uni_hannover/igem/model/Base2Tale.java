package de.uni_hannover.igem.model;

public enum Base2Tale {

	C(Tale.HD), T(Tale.NG), A(Tale.NI), G(Tale.NN);

	private final Tale tale;

	private Base2Tale(Tale tale) {
		this.tale = tale;
	}

	public Tale getTale() {
		return this.tale;
	}

}
