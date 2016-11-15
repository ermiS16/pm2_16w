package aufgabe2.threads;

/**
 * Repraesentiert ein Flugzeug, dass als 
 * Thread ueber den Flughafen gestartet wird
 * @author Eric Misfeld, Simon Felske
 * @version 08.11.2016
 *
 */

public class Flugzeug extends Thread{
	
	private Flughafen2 flughafen;
	private String id;
	private int flugdauer;
	private int restZeit;
	private Status status;
	private int startZeit;
	
	/*
	 * Erstellt ein neues Flugzeug-Objekt
	 * @param id - ID des Flugzeuges aka. Name des Flugzeuges
	 * @param flugdauer - Flugdauer. Muss groesser als 1 sein
	 * @param zielort - Zielflughafen fuer das Flugzeug
	 * @param startZeit - Zeitpunkt des Flugbegins. Muss >= 0 sein
	 */
	public Flugzeug(String id, int flugdauer, Flughafen2 zielort, int startZeit){
		if(flugdauer >= 1 && startZeit >= 0){
			this.id = id;
			this.flugdauer = flugdauer;
			flughafen = zielort;
			restZeit = flugdauer;
			this.startZeit = startZeit;
			status = Status.IM_FLUG;
		}
	}
	
	@Override
	public void run(){
		while(!isInterrupted()){
			
		}
	}
	
	/*
	 * Aendert den Status des Flugzeuges
	 * @param statusAenderung
	 */
	public void setStatus(Status statusAenderung){
		switch(statusAenderung){
		case IM_FLUG:
			status = Status.IM_FLUG;
			break;
		case IM_LANDEANFLUG:
			status = Status.IM_LANDEANFLUG;
			break;
		case IN_WARTESCHLEIFE:
			status = Status.IN_WARTESCHLEIFE;
			break;
		case GELANDET:
			status = Status.GELANDET;
			break;
		default:
			//this.status = status;
			break;
		}
	}
	
	/*
	 * Aendert Status
	 */
	//public void imLandeAnflug(){
	//status = Status.IM_LANDEANFLUG;
	//}
	
	/*
	 * Aendert Status
	 */
	//public void landung(){
	//	status = Status.GELANDET;
	//}
	
	/*
	 * Landevorgang abgeschlossen?
	 * @return amBoden 
	 */
	public boolean isGelandet(){
		boolean amBoden = false;
		if(status == Status.GELANDET){
			amBoden = true;
		}
		return amBoden;
	}
	
	/*
	 * Erstellt einen formatierten String mit allen 
	 * notwendigen Flugzeuginformationen
	 * @return formatiert
	 */
	public String toString(){
		String formatiert = "";
		formatiert += "Flugzeug-ID: " + id 
				+ " | Zielort: " + flughafen.toString() + " | Flugdauer: " + flugdauer 
				+ " | Restzeit: " + restZeit + " | Status: " + status;
		return formatiert;
	}
	
	/*
	 * Setzt die aktuelle Zeit und
	 * aktualisiert die verbleibende Flugzeit
	 * @parem zeitWert
	 */
	public void setZeit(int zeitWert){
		if(status != Status.IM_LANDEANFLUG && (flugdauer - (Math.abs(startZeit - zeitWert))) >= 0){
			restZeit = flugdauer - (Math.abs(startZeit - zeitWert));
		}
		else{
			restZeit = 0;
		}
	}
	
	/*
	 * Gibt die verbleibende Flugzeit zurueck
	 * @return restZeit
	 */
	public int getZeit(){
		return restZeit;
	}
	
	/*
	 * Git die Flugdauer zurueck
	 * @return flugdauer
	 */
	public int getFlugdauer(){
		return flugdauer;
	}	
}
