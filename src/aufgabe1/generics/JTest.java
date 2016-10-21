package aufgabe1.generics;

import static org.junit.Assert.*;

import org.junit.Test;

public class JTest {

	@Test
	public void testGetAnzahlElemente(){
		ArrayListe testliste = new ArrayListe();
		assertEquals("Funktioniert getAnzahlElemente?", 0, testliste.getAnzahlElemente());
	}
	
	@Test
	public void testHinzufuegen1() {
		ArrayListe testliste = new ArrayListe();
		String teststring = new String ("test");
		testliste.hinzufuegen(teststring);
		assertEquals("Wurde des Objekt hinzugefuegt?", 1, testliste.getAnzahlElemente());
	}
	
	@Test
	public void testEntfernenIndex(){
		ArrayListe testliste = new ArrayListe();
		String teststring = new String ("test");
		testliste.hinzufuegen(teststring);
		testliste.entferneElementAnIndex(0);
		assertEquals("Wurde des Objekt entfernt", 0, testliste.getAnzahlElemente());
		assertEquals("Wurde des Objekt entfernt", null, testliste.get(0));
	}
	
	@Test
	public void testEntfernen(){
		ArrayListe<String> testliste = new ArrayListe<String>();
		testliste.hinzufuegen("eins");
		testliste.hinzufuegen("zwei");
		testliste.hinzufuegen("drei");
		testliste.hinzufuegen("vier");		
		System.out.println(testliste);
		testliste.entfernen("zwei");
		System.out.println(testliste);
		
		assertEquals("Wurde des Objekt entfernt", 3, testliste.getAnzahlElemente());
	}
	
	
	
	@Test
	public void testGetKleinstesElement(){
		ArrayListe testliste = new ArrayListe();
		Integer testint1 = new Integer(10);
		Integer testint2 = new Integer(13);
		Integer testint3 = new Integer(20);
		Integer testint4 = new Integer(9);
		testliste.hinzufuegen(testint1);
		testliste.hinzufuegen(testint2);
		testliste.hinzufuegen(testint3);
		testliste.hinzufuegen(testint4);
		assertEquals("Kleinstes Objekt gefunden", 9, testliste.getKleinstesElement());
	}
	
	@Test
	public void testSupport(){
		ArrayListe testliste = new ArrayListe();
		assertEquals("Erstes Objekt eine Nummer?", false, Support.istZahl(testliste));
		Integer testint1 = new Integer(10);
		Integer testint2 = new Integer(13);
		Integer testint3 = new Integer(20);
		Integer testint4 = new Integer(9);
		testliste.hinzufuegen(testint1);
		testliste.hinzufuegen(testint2);
		testliste.hinzufuegen(testint3);
		testliste.hinzufuegen(testint4);
		assertEquals("Erstes Objekt eine Nummer?", true, Support.istZahl(testliste));
		ArrayListe testliste2 = new ArrayListe();
		String teststring1 = new String ("ABC");
		assertEquals("Erstes Objekt eine Nummer?", false, Support.istZahl(testliste2));
	}
}
