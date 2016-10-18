package de.uni_hannover.igem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public static List<String> nucleotides2rvds(String nucleotides) throws Exception {
		List<String> result = new ArrayList<String>();
		String nucleotide;
		for (int i = 0; i < nucleotides.length(); i++) {
			nucleotide = nucleotides.substring(i, i+1);
			result.add(nucleotide2rvd(nucleotide));
		}
		return result;
	}

	public static String nucleotide2rvd(String nucleotide) throws Exception {
		switch(nucleotide.toLowerCase().charAt(0)) {
		case 'a':
			return "ni";
		case 'c':
			return "hd";
		case 'g':
			return "nn";
		case 't':
			return "ng";
		case 'x':
			return "ns";
		default:
			throw new Exception("Error: no RDV known for nucleotide `" + nucleotide + "`.");
		}
	}
}
