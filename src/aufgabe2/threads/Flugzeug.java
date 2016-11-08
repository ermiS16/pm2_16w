package aufgabe2.threads;

//import java.util.Comparator;

public class Flugzeug extends Thread{
	
	private Flughafen flughafen;
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
	public Flugzeug(String id, int flugdauer, Flughafen zielort, int startZeit){
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
		while(!isInterrupted() && status != Status.GELANDET){
			
		}
	}
	
	/*
	 * Aendert Status
	 */
	public void imLandeAnflug(){
		status = Status.IM_LANDEANFLUG;
	}
	
	/*
	 * Aendert Status
	 */
	public void landung(){
			status = Status.GELANDET;
	}
	
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
	 * notwendigen FLugzeuginformationen
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
	 * Setzt die Zeit
	 * @parem zeitwert
	 */
	public void setZeit(int zeitWert){
		if(status != Status.IM_LANDEANFLUG){
			restZeit = flugdauer - (Math.abs(startZeit - zeitWert));
		}
	}
	
	/*
	 * Gibt die Zeit zurueck
	 * @return zeit
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
