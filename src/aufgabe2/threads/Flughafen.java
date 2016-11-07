package aufgabe2.threads;

import java.util.*;

public class Flughafen extends Thread{
	
	private final int warteZeit;
	private final int dauerLandeAnflug;
	private List<Flugzeug> flugzeugListe;
	private int anzahlFlugzeuge;
	private int landeZeit;
	private int zeit;
	private String flughafenID;
	private final List<Flugzeug> puffer;
	private int pufferElemente;
	
	public Flughafen(List<Flugzeug> flugzeuge){
		flugzeugListe = Collections.synchronizedList( new ArrayList<Flugzeug>());
		for(Flugzeug fz : flugzeuge){
			flugzeugListe.add(fz);
		}
		anzahlFlugzeuge = flugzeugListe.size();
		warteZeit = 500;
		dauerLandeAnflug = 1500;
		landeZeit = 0;
		zeit = 0;
		pufferElemente = 0;
		puffer = new ArrayList<Flugzeug>();
		for(int i=0; i<flugzeugListe.size();i++){
			puffer.add(null);
		}
	}
	public Flughafen(){
		flugzeugListe = new ArrayList<Flugzeug>();
		anzahlFlugzeuge = flugzeugListe.size();
		warteZeit = 500;
		dauerLandeAnflug = 1500;
		landeZeit = 0;
		zeit = 0;
		pufferElemente = 0;
		puffer = new ArrayList<Flugzeug>();
		for(int i=0; i<flugzeugListe.size();i++){
			puffer.add(null);
		}
	}
	
	@Override
	public void run(){
		int zeit = 0;
		int umrechnungsFaktor = 1000;
		int echteZeit = 0;
		boolean landeBahnBelegt = false;
		while(pufferElemente == puffer.size()){
			try{
				this.wait();
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
				return;
			}
		}
		while(!isInterrupted() && true){
			try {
				Flughafen.sleep(warteZeit);
				zeit += warteZeit;
				echteZeit = zeit/umrechnungsFaktor;
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupt();
			}
			
			if(anzahlFlugzeuge > flugzeugListe.size()){
				Flugzeug neuesFlugzeug = erzeugeFlugzeug();
				flugzeugListe.add(neuesFlugzeug);
				System.out.println("Flugzeug erzeugt: " + neuesFlugzeug.toString());
				neuesFlugzeug.start();
				neuesFlugzeug.interrupt();
			}
			
			synchronized(flugzeugListe){
			for(Flugzeug flugzeug : flugzeugListe){
				System.out.println("Zeit: " + echteZeit + "\n" + flugzeug.toString());
				flugzeug.setZeit(echteZeit);
				if(flugzeug.getZeit() <= flugzeug.getFlugdauer() && !landeBahnBelegt){
					landeBahnBelegt = true;
					landen(flugzeug);
					if(flugzeug.isGelandet()){
						landeBahnBelegt = false;
						System.out.println("Flugzeug gelandet: " + flugzeug.toString());
						flugzeug.interrupt();
						flugzeugListe.remove(flugzeug);
					}
				}
			}
			}
		}
	}
	
	public synchronized void landen(Flugzeug fz){
		while(landeZeit < dauerLandeAnflug){
			fz.imLandeAnflug();
			landeZeit += warteZeit;
		}
		fz.landung();
		
	}
	
	public Flugzeug erzeugeFlugzeug(){
		int zufall = (int) (Math.random() * 2);
		String luftHansa = "Lufthansa ";
		String airBerlin = "Air Berlin ";
		String germanWings = "German Wings ";
		int flugId = (int) ((Math.random() + 1) * 1000);
		int flugdauer = (int) (Math.random() * 20);
		Flughafen zielort = this;
		
		switch(zufall){
		case 0:	Flugzeug neuesFlugzeug = new Flugzeug(luftHansa+flugId, flugdauer, zielort, zeit);
				return neuesFlugzeug;
		case 1: Flugzeug neuesFlugzeug2 = new Flugzeug(airBerlin+flugId, flugdauer, zielort, zeit);
				return neuesFlugzeug2;
		case 2: Flugzeug neuesFlugzeug3 = new Flugzeug(germanWings+flugId, flugdauer, zielort, zeit);
				return neuesFlugzeug3;
		default: Flugzeug neuesFlugzeugDefault 
					= new Flugzeug("Unbekannter Flug", flugdauer, zielort, zeit);
				return neuesFlugzeugDefault;
		}
	}
	
	public static void main(String[] args){
		Flughafen fh = new Flughafen();
		List<Flugzeug> liste = new ArrayList<Flugzeug>();
		Flugzeug fz1 = new Flugzeug("Lufthansa 1", 2, fh, 3);
		Flugzeug fz2 = new Flugzeug("Air Berlin", 4, fh, 5);
		liste.add(fz1);
		liste.add(fz2);
		Thread flughafen = new Flughafen(liste);
		flughafen.start();
		System.out.println("Flughafen hat Betrieb aufgenommen");
		if(!flughafen.isAlive()){
			flughafen.interrupt();
			System.out.println("Flughafen hat den Betrieb eingestellt.");
		}
	}
}
