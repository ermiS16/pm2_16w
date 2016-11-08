package aufgabe2.streams;

import java.util.*;
import java.util.stream.*;

public class Stringverarbeitung {

	private String[] standardWerte = {"Eingabe ","Äußeres  ", null, "Strassen-Feger", " ein Haus"};
	private String[] nutzerWerte;
	private List<String> speicherListe;
	
	/*
	 * Constructor
	 */
	Stringverarbeitung(){
	}
	
	/*
	 * Constructor mit Nutzereingabe
	 * @param nutzereingabe - beliebig viele Strings per varargs
	 */
	Stringverarbeitung (String... nutzereingabe){
		standardWerte = new String[nutzereingabe.length];
		for (int i = 0; i < nutzereingabe.length; i++){
			standardWerte[i] = nutzereingabe[i];
		}
	}
	
	/*
	 * Verarbeitet die im Array gegebenen Strings 
	 * in einem Stream nach den Vorgaben
	 * und legt sie in einer List ab
	 */
	public void verarbeite(){
		List<String> ph = Arrays.asList(standardWerte);
		Stream<String> t1 = ph.stream();
		nutzerWerte = t1.filter(o -> o!=null).map(e -> e.replace("ß", "ss"))
		.map(txt -> txt.toUpperCase()).map(e -> e.replace("Ä", "AE"))
		.map(e -> e.replace("Ö", "OE")).map(e -> e.replace("Ü", "UE"))
		.map(e -> e.trim()).map(o -> o.length()> 8 ? o.substring(0, 8) : o.toString())
		.toArray(String[]::new);
		speicherListe = Arrays.asList(nutzerWerte);
	}
	
	/*
	 * Gibt die Liste zurueck
	 * @return - Liste mit bearbeiteten Werten
	 */
	public List<String> getListe(){
		return speicherListe;
	}
}
