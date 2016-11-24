package aufgabe2.streams;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Testklasse zu "Stringverarbeitung"
 * 
* Praktikum TIPR2, WS2016/2017
 * Praktikumsgruppe Nr. 4
 * @author Eric Misfeld, Simon Felske
 * @version 21.11.2016
 * Aufgabenblatt 2 | Aufgabe 2
 */

public class Stringtester {

	/*
	 * Test auf Standardwerte
	 */
	@Test
	public void testeVerarbeite() {
		Stringverarbeitung test1 = new Stringverarbeitung();
		test1.verarbeite();
		assertEquals(test1.getListe().contains("EINGABE"), true);
		assertEquals(test1.getListe().contains("AEUSSERE"), true);
		assertEquals(test1.getListe().contains("STRASSEN"), true);
		assertEquals(test1.getListe().contains("EIN HAUS"), true);
		assertEquals(test1.getListe().contains(" EIN HAUS"), false);
		System.out.println("V: " + test1.getListe());
	}
	
	/*
	 * Test auf null
	 */
	@Test
	public void testeContainsNull(){
		Stringverarbeitung test = new Stringverarbeitung();
		test.verarbeite();
		assertEquals(test.getListe().contains(null), false);
		System.out.println("CN:" + test.getListe());
	}

	/*
	 * Test auf nur null
	 */
	@Test
	public void testeOnlyContainsNull(){
		Stringverarbeitung test = new Stringverarbeitung(null, null, null);
		test.verarbeite();
		assertEquals(test.getListe().contains(null), false);
		assertTrue(test.getListe().size() == 0);
		System.out.println("OCN: " + test.getListe());
	}
	
	/*
	 * Test auf dt. Umlaute und ß
	 */
	@Test
	public void testeUmlaute(){
		Stringverarbeitung test = new Stringverarbeitung("Ä", "ä", "Ü", "ü", "Ö", "ö", "Straße", "#");
		test.verarbeite();
		assertEquals(test.getListe().contains("#"), true);
		assertEquals(test.getListe().contains("ä"), false);
		assertEquals(test.getListe().contains("Ä"), false);
		assertEquals(test.getListe().contains("UE"), true);
		assertEquals(test.getListe().contains("ß"), false);
		System.out.println("U: " + test.getListe());
	}
	
	/*
	 * Test auf leere Strings und Strings mit
	 * unnoetigen Leerzeichen
	 */
	@Test
	public void testeLeereStrings(){
		Stringverarbeitung test = new Stringverarbeitung("", "abcdef.", " ", "", "  .  ");
		test.verarbeite();
		assertEquals(test.getListe().contains(""), true);
		assertEquals(test.getListe().contains("."), true);
		assertEquals(test.getListe().contains(" "), false);
		assertEquals(test.getListe().contains("ABCDEF."), true);
		System.out.println("LS: " + test.getListe());
	}
	
	/*
	 * Test auf Zahlen und Sonderzeichen
	 */
	@Test
	public void testeSonderZeichen(){
		Stringverarbeitung test = new Stringverarbeitung("1", "22", "#*@", "null", null, "\"", "\\");
		test.verarbeite();
		assertEquals(test.getListe().contains("\""), true);
		assertEquals(test.getListe().contains("\\"), true);
		assertEquals(test.getListe().contains("1"), true);
		assertEquals(test.getListe().contains("22"), true);
		assertEquals(test.getListe().contains("#*@"), true);
		assertTrue(test.getListe().size() == 6);
		System.out.println("SZ: " + test.getListe());
	}
	
	@Test
	public void testeStringGroesserAcht(){
		Stringverarbeitung test = new Stringverarbeitung("1234567890");
		test.verarbeite();
		assertEquals(test.getListe().get(0).length(), 8);
		System.out.println("SGA: " + test.getListe());
	}
}
