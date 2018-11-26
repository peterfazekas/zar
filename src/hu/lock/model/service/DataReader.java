package hu.lock.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import hu.lock.model.domain.Key;

public class DataReader {

	private int id;
	
	public String consoleRead(String text) {
		Scanner sc = new Scanner(System.in);
		System.out.print(text);
		return sc.nextLine();
	}
	
	public List<Key> fileRead(String fileName) {
		List<Key> keyList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			keyList = br.lines().map(this::createKey).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keyList;
	}
	
	private Key createKey(String line) {
		return new Key(++id, line);
	}
}
