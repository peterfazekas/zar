package hu.lock.model.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Key {

	private final int id;
	private final String combination;
	
	public Key(int id, String combination) {
		this.id = id;
		this.combination = combination;
	}

	public int getId() {
		return id;
	}

	public String getCombination() {
		return combination;
	}
	
	public boolean isEqual(String masterKey) {
		return combination.equals(masterKey);
	}

	public boolean hasCodeRepeat() {
		return createKeyMap().values().stream().anyMatch(i -> i > 1);
	}

	private Map<String, Long> createKeyMap() {
		return combination.chars()
				.mapToObj(Integer::toString)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
	
	public String openResult(String masterKey) {
		String result;
		if (!azonosHossz(masterKey, combination)) {
			result = " hib�s hossz!";
		} else {
			result = nyit(masterKey, combination) ? " sikeres" :  " hib�s k�dsz�m";
		}
		return combination + result;
	}
	
	private boolean nyit(String jo, String proba) {
		boolean egyezik = azonosHossz(jo, proba);
		if (egyezik) {
			int elteres = (int) jo.charAt(0) - (int) proba.charAt(0);
			for (int i = 1; i < jo.length(); i++) {
				if ((elteres - ((int) jo.charAt(i) - (int) proba.charAt(i))) % 10 != 0) {
					egyezik = false;
				}
			}
		}
		return egyezik;
	}

	private boolean azonosHossz(String jo, String proba) {
		boolean egyezik = jo.length() == proba.length();
		return egyezik;
	}
}
