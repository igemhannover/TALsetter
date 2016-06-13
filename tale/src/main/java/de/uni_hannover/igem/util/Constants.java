package de.uni_hannover.igem.util;

import java.util.Map;
import java.util.HashMap;

/* this seriously needs some metaprogramming */

public class Constants {
	private static Map<String, Object> values;
	private static Map<String, String> descriptions;
	
	public static void initialize() {
		values = new HashMap<String, Object>();
		setMinTALLength(12);
		setMaxTALLength(24);
		setMinNucleaseDistance(12);
		setMaxNucleaseDistance(16);
	}
	
	/* TAL min & max lengths */
	public static Integer getMinTALLength() {
		return (Integer)values.get("tal.length.min");
	}
	
	public static void setMinTALLength(Integer value) {
		values.put("tal.length.min", (Object)value);
	}
	
	public static Integer getMaxTALLength() {
		return (Integer)values.get("tal.length.max");
	}
	
	public static void setMaxTALLength(Integer value) {
		values.put("tal.length.max", (Object)value);
	}
	
	/* min & max distances between nucleases */
	public static Integer getMinNucleaseDistance() {
		return (Integer)values.get("nuclease.distance.min");
	}
	
	public static void setMinNucleaseDistance(Integer value) {
		values.put("nuclease.distance.min", (Object)value);
	}

	public static Integer getMaxNucleaseDistance() {
		return (Integer)values.get("nuclease.distance.max");
	}
	
	public static void setMaxNucleaseDistance(Integer value) {
		values.put("nuclease.distance.max", (Object)value);
	}
}