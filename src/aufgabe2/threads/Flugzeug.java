package aufgabe2.threads;

public class Flugzeug {
	
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

	public void istGelandet(){
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
		
	}
	
}
