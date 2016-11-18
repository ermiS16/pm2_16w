package aufgabe2.threads;

/**
 * Repraesentiert ein Flugzeug, dass als 
 * Thread ueber den Flughafen gestartet wird
 * 
 * @author Eric Misfeld, Simon Felske
 * @version 18.11.2016
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
			try{
				if(restZeit == 0){
					flughafen.landen(this);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Aendert den Status des Flugzeuges
	 * @param statusAenderung - darf nicht Status.IM_FLUG sein
	 */
	public void setStatus(Status statusAenderung){
		switch(statusAenderung){
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
			break;
		}
	}
	
	/*
	 * Erstellt einen formatierten String mit allen 
	 * vorhandenen Flugzeuginformationen
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
	 * aktualisiert die verbleibende Flugzeit.
	 * Aendert den Status wenn die Landung
	 * nicht zeitgerecht eingeleitet werden kann
	 * @parem zeitWert
	 */
	public void setZeit(int zeitWert){
		if(status != Status.IM_LANDEANFLUG && (flugdauer - (Math.abs(startZeit - zeitWert))) >= 0){
			restZeit = flugdauer - (Math.abs(startZeit - zeitWert));
		}
		else{
			restZeit = 0;
			if(status == Status.IM_FLUG){
				status = Status.IN_WARTESCHLEIFE;
			}
		}
	}
}
