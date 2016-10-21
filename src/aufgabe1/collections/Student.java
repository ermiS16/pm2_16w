package aufgabe1.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

	/**
	 * Klasse repraesentiert einen Studenten.
	 * @author Eric Misfeld, Simon Felske
	 * @version 17.10.2016
	 */
	
	public class Student implements Comparable<Student>, Comparator<Student>{
		private String name;
		private String vorname;
		private int matrikelnummer;
		private List<Pruefungsleistung> leistung;
		
		public Student(){
			name = "";
			vorname = "";
			matrikelnummer = 0;
			leistung = new ArrayList<Pruefungsleistung>();
		}
		
		/*
		 * Erstellt ein neues Studentenobject
		 * @param inputName
		 * @param inputVorname
		 * @param inputMatrikel
		 * @param inputLeistung
		 */
		public Student(String inputName, String inputVorname, int inputMatrikel, Pruefungsleistung... inputLeistung){
			//Uebergebener Name darf nicht leer, NULL oder < 1 sein
			if(inputName != "" && inputName != null && inputName.trim().length() > 1){
				name = inputName.trim();
			}
			else{
				name = "Namensfehler";
			}
			//Uebergebener Vorname darf nicht leer, NULL oder < 1 sein 
			if(inputVorname != "" && inputVorname != null && inputVorname.trim().length() > 1){
				vorname = inputVorname.trim();
			}
			else{
				vorname = "Vornamensfehler";
			}
			//Matrikelnummer muss sieben Stellen haben
			if (inputMatrikel > 1000000 && inputMatrikel < 9999998){
				matrikelnummer = inputMatrikel;
			}
			else{
				matrikelnummer = 0;
			}
			//Pruefungsleistung per VarArgs
			leistung = new ArrayList<Pruefungsleistung>(inputLeistung.length);
			for(int i = 0; i < inputLeistung.length; i++){
				leistung.add(inputLeistung[i]);
			}
		}
		
		/*
		 * Erstellt den Hashcode
		 * @return result
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + matrikelnummer;
			return result;
		}
		


		/*
		* Prueft ob das uebergebene Objekt ein Student ist,
		* wenn ja prueft ob die Matrikelnummer identisch ist
		* @param obj - beliebiges, zum Vergleich uebergebenes Object
		* @return wahrheitswert
		*/
		@Override
		public boolean equals(Object obj){
			boolean wahrheitswert = false;
			if (obj instanceof Student == true){
				Student q = (Student) obj;
				if (matrikelnummer == q.getMatrikelnummer()){
					wahrheitswert = true;
				}
			}
			return wahrheitswert;
		}
		
		//@Override
		//	public boolean equals(Object obj) {
			//	if (this == obj){
				//		return true;
				//	}	
			//	if (obj == null){
				//	return false;
				//}
			//if (getClass() != obj.getClass()){
				//	return false;
				//}
			//Student other = (Student) obj;
			//if (matrikelnummer != other.matrikelnummer){
				//	return false;
				//}
			//return true;
			//}
		


		/*
		 * Liefert einen formatierten String zurueck
		 * @return String
		 */
		@Override
		public String toString(){
			return "Name: " + name + ", " 
					+ "Vorname: " + vorname
					+ "\n" + "Matrikelnummer: " + matrikelnummer 
					+ "\n" + leistung.toString()
					+ "\n";
		}
		
		/*
		 * Liefert den Namen des Studenten zurueck
		 * @return name
		 */
		public String getName(){
			return name;
		}
		
		/*
		 * Liefer den Vornamen des Studenten zurueck
		 * @return vorname
		 */
		public String getVorname(){
			return vorname;
		}
		


		/*
		 * Liefert die Matrikelnummer des Studenten zurueck
		 * @return matrikelnummer
		 */
		public int getMatrikelnummer(){
			return matrikelnummer;
		}
		

		/*
		* Vergleicht die Matrikelnummer von zwei Studenten auf relative Groesse
		* @param x - beliebiger Student
		* @return ergebnis
		*/
		public int compareTo(Student x){
			int ergebnis = 0;
			if(matrikelnummer < x.getMatrikelnummer()){
				ergebnis = -1; 
			}
			if(matrikelnummer > x.getMatrikelnummer()){
				ergebnis = 1;
			}
			return ergebnis;
		}
		
		
		/*
		* Vergleicht den Namen, Vornamen von zwei Studenten auf relative Groesse
		* @param student1
		* @param student2
		* @return
		*/
		public int compare(Student student1, Student student2){
			return (student1.getName()+student1.getVorname()).compareTo((student2.getName()+student2.getVorname()));
		}
		
	}
	