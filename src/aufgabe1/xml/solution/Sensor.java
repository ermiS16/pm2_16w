package aufgabe1.xml.solution;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
	private List<Messung> listeMessungen;
	@SuppressWarnings("unused")
	private Messung messung;
	private String id;
	
	/*
	 * Constructor
	 * @param inputMessung - Liste von Messungen
	 * @param inputID - ID zur Identifizierung des Sensors
	 */
	public Sensor(List<Messung> inputMessung, String inputId){
		listeMessungen = inputMessung;
		messung = null;
		id = inputId;
	}
	
	
	public Sensor(){
		listeMessungen = new ArrayList<Messung>();
		messung = null;
		id = "";
	}
	
	/*
	 * Fuegt eine neue Messung der Liste der Messungen hinzu
	 * @param inputWert - Messwert als Kommazahl
	 * @param inputZeitstempel - Zeitpunkt der Messung
	 */
	public void addMessung(double inputWert, String inputZeitstempel){
		Messung neueMessung = new Messung();
		neueMessung.setWert(inputWert);
		neueMessung.setLocalDateTime();
		listeMessungen.add(neueMessung);
	}
	
	/*
	 * Entfernt eine Messung am gegebenen Index
	 * aus der Liste der Messungen
	 * @param idx - Index der zu entfernenden Messung
	 */
	public void removeMessung(int idx){
		listeMessungen.remove(idx);
	}
	
	/*
	 * Liefert die Messung an gegebenem Index in 
	 * der Liste von Messungen zurueck
	 * @param idx - Index der gewuenschten Messung in der Liste der Messungen
	 * @return - Messung an Index
	 */
	public Messung getMessung(int idx){
		return listeMessungen.get(idx);
	}
	
	/*
	 * @return - liefert eine Liste von Messungen (Messwerten) zurueck
	 */
	public List<Messung> getListeMessung(){
		return listeMessungen;
	}
	
	/*
	 * @return - liefert die Sensor-ID zurueck
	 */
	public String getId(){
		return id;
	}
	
	/*
	 * Fuer einen beliebigen Sensor wird ein formatierter String
	 * erstellt, der alle Messungen und die Sensor-ID enthaelt
	 * @return - formatierter String
	 */
	@Override
	public String toString(){
		String result = "\n----------\nSensor ID: " + id 
				+ "\nMessungen: \n----------";
				for(Messung messung : listeMessungen){
					result += messung.toString();
				}
		
		return result;
	}
}
