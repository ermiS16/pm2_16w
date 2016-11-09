package aufgabe2.threads;

import java.util.ArrayList;
import java.util.List;

public class Flughafen2 extends Thread{
	private final int warteZeit;
	private List<Flugzeug> flugzeugListe;
	private int anzahlFlugzeuge;
	
	public Flughafen2(List<Flugzeug> inputFlugzeugListe){
		warteZeit = 500;
		flugzeugListe = inputFlugzeugListe;
		anzahlFlugzeuge = 0;
	}
	
	public Flughafen2(){
		warteZeit = 500;
		flugzeugListe = new ArrayList<Flugzeug>();
		anzahlFlugzeuge = 0;
	}
	
	@Override
	public void run(){
		int landeIndex = -1;
		int zeit = 0;
		int umrechnungsFaktor = 1000;
		int echteZeit = 0;
		int syncroZeit = 0;
		boolean landeBahnBelegt = false;
		
		while(!isInterrupted() && true){
			try {
				Flughafen.sleep(warteZeit);
				zeit += warteZeit;
				if(zeit%umrechnungsFaktor == 0){ //smoothing time
					echteZeit = zeit/umrechnungsFaktor;
					System.out.println("\nZeit: " + echteZeit);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupt();
			}
			if(anzahlFlugzeuge > flugzeugListe.size()){
				Flugzeug neuesFlugzeug = erzeugeFlugzeug(echteZeit);
				flugzeugListe.add(neuesFlugzeug);
				System.out.println("Flugzeug erzeugt: " + neuesFlugzeug.toString());
				neuesFlugzeug.start();
			}//END IF
			for(int i = 0; i<flugzeugListe.size();i++){
				flugzeugListe.get(i).setZeit(echteZeit);
			}
			
		}
	}
	
	public Flugzeug erzeugeFlugzeug(int currentTime){
		int zufall = (int) (Math.random() * 2);
		String luftHansa = "Lufthansa ";
		String airBerlin = "Air Berlin ";
		String germanWings = "German Wings ";
		int flugId = (int) ((Math.random() + 1) * 1000);
		int flugdauer = (int) (Math.random() * 10) + 1; //flugdauer darf nicht 0 sein
		Flughafen2 zielort = this;
		
		switch(zufall){
		case 0:	Flugzeug neuesFlugzeug = new Flugzeug(luftHansa+flugId, flugdauer, zielort, currentTime);
				return neuesFlugzeug;
		case 1: Flugzeug neuesFlugzeug2 = new Flugzeug(airBerlin+flugId, flugdauer, zielort, currentTime);
				return neuesFlugzeug2;
		case 2: Flugzeug neuesFlugzeug3 = new Flugzeug(germanWings+flugId, flugdauer, zielort, currentTime);
				return neuesFlugzeug3;
		default: Flugzeug neuesFlugzeugDefault 
					= new Flugzeug("Unbekannter Flug", flugdauer, zielort, currentTime);
				return neuesFlugzeugDefault;
		}// END SWITCH
	}// END METHOD

	public static void main(String[] args){
		Thread flughafen = new Thread(new Flughafen());
		flughafen.start();
	}
	
}