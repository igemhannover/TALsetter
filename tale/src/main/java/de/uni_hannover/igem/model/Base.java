package de.uni_hannover.igem.model;

public enum Base {
	C(BindingStrength.STRONG), G(BindingStrength.STRONG), A(BindingStrength.WEAK), T(BindingStrength.WEAK);

	private final BindingStrength strength;

	private Base(BindingStrength strength) {
		this.strength = strength;
	}

	public BindingStrength getStrength() {
		return this.strength;
	}

	public Base getOpposite() throws Exception {
		switch (this) {
		case C:
			return G;
		case G:
			return C;
		case A:
			return T;
		case T:
			return A;
		default:
			throw new Exception("Kein passendes Gegenstück gefunden");
		}
	}

}
