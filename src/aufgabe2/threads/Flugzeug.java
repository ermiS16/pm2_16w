package aufgabe2.threads;

//import java.util.Comparator;

public class Flugzeug extends Thread{
	
	private Flughafen flughafen;
	private String id;
	private int flugdauer;
	private int restzeit;
	private Status status;
	private int startzeit;
	
	/*
	 * Erstellt ein neues Flugzeug-Objekt
	 * @param id - ID des Flugzeuges aka. Name des Flugzeuges
	 * @param flugdauer - Flugdauer
	 * @param zielort - Zielflughafen fuer das Flugzeug
	 * @param zeit - Zeit
	 */
	public Flugzeug(String id, int flugdauer, Flughafen zielort, int zeit){
		this.id = id;
		this.flugdauer = flugdauer;
		flughafen = zielort;
		//restzeit = zeit;
		restzeit = flugdauer;
		startzeit = zeit;
		//this.zeit = flugdauer;
		status = Status.IM_FLUG;
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
				+ " | Restzeit: " + restzeit + " | Status: " + status;
		return formatiert;
	}
	
	/*
	 * Setzt die Zeit
	 * @parem zeitwert
	 */
	public void setZeit(int zeitwert){
		restzeit = flugdauer - (Math.abs(zeitwert - startzeit));
		//if(zeit - zeitwert >= 0){
		//zeit -= zeitwert;
		//}
		//zeit = flugdauer - zeitwert;
		//if( restzeit > 0){
		//int x = zeitwert - startzeit;
			//restzeit = flugdauer - x;
		//restzeit -= x;
		//}
	}
	
	/*
	 * Gibt die Zeit zurueck
	 * @return zeit
	 */
	public int getZeit(){
		return restzeit;
	}
	
	/*
	 * Git die Flugdauer zurueck
	 * @return flugdauer
	 */
	public int getFlugdauer(){
		return flugdauer;
	}	
}
