package aufgabe3;



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
	
	private Zug[] gleisAnzahl;
	private int zugAnzahl;
	
	public Object monitorX = new Object();
	
	/*
	 * Konstruktor
	 */
	Rangierbahnhof(){
		gleisAnzahl = new Zug[3];
		zugAnzahl = 0;
	}
	
	/*
	 * Gibt die Anzahl der Rangiergleise zurueck
	 * @return gleisAnzahl.length
	 */
	public int getGleisAnzahl(){
		return gleisAnzahl.length;
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
	 * @param lf
	 */
	public void arbeiten(Lokfuehrer lf)throws InterruptedException{
		synchronized(monitorX){
		switch(lf.getAufgabe()){
		case 0: zugEinfahrenAuf(lf);
			System.out.println(lf.toString());
			break;
		case 1:zugAusfahrenAuf(lf);
			System.out.println(lf.toString());
			break;
		default: System.out.println("Error");
			lf.interrupt();
			break;
		}
		}
	}
	
	
	/*
	 * Faehrt einen Zug auf ein Rangiergleis ein
	 * Rangiergleis muss vorhanden und frei sein,
	 * sonst wird der Lokfuehrer pausiert
	 * @param lf
	 */
	//Zug zug, mit oder ohne?
	public void zugEinfahrenAuf(Lokfuehrer lf)throws InterruptedException{
		synchronized(monitorX){
			if(gleisAnzahl[lf.getGleis()] == null){
				gleisAnzahl[lf.getGleis()] = lf.getZug();
				zugAnzahl++;
			}// END IF
			else{
				lf.wait();
			}
		}// END SYNCRO
	}// END METHOD
	
	/*
	 * Faehrt einen Zug aus dem gegebenen Rangiergleis aus
	 * Rangiergleis muss vorhanden und belegt sein
	 * @param gleisNummer
	 */
	public void zugAusfahrenAuf(Lokfuehrer lf) throws InterruptedException{
		synchronized(monitorX){
			if(gleisAnzahl[lf.getGleis()] != null){
				lf.setZug(gleisAnzahl[lf.getGleis()]);
				gleisAnzahl[lf.getGleis()] = null;
				zugAnzahl--;
			}// END IF
			else{
				lf.wait();
			}
		}// END SYNCRO
	}// END METHOD
}
//<<<<<<< HEAD
//
//=======
//	
//	
//	public void arbeiten(Lokfuehrer lf) throws InterruptedException{
//		switch(lf.getAufgabe()){
//		case 0: zugEinfahrenAuf(lf.getZug(), lf.getGleis());
//			System.out.println(lf.toString());
//			break;
//		case 1:zugAusfahrenAuf(lf.getGleis());
//			System.out.println(lf.toString());
//			break;
//		default: System.out.println("Error");
//			break;
//		}
//	}
//>>>>>>> df2bc248e55b8a8e45039ceb57d69e7da6fafc43
//}
