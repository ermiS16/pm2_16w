package aufgabe2.threads;

import static org.junit.Assert.assertEquals;

import java.util.*;

public class Flughafen extends Thread{
	
	private final int warteZeit = 500;
	private final long dauerLandeAnflug = 1500;
	private List<Flugzeug> flugzeugListe;
	private int anzahlFlugzeuge;
	private int landeZeit;
	private int zeit;
	private String flughafenID;
	
	/*
	 * Erstellt ein Flughafen-Objekt
	 * @param flugzeuge
	 */
	public Flughafen(List<Flugzeug> flugzeuge){
		flugzeugListe = Collections.synchronizedList( new ArrayList<Flugzeug>());
		for(Flugzeug fz : flugzeuge){
			flugzeugListe.add(fz);
		}
		anzahlFlugzeuge = flugzeugListe.size();
		landeZeit = 0;
		zeit = 0;
	}
	
	/*
	 * Erstellt ein Flughafen-Objekt
	 */
	public Flughafen(){
		flugzeugListe = new ArrayList<Flugzeug>();
		anzahlFlugzeuge = flugzeugListe.size();
		landeZeit = 0;
		zeit = 0;
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
				//if(zeit%umrechnungsFaktor == 0){ //smoothing time
				landeZeit += warteZeit;
				if(zeit%umrechnungsFaktor == 0){ //smoothing time
					echteZeit = zeit/umrechnungsFaktor;
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
				neuesFlugzeug.interrupt();
			}//END IF
			
			synchronized(flugzeugListe){
				int syncroTimer = echteZeit;
				if(syncroTimer > syncroZeit){ //eine Aktion alle 2 Zyklen
					syncroZeit++;
					System.out.println("\nZeit: " + echteZeit);
					System.out.println("RealeZeit: " + zeit);
					for(int i = 0; i < flugzeugListe.size(); i++){
						flugzeugListe.get(i).setZeit(echteZeit);
						if(flugzeugListe.get(i).getZeit() == 0 && !landeBahnBelegt){
							landeBahnBelegt = true;
							try {
								landen(flugzeugListe.get(i));
							} catch (InterruptedException e) {
								e.printStackTrace();
								flugzeugListe.get(i).interrupt();
							}
							landeIndex = i;
						}//END IF
						else{
							System.out.println(flugzeugListe.get(i).toString());
						}//END ELSE
					}//END FOR
					if(landeIndex != -1 && landeZeit >= 1500){
						landeBahnBelegt = false;
						flugzeugListe.get(landeIndex).landung();
						System.out.println("Flugzeug gelandet: " + flugzeugListe.get(landeIndex).toString());
						flugzeugListe.get(landeIndex).interrupt();
						flugzeugListe.remove(landeIndex);
						landeZeit = 0;
						landeIndex = -1;
					}//END IF
				}//END IF SYNCROTIME
			}//END synced thing
		}// END WHILE
	}//END RUN
	
	
/*<<<<<<< HEAD
					}
					if (landeIndex != -1){
>>>>>>> c20f9165613378bea36a5418eb266ad1d19af073
						flugzeugListe.get(landeIndex).interrupt();
						flugzeugListe.remove(landeIndex);
					}
					
				}
			}
			
			synchronized(flugzeugListe){
				System.out.println("Zeit: " + echteZeit);
				for(Flugzeug flugzeug : flugzeugListe){
					//System.out.println("Zeit: " + echteZeit + "\n" + flugzeug.toString());
					//System.out.println(flugzeug.toString());
					flugzeug.setZeit(echteZeit);
					////flugzeug.setZeit(1);
					if(flugzeug.getZeit() >= flugzeug.getFlugdauer() && !landeBahnBelegt){
					//if(flugzeug.getZeit() <= flugzeug.getFlugdauer() && !landeBahnBelegt){
						landeBahnBelegt = true;
						//flugzeug.imLandeAnflug();
						//System.out.println(flugzeug.toString());
						//flugzeug.sleep(millis);
						landen(flugzeug);
						if(flugzeug.isGelandet()){
							landeBahnBelegt = false;
							System.out.println("Flugzeug gelandet: " + flugzeug.toString());
							flugzeug.interrupt();
							flugzeugListe.remove(flugzeug);
						}
					}
					else{
						System.out.println(flugzeug.toString());
					}
				}
				}
		}
	}
=======
						if (landeIndex != -1){
							flugzeugListe.remove(landeIndex);
						}//ENDIF
					}//ENDFOR
				}//ENDIF
			}//ENDSYNC
		}//ENDWHILE
	}//ENDRUN
>>>>>>> dev*/
	
	public synchronized void landen(Flugzeug fz) throws InterruptedException{
		fz.imLandeAnflug();
		System.out.println(fz.toString());
		landeZeit = 0;
		}
	
	/*
	 * Erstellt ein neues Flugzeug-Objekt
	 * @param currentTime - Startzeitpunkt
	 * @return neuesFlugzeug*
	 */
	public Flugzeug erzeugeFlugzeug(int currentTime){
		int zufall = (int) (Math.random() * 2);
		String luftHansa = "Lufthansa ";
		String airBerlin = "Air Berlin ";
		String germanWings = "German Wings ";
		int flugId = (int) ((Math.random() + 1) * 1000);
		int flugdauer = (int) (Math.random() * 10) + 1; //flugdauer darf nicht 0 sein
		Flughafen zielort = this;
		
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
	
	/*
	 * Erstellt einen String mit dem
	 * Namen des Flughafens
	 * @return - Name des Flughafens
	 */
	public String toString(){
		return "Generic International";
	}
	
	public static void main(String[] args){
		Flughafen fh = new Flughafen();
		List<Flugzeug> liste = new ArrayList<Flugzeug>();
		Flugzeug fz1 = new Flugzeug("Lufthansa 1", 2, fh, 0);
		Flugzeug fz2 = new Flugzeug("Air Berlin 1", 2, fh, 0);
		liste.add(fz1);
		liste.add(fz2);
		Thread flughafen = new Flughafen(liste);
		flughafen.start();
		System.out.println("Flughafen hat Betrieb aufgenommen");
		System.out.println("Zeit: 0");
		System.out.println("Flugzeug gestartet: " + fz1.toString());
		System.out.println("Flugzeug gestartet: " + fz2.toString());
		if(!flughafen.isAlive()){
			flughafen.interrupt();
			System.out.println("Flughafen hat den Betrieb eingestellt.");
		}
	}
}
