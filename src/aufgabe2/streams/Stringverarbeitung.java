package aufgabe2.streams;

import java.util.*;
import java.util.stream.*;

public class Stringverarbeitung {

	private String[] standardwerte = {"Eingabe ","Äußeres  ", null, "Strassen-Feger", " ein Haus"};
	private String[] nutzerwerte;
	private List<String> speicherliste;
	
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
		standardwerte = new String[nutzereingabe.length];
		for (int i = 0; i < nutzereingabe.length; i++){
			standardwerte[i] = nutzereingabe[i];
		}
	}
	
	/*
	 * Verarbeitet die im Array gegebenen Strings 
	 * in einem Stream nach den Vorgaben
	 * und legt sie in einer List ab
	 */
	public void verarbeite(){
		List<String> ph = Arrays.asList(standardwerte);
		Stream<String> t1 = ph.stream();
		nutzerwerte = t1.filter(o -> o!=null).map(e -> e.replace("ß", "ss"))
		.map(txt -> txt.toUpperCase()).map(e -> e.replace("Ä", "AE"))
		.map(e -> e.replace("Ö", "OE")).map(e -> e.replace("Ü", "UE"))
		.map(e -> e.trim()).map(o -> o.length()> 8 ? o.substring(0, 8) : o.toString()).toArray(String[]::new);
		speicherliste = Arrays.asList(nutzerwerte);
	}
	
	/*
	 * Gibt die Liste zurueck
	 * @return - Liste mit bearbeiteten Werten
	 */
	public List<String> getListe(){
		return speicherliste;
	}
}
