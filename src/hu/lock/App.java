package hu.lock;

import hu.lock.controller.LockFacade;
import hu.lock.model.service.DataReader;
import hu.lock.model.service.DataWriter;

public class App {

	private final LockFacade lock;
	private final DataReader data;
	private final DataWriter writer;
	
	public App() {
		data = new DataReader();
		lock = new LockFacade(data.fileRead("ajto.txt"));
		writer = new DataWriter("siker.txt");
	}
	
	public static void main(String[] args) {
		new App().run();
	}

	private void run() {
		String masterKey = data.consoleRead("2. feladat: Adja meg mi nyitja a zárat: ");
		System.out.println("3. feladat: A nyitó kódszámok sorai: " + lock.getSameKeyIds(masterKey));
		System.out.println("4. feladat: " + lock.getFirtsRepeatKeyId());
		System.out.println("5. feladat: Egy " + masterKey.length() + " hosszú kódszám: " + lock.getRandomKey(masterKey));
		writer.printAll(lock.getOpenResults(masterKey));
	}

}
