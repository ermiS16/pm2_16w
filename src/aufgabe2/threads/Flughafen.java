package aufgabe2.threads;

import java.util.*;

public class Flughafen extends Thread{
	
	private List<Flugzeug> flugzeugListe;
	private int anzahlFlugzeuge;
	
	public Flughafen(Flugzeug...flugzeuge){
		flugzeugListe = new ArrayList<Flugzeug>();
		for(Flugzeug fz : flugzeuge){
			flugzeugListe.add(fz);
		}
		anzahlFlugzeuge = flugzeugListe.size();
		
	}
	public Flughafen(){
		flugzeugListe = new ArrayList<Flugzeug>();
		anzahlFlugzeuge = flugzeugListe.size();
	}
	
	@Override
	public void run(){
		int zeitIntervall = 500;
		int dauerLandeAnflug = 1500;
		int zeit = 0;
		int umrechnungsFaktor = 1000;
		int landeZeit = 0;
		boolean landeBahnBelegt = false;
		
		while(!isInterrupted() && true){
			try {
				Flughafen.sleep(zeitIntervall);
				zeit += zeitIntervall;
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupt();
			}
			
			for(Flugzeug flugzeug : flugzeugListe){
				flugzeug.setZeit(zeit/umrechnungsFaktor);
				if(flugzeug.getZeit() < 1 && !landeBahnBelegt){
					landeBahnBelegt = true;
					landen(flugzeug);
					if(flugzeug.isGelandet()){
						landeBahnBelegt = false;
						flugzeug.interrupt();
						flugzeugListe.remove(flugzeug);
					}
				}
			}
			
			Flugzeug neuesFlugzeug = erzeugeFlugzeug();
			flugzeugListe.add(neuesFlugzeug);
			neuesFlugzeug.start();
		}
	}
	
	public synchronized void landen(Flugzeug fz){
		
	}
	
	public Flugzeug erzeugeFlugzeug(){
		int zufall = (int) (Math.random() * 2);
		String luftHansa = "Lufthansa ";
		String airBerlin = "Air Berlin ";
		int flugId = (int) ((Math.random() + 1) * 1000);
		int flugdauer = (int) (Math.random() * 50);
		Flughafen zielort = this;
		int zeit = (int) (Math.random() * 100);
		
		switch(zufall){
		case 1:	Flugzeug neuesFlugzeug = new Flugzeug(luftHansa+flugId, flugdauer, zielort, zeit);
				return neuesFlugzeug;
		case 2: Flugzeug neuesFlugzeug2 = new Flugzeug(airBerlin+flugId, flugdauer, zielort, zeit);
				return neuesFlugzeug2;
		default: Flugzeug neuesFlugzeugDefault 
					= new Flugzeug("Unbekannter Flug", flugdauer, zielort, zeit);
				return neuesFlugzeugDefault;
		}
	}
	
	public static void main(String[] args){
		Thread flughafen = new Flughafen();
		flughafen.start();
		flughafen.interrupt();
	}
}
