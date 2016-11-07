package aufgabe2.threads;

import java.util.Comparator;

public class Flugzeug extends Thread{
	
	private Flughafen flughafen;
	private String id;
	private int flugdauer;
	private int startzeit;
	private Status status;
	private int zeit;
	
	public Flugzeug(String id, int flugdauer, Flughafen zielort, int zeit){
		this.id = id;
		this.flugdauer = flugdauer;
		flughafen = zielort;
		startzeit = zeit;
		this.zeit = zeit;
		status = Status.IM_FLUG;
	}
	
	@Override
	public void run(){
		while(!isInterrupted() && status != Status.GELANDET){
			
		}
	}
	
	public void imLandeAnflug(){
		status = Status.IM_LANDEANFLUG;
	}
	public void landung(){
			status = Status.GELANDET;
	}
	
	public boolean isGelandet(){
		boolean amBoden = false;
		if(status == Status.GELANDET){
			amBoden = true;
		}
		return amBoden;
	}
	
	public String toString(){
		String formatiert = "";
		formatiert += "Flugzeug-ID: " + id + " Zielort: " + flughafen + " Flugdauer: " + flugdauer + " Zeit: " + startzeit + " Status: " + status;
		return formatiert;
	}
	
	public void setZeit(int zeitwert){
		zeit = zeitwert;
	}
	public int getZeit(){
		return zeit;
	}
	public int getFlugdauer(){
		return flugdauer;
	}	
}
