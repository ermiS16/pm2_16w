package aufgabe2.threads;

//import java.util.Comparator;

public class Flugzeug extends Thread{
	
	private Flughafen flughafen;
	private String id;
	private int flugdauer;
	private int restzeit;
	private Status status;
	private int zeit;
	
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
		restzeit = zeit;
		this.zeit = zeit;
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
				+ " Zielort: " + flughafen.toString() + "| Flugdauer: " + flugdauer 
				+ "| Zeit: " + zeit + "| Status: " + status;
		return formatiert;
	}
	
	/*
	 * Setzt die Zeit
	 * @parem zeitwert
	 */
	public void setZeit(int zeitwert){
		//if(zeit - zeitwert >= 0){
		//zeit -= zeitwert;
		//}
		//zeit = flugdauer - zeitwert;
		zeit = zeitwert;
	}
	
	/*
	 * Gibt die Zeit zurueck
	 * @return zeit
	 */
	public int getZeit(){
		return zeit;
	}
	
	/*
	 * Git die Flugdauer zurueck
	 * @return flugdauer
	 */
	public int getFlugdauer(){
		return flugdauer;
	}	
}
