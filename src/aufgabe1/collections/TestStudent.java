package aufgabe1.collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStudent {

	@Test
	public void testCompareTo() {
		Student test1 = new Student("Max", "Mustermann", 1234567, new Pruefungsleistung("A", 8));
		Student test2 = new Student("Max", "Mustermann", 1234566, new Pruefungsleistung("A", 8));
		Student test3 = new Student("Max", "Mustermann", 1234568, new Pruefungsleistung("A", 8));
		Student test4 = new Student("Sam", "Summermann", 1234567, new Pruefungsleistung("B", 8));
		assertEquals("Gleiche Matrikelnummer bedeutet gleicher Student", 0, test1.compareTo(test4));
		assertEquals("Unterschiedliche Matrikelnummer, kleiner", -1, test2.compareTo(test3));
		assertEquals("Unterschiedliche Matrikelnummer, groesser", 1, test3.compareTo(test2));
	}
}
