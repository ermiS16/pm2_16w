package aufgabe1.xml.solution;

import java.time.LocalDateTime;

public class Messung {
	private double wert;
	private LocalDateTime zeitstempel;
	
	/*
	 * Constructor
	 * @param inputWert - Messwert
	 * @param inputZeitstempel - Zeitpunkt
	 */
	public Messung(double inputWert, LocalDateTime inputZeitstempel){
		wert = inputWert;
		zeitstempel = inputZeitstempel;
	}
	
	
	public Messung(){
		wert = 0d;
		zeitstempel = null;
	}
	
	/*
	 * Setzt den Messwert
	 * @param inputWert - Messwert
	 */
	public void setWert(double inputWert){
		wert = inputWert;
	}
	
	/*
	 * Liefert den Messwert zurueck
	 * @return - Messwert
	 */
	public double getWert(){
		return wert;
	}
	
	/*
	 * Setzt den Zeitwert
	 * @param inputZeitstempel - Zeitpunkt
	 */
	public void setLocalDateTime(LocalDateTime inputZeitstempel){
		zeitstempel = inputZeitstempel;
	}
	
	public void setLocalDateTime(){
		zeitstempel = LocalDateTime.now();
	}
	
	/*
	 * Liefert den Zeitpunkt zurueck
	 * @return - Zeitpunkt
	 */
	public LocalDateTime getLocalDateTime(){
		return zeitstempel;
	}
	
	/*
	 * Liefert einen formatierten String der Messwert 
	 * und Zeitpunkt enthaelt
	 * @return - formatierter String
	 */
	@Override
	public String toString(){
		return "\nWert: " + getWert() + "\nZeitstempel: " + getLocalDateTime();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(wert);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((zeitstempel == null) ? 0 : zeitstempel.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Messung other = (Messung) obj;
		if (Double.doubleToLongBits(wert) != Double.doubleToLongBits(other.wert))
			return false;
		if (zeitstempel == null) {
			if (other.zeitstempel != null)
				return false;
		} else if (!zeitstempel.equals(other.zeitstempel))
			return false;
		return true;
	}
	
}
