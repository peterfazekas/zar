package hu.lock.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public String getFirtsRepeatKeyId() {
		return getFirtsRepeatKey()
				.map(i -> String.format("Az első ismétrődést tartalmazó próbálkozás sorszáma: %d", i))
				.orElse("Nem volt ismétlődő számjegy");
	}
	
	private Optional<Integer> getFirtsRepeatKey() {
		return keyList.stream().filter(Key::hasCodeRepeat).map(Key::getId).findFirst();
	}
	
	public List<String> getOpenResults(String masterKey) {
		return keyList.stream()
				.map(i -> i.openResult(masterKey))
				.collect(Collectors.toList());
	}

	public String getRandomKey(String masterKey) {
		Random random = new Random();
		List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
		return IntStream.range(0, masterKey.length())
				.map(i -> numbers.remove(random.nextInt(numbers.size())))
				.mapToObj(String::valueOf)
				.collect(Collectors.joining());
	}
}
