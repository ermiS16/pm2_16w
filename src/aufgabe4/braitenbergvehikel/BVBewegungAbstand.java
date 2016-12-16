package aufgabe4.braitenbergvehikel;

/**
 * Attraktion zum Signal,
 * bis eine spez. Naehe zum Signal erreicht ist.
 * 
 * @author Simon Felske, Eric Misfeld
 */

public class BVBewegungAbstand implements BVBewegung {
	
	public static final String ID = "ABSTAND";
	
	//DOES NOTHING DIFFERENT THAN "ATTRAKTION" AT THE MOMENT

	@Override
	public double berechneMotorAnsteuerungLinks(double sensorWertLinks,
			double sensorWertRechts){
		return sensorWertRechts * sensorWertRechts;
	}

	@Override
	public double berechneMotorAnsteuerungRechts(double sensorWertLinks,
			double sensorWertRechts) {
		return sensorWertLinks * sensorWertLinks;
	}

	@Override
	public String getId() {
		return ID;
	}
}