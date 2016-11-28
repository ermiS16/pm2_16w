package aufgabe3;

/**
* Anwendung fuer Aufgabenblatt 3
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 28.11.2016
* Aufgabenblatt 3 | Aufgabe 2
*/

import javafx.application.Application;

public class Anwendung {
	
	/*
	 * MAIN
	 */
	public static void main(String[] args){
		Application.launch(Gui.class);
	}
	
/*	
	public static void main(String args[]){
		//Rangierbahnhof tbhf = new Rangierbahnhof();
		//Lokfuehrer tlf = new Lokfuehrer(0,1,tbhf);
		//Lokfuehrer tlf2 = new Lokfuehrer(0,2,tbhf);
		//Lokfuehrer tlf3 = new Lokfuehrer(1,2,tbhf);
		//Lokfuehrer tlf4 = new Lokfuehrer(0,1,tbhf);
		//tbhf.start();
		//tlf.start();
		//tlf2.start();
		//tlf3.start();
		//tlf4.start();
		//wait(20);
		//System.out.println(tbhf.getZugAnzahl());
		// ACHTUNG AUSKOMMENTIERT
//		Thread test = new Thread(new Simulation());
		Gui.launch(args);
//		test.start();
	}
	*/
}
