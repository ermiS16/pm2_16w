package aufgabe3;

import javafx.application.Application;

public class Anwendung {
	
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
