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
* @version 28.11.2016
* Aufgabenblatt 3 | Aufgabe 1
*/

public class Rangierbahnhof{
	
	private Zug[] zugAufGleis;
	//private List<Lokfuehrer> lfspeicher;
	
	//public Object monitorX = new Object();
	
	/*
	 * Konstruktor
	 */
	Rangierbahnhof(){
		zugAufGleis = new Zug[3];
		//lfspeicher = new ArrayList<Lokfuehrer>();
	}
	
	/*
	 * Konstruktor mit Nutzereingabe
	 * @param gleisAnzahl - Anzahl der Gleise
	 */
	Rangierbahnhof(int gleisAnzahl){
		if(gleisAnzahl > 0){
			zugAufGleis = new Zug[gleisAnzahl];
		}// END IF
	}// END METHOD
	
	/*
	 * Gibt die Anzahl der Gleise zurueck
	 * @return gleisAnzahl.length - Anzahl an Gleisen
	 */
	public int getGleisAnzahl(){
		return zugAufGleis.length;
	}// END METHOD
	
//	/*
//	 * Gibt die Anzahl der Zuege im
//	 * Bahnhof zurueck
//	 * @return zugAnzahl
//	 */
//	public int getZugAnzahl(){
//		return zugAnzahl;
//	}
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen,
	 * der je nach Aufgabe weitergereicht wird
	 * @param lf
	 */
	// TODO: WO NOTIFY VERWENDEN?
	// TODO: BESSERES ZWISCHENSPEICHERN, RICHTIG ENTFERNEN, RICHTIG PRÜFEN
	public synchronized void arbeiten(Lokfuehrer lf)throws InterruptedException{
		//synchronized(monitorX){
			//monitorX.notifyAll();
			switch(lf.getAufgabe()){
			
			case 0: while((zugAufGleis[lf.getGleis()] != null)){
					monitorX.wait();
					}
					zugEinfahrenAuf(lf);
				break;
				
			case 1:zugAusfahrenAuf(lf);
				break;
				
			default: System.out.println("Error");
				lf.interrupt();
				break;
			}// END SWITCH
			//}// END SYNCRO
	}// END METHOD
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen, der
	 * einen Zug auf ein Rangiergleis einfaehrt.
	 * Rangiergleis muss frei sein,
	 * sonst wird der Lokfuehrer pausiert
	 * @param lf
	 */
	public synchronized void zugEinfahrenAuf(Lokfuehrer lf)throws InterruptedException{
		//synchronized(monitorX){
			if(zugAufGleis[lf.getGleis()] == null){
				zugAufGleis[lf.getGleis()] = lf.getZug();
			}// END IF
			else{
				//lfspeicher.add(lf);
				monitorX.wait();
			}// END ELSE
			//}// END SYNCRO
	}// END METHOD
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen, der
	 * einen Zug aus einem Rangiergleis ausfaehrt.
	 * Rangiergleis muss belegt sein,
	 * sonst wird der Lokfuehrer pausiert
	 * @param lf
	 */
	public synchronized void zugAusfahrenAuf(Lokfuehrer lf) throws InterruptedException{
		//synchronized(monitorX){
			if(zugAufGleis[lf.getGleis()] != null){
				lf.setZug(zugAufGleis[lf.getGleis()]);
				zugAufGleis[lf.getGleis()] = null;
			}// END IF
			else{
				//lfspeicher.add(lf);
				monitorX.wait();
			}// END ELSE
			//}// END SYNCRO
	}// END METHOD
	
}