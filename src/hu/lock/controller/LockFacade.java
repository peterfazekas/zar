package hu.lock.controller;

import java.util.List;
import java.util.stream.Collectors;

import hu.lock.model.domain.Key;

public class LockFacade {
	
	private final List<Key> keyList;

	public LockFacade(List<Key> keyList) {
		this.keyList = keyList;
	}
	
	public String getSameKeyIds(String masterKey) {
		return keyList.stream()
				.filter(i -> i.isEqual(masterKey))
				.map(Key::getId)
				.map(Object::toString)
				.collect(Collectors.joining(" "));
	}
	
	public List<String> getOpenResults(String masterKey) {
		return keyList.stream()
				.map(i -> i.openResult(masterKey))
				.collect(Collectors.toList());
	}

}
