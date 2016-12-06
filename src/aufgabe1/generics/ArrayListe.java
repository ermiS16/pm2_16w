package aufgabe1.generics;

/**
 * 
 * @author Eric Misfeld, Simon Felske
 * @version 18.10.2016
 *
 * Eigene ArrayList, die intern mit einem Array arbeitet
 */

public class ArrayListe<T> {
	private int anzahlElemente;
	private Object[] elemente;
	
	public ArrayListe(){
		anzahlElemente = 0;
		elemente = new Object[10]; //Anfangskapazitaet betraegt 10 Elemente
	}
	
	/*
	 * Konstruktor fuer vom Nutzer definierte Anfangskapazitaet
	 * @param initialKapazitaet - Arraygroesse
	 */
	public ArrayListe(int initialKapazitaet){
		if(initialKapazitaet > 0 && initialKapazitaet <= Integer.MAX_VALUE){
			anzahlElemente = 0;
			elemente = new Object[initialKapazitaet]; //Nutzerdefinierte Anfangskapazitaet
		}
	}
	
	/*
	 * Fuegt ein beliebiges Element dem Object Array hinzu
	 * @param obj - Object das hinzugefuegt wird
	 */
	//Array vergroeßern?
	public void hinzufuegen(T obj){
		//freie Position im Array finden
		int i = 0;
		while(elemente[i] != null && i < elemente.length){
			i++;
		}
		elemente[i] = obj;
		anzahlElemente++;
	}
	
	/*
	 * Liefert das Element einer bestimmten Position im Array zurueck.
	 * @param idx - Index des gesuchten Elementes
	 * @return elemente - Element am angegebenen Index
	 */
	@SuppressWarnings("unchecked") //unterdrueckt Compiler Warnung wg. moeglicher Fehler beim Casten
	public T get(int idx){
		return (T) elemente[idx];
	}
	
	/*
	 * Entfernt das erste Element im Array, 
	 * dass mit dem uebergebenen Element uebereinstimmt.
	 * Nachstehende Elemente ruecken auf.
	 * @param obj - zu entfernendes Element, sofern vorhanden
	 */
	//Array verkleinern?
	public void entfernen(T obj){
		if(obj != null){
			int i = 0;
			//Bis das erste passende Object gefunden wurde
			while(!elemente[i].equals((Object) obj)){
				i++;
			}
			//Position im Array ueberschreiben
			elemente[i] = null;
			anzahlElemente--;
			//Elemente aufruecken lassen
			for(int k = i; k < anzahlElemente;k++){
				elemente[k] = elemente[i+1];
				i++;
			}
			elemente[i] = null;
		}
	}
	
	/*
	 * Entfernt Element an der Position im Array
	 * Nachstehende Elemente ruecken auf
	 * Siehe ArrayList Interface
	 * @param idx - Index des zu entfernenden Elementes
	 */
	public void entferneElementAnIndex(int idx){
		//Element nur entfernen wenn Element an Index vorhanden
		if(elemente[idx] != null){
			elemente[idx] = null;
			for(int k = idx; k < anzahlElemente;k++){
				//restliche Elemente aufruecken lassen
				if((idx+1 < elemente.length)){
					elemente[k] = elemente[idx+1];
					elemente[idx+1] = null;
				}
				else{
					elemente[k] = null;
				}
				idx++;
			}//close for
			anzahlElemente--;
		}//close if
	}
	
	/*
	 * Liefert die Anzahl der Elemente im Array
	 * @return anzahlElemente - Elementanzahl
	 */
	public int getAnzahlElemente(){
		return anzahlElemente;
	}
	
	/*
	 * Liefert einen String aller Elemente 
	 * in ihrer Reihenfolge im Array zurueck
	 * @return ausgabe - formatierter String mit allen Elementen
	 */
	@Override
	public String toString(){
		String ausgabe = "\n";
		for(int i = 0; i < elemente.length; i++){
			ausgabe += "[" + String.valueOf(elemente[i]) + "]";
			if(i < (elemente.length - 1)){
				ausgabe += ", ";
			}
		}
		return (ausgabe + "\n");
	}
	
	
	/*
	 * Liefert das kleinste Element im Array zurueck.
	 * @return kleinstesElement - das kleinste Element im Array 
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends Comparable<T>> T getKleinstesElement(){
		T kleinstesElement = (T)elemente[0];
		if(anzahlElemente > 1){
			for(int i = 1; i < elemente.length; i++){
				if(elemente[i]!= null){
					if(kleinstesElement.compareTo((T)elemente[i]) == 1){
						kleinstesElement = (T)elemente[i];
					}//close if
				}//close if
			}//close for
		}//close if
		return kleinstesElement;
	}
	
}
