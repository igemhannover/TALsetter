package de.uni_hannover.igem.model;

import java.util.HashMap;
import java.util.Map;

public enum Base2Tale {

	C(Tale.HD), T(Tale.NG), A(Tale.NI), G(Tale.NN), X(Tale.NS);

	private final Tale tale;
	private static Map<Tale, Base> reverseMap;

	static {
		reverseMap = new HashMap<Tale, Base>();
		for (Base2Tale base2Tale : Base2Tale.values()) {
			reverseMap.put(base2Tale.tale, Base.valueOf(base2Tale.name()));
		}
	}

	private Base2Tale(Tale tale) {
		this.tale = tale;
	}

	public Tale getTale() {
		return this.tale;
	}

	public static Base getBase2Tale(Tale tale) {
		return reverseMap.get(tale);
	}

}
