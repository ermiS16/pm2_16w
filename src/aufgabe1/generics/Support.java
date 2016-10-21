package aufgabe1.generics;

/**
 * 
 * @author Eric Misfeld, Simon Felske
 * @version 13.10.2016
 * 
 * Support Klasse fuer die statische Methode
 *
 */

public class Support{
	
	/*
	 * Prueft ob das erste Element einer beliebiegen Liste eine Zahl ist
	 * @param list - beliebige Liste
	 * @return wahrheitswert
	 */
	public static boolean istZahl(ArrayListe<?> list){
		boolean wahrheitswert = false;
		//mindestens ein Element vorhanden?
		if(list.getAnzahlElemente() >= 1){
			//ist das erste Element eine Zahl?
			if(list.get(0) instanceof Number){
				wahrheitswert = true;
			}
		}
		return wahrheitswert;
	}
}
