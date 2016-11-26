package aufgabe3;

import java.util.ArrayList;
import java.util.List;

/**
* Repraesentiert einen Rangierbahnhof 
* mit beliebig vielen Rangiergleisen
* fuer Zuege
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 26.11.2016
* Aufgabenblatt 3 | Aufgabe 1
*/

public class Rangierbahnhof{
	
	private Zug[] zugAufGleis;
	private int zugAnzahl;
	private List<Lokfuehrer> lfspeicher;
	
	private static Object monitorX = new Object();
	
	/*
	 * Konstruktor
	 */
	Rangierbahnhof(){
		zugAufGleis = new Zug[3];
		zugAnzahl = 0;
		lfspeicher = new ArrayList<Lokfuehrer>();
	}
	
	/*
	 * Gibt die Anzahl der Rangiergleise zurueck
	 * @return gleisAnzahl.length
	 */
	public int getGleisAnzahl(){
		return zugAufGleis.length;
	}
	
	/*
	 * Gibt die Anzahl der Zuege im
	 * Bahnhof zurueck
	 * @return zugAnzahl
	 */
	public int getZugAnzahl(){
		return zugAnzahl;
	}
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen,
	 * der je nach Aufgabe weitergereicht wird
	 * @param lf
	 */
	// TODO: WO NOTIFY VERWENDEN?
	// TODO: BESSERES ZWISCHENSPEICHERN, RICHTIG ENTFERNEN, RICHTIG PR�FEN
	public void arbeiten(Lokfuehrer lf)throws InterruptedException{
		synchronized(monitorX){
			//if(lfspeicher.size() != 0){
				//monitorX.notify();
				//System.out.println(lfspeicher.size());
				//}
			switch(lf.getAufgabe()){
			case 0: zugEinfahrenAuf(lf);
				//System.out.println(lf.toString());
				break;
			case 1:zugAusfahrenAuf(lf);
				//System.out.println(lf.toString());
				break;
			default: System.out.println("Error");
				lf.interrupt();
				break;
			}// END SWITCH
		}// END SYNCRO
	}// END METHOD
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen, der
	 * einen Zug auf ein Rangiergleis einfaehrt.
	 * Rangiergleis muss frei sein,
	 * sonst wird der Lokfuehrer pausiert
	 * @param lf
	 */
	public void zugEinfahrenAuf(Lokfuehrer lf)throws InterruptedException{
		synchronized(monitorX){
			if(zugAufGleis[lf.getGleis()] == null){
				zugAufGleis[lf.getGleis()] = lf.getZug();
				zugAnzahl++;
				System.out.println(lf.toString());
			}// END IF
			else{
				lfspeicher.add(lf);
				monitorX.wait();
			}// END ELSE
		}// END SYNCRO
	}// END METHOD
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen, der
	 * einen Zug aus einem Rangiergleis ausfaehrt.
	 * Rangiergleis muss belegt sein,
	 * sonst wird der Lokfuehrer pausiert
	 * @param lf
	 */
	public void zugAusfahrenAuf(Lokfuehrer lf) throws InterruptedException{
		synchronized(monitorX){
			if(zugAufGleis[lf.getGleis()] != null){
				lf.setZug(zugAufGleis[lf.getGleis()]);
				zugAufGleis[lf.getGleis()] = null;
				zugAnzahl--;
				System.out.println(lf.toString());
			}// END IF
			else{
				lfspeicher.add(lf);
				monitorX.wait();
			}// END ELSE
		}// END SYNCRO
	}// END METHOD
	
}