package aufgabe2.streams;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.*;
import java.util.*;

public class Stringtester {

	@Test
	public void testeVerarbeite() {
		Stringverarbeitung test1 = new Stringverarbeitung();
		test1.verarbeite();
		assertEquals(test1.getListe().contains("EINGABE"), true);
		//assertEquals(test1.getListe().contains("AEUSSERE"), true);
		//assertEquals(test1.getListe().contains("STRASSEN"), true);
		assertEquals(test1.getListe().contains("EIN HAUS"), true);
		assertEquals(test1.getListe().contains(" EIN HAUS"), false);
		//System.out.println(test1.getListe());
	}
	
	@Test
	public void testeContainsNull(){
		Stringverarbeitung test = new Stringverarbeitung();
		test.verarbeite();
		assertEquals(test.getListe().contains(null), false);
		//System.out.println(test.getListe());
	}

	@Test
	public void testeOnlyContainsNull(){
		Stringverarbeitung test = new Stringverarbeitung(null, null, null);
		test.verarbeite();
		assertEquals(test.getListe().contains(null), false);
		//System.out.println(test.getListe());
	}
	
	@Test
	public void testeSonderzeichen(){
		Stringverarbeitung test = new Stringverarbeitung("Ä", "ä", "Ü", "ü", "Ö", "ö", "Straße", "#");
		test.verarbeite();
		assertEquals(test.getListe().contains("#"), true);
		assertEquals(test.getListe().contains("ä"), false);
		assertEquals(test.getListe().contains("UE"), true);
		//System.out.println(test.getListe());
	}
}
