package aufgabe2.threads;

import java.util.Comparator;

public class Flugzeug extends Thread implements Comparator<Flugzeug>{
	
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
		status = Status.IM_FLUG;
	}
	
	@Override
	public void run(){
		while(!isInterrupted() && status != Status.GELANDET){
			
		}
	}

	public void landeAnflug(int landeZeit){
		if(landeZeit == 1500){
			status = Status.GELANDET;
		}
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

	@Override
	public int compare(Flugzeug fz1, Flugzeug fz2) {
		int result = 0;
		
		if(fz1.getZeit() > fz2.getZeit()){
			result = 1;
		}
		if(fz1.getZeit() < fz2.getZeit()){
			result = -1;
		}
		if(fz1.getZeit() == fz2.getZeit()){
			result = 0;
		}
		return result;
	}
	
}
