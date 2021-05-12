package spring.application;

import java.util.concurrent.atomic.AtomicLong;

public class CreateID {

	// Class that creates a unique id, to be assigned to messages

	private static AtomicLong idCounter = new AtomicLong();

	public static String createID() {
		return String.valueOf(idCounter.getAndIncrement());

	}

	public static void resetID() {
		idCounter.set(0);
	}

}
