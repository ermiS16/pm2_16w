package aufgabe2.streams;

public class Stringverarbeitung {

	String[] standardwerte = {"Eingabe ","�u�eres  ", null, "Strassen-Feger", " ein Haus"};
	String[] nutzerwerte;
	
	Stringverarbeitung(){
	}
	
	Stringverarbeitung (String... nutzereingabe){
		nutzerwerte = new String[nutzereingabe.length];
		for (int i = 0; i < nutzereingabe.length; i++){
			nutzerwerte[i] = nutzereingabe[i];
		}
	}
}
