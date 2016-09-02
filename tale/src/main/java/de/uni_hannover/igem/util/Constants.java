package de.uni_hannover.igem.util;

import java.util.HashMap;
import java.util.Map;

/* this seriously needs some metaprogramming */

public class Constants {
	private static Map<String, Object> values;
	private static Map<String, String> descriptions;

	static {
		values = new HashMap<String, Object>();
		setMinTALLength(12);
		setMaxTALLength(24);
		setMinNucleaseDistance(12);
		setMaxNucleaseDistance(16);
		setOptimalTALLength(18);
		setMinWeakBinding(1);
		setMaxWeakBindung(5);
		setOptimalStrongBinding(4);
	}

	/* TAL min & max lengths */
	public static Integer getMinTALLength() {
		return (Integer) values.get("tal.length.min");
	}

	public static void setMinTALLength(Integer value) {
		values.put("tal.length.min", (Object) value);
	}

	public static Integer getMaxTALLength() {
		return (Integer) values.get("tal.length.max");
	}

	public static void setMaxTALLength(Integer value) {
		values.put("tal.length.max", (Object) value);
	}

	/* min & max distances between nucleases */
	public static Integer getMinNucleaseDistance() {
		return (Integer) values.get("nuclease.distance.min");
	}

	public static void setMinNucleaseDistance(Integer value) {
		values.put("nuclease.distance.min", (Object) value);
	}

	public static Integer getMaxNucleaseDistance() {
		return (Integer) values.get("nuclease.distance.max");
	}

	public static void setMaxNucleaseDistance(Integer value) {
		values.put("nuclease.distance.max", (Object) value);
	}

	public static void setOptimalTALLength(Integer value) {
		values.put("tal.length.optimal", (Object) value);
	}

	public static Integer getOptimalTALLength() {
		return (Integer) values.get("tal.length.optimal");
	}

	public static void setMinWeakBinding(Integer value) {
		values.put("tal.weakbinding.min", (Object) value);
	}

	public static Integer getMinWeakBinding() {
		return (Integer) values.get("tal.weakbinding.min");
	}

	public static void setMaxWeakBindung(Integer value) {
		values.put("tal.weakbinding.max", (Object) value);
	}

	public static Integer getMaxWeakBinding() {
		return (Integer) values.get("tal.weakbinding.max");
	}

	public static void setOptimalStrongBinding(Integer value) {
		values.put("tal.strongbinding.optimal", (Object) value);
	}

	public static Integer getOptimalStrongBinding() {
		return (Integer) values.get("tal.strongbinding.optimal");
	}

}