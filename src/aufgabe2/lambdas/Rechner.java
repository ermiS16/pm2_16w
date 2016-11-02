package aufgabe2.lambdas;

import java.util.HashMap;
import java.util.function.*;

public class Rechner implements DoubleDoubleZuDouble{
	
	private HashMap<Operation, BinaryOperator<Double>> operationen;
	private BinaryOperator<Double> add;
	private BinaryOperator<Double> sub;
	private BinaryOperator<Double> mul;
	private BinaryOperator<Double> div;
	private DoubleDoubleZuDouble nullstelle;
	private DoubleDoubleZuDouble multi;
	public enum Operation {ADD, SUB, MUL, DIV};
	
	
	/*
	 * Erstellt ein Rechnerobject
	 * Legt eine neue Hashmap an
	 */
	public Rechner(){
		operationen = new HashMap<Operation,BinaryOperator<Double>>();
		add = (num1, num2) -> {return num1 + num2;};
		sub = (num1, num2) -> {return num1 - num2;};
		mul = (num1, num2) -> {return num1 * num2;};
		div = (num1, num2) -> {return num1 / num2;};
		operationen.put(Operation.ADD, add);
		operationen.put(Operation.SUB, sub);
		operationen.put(Operation.MUL, mul);
		operationen.put(Operation.DIV, div);
		nullstelle = (x,y) -> {return y = (1*x)+0;};
		multi = (x,y) -> {return x*y;};
	}
	
	/*
	 * Fuert die vier Grundrechenarten auf zweil Zahlen des Typs "Double" aus
	 * Berechnung erfolgt nur wenn sinnvoll
	 * @param opcode - Wahlparameter fuer die Rechenoperation
	 * @param zahl1 - erster Zahlenwert
	 * @param zahl2 - zweiter Zahlenwert
	 * @return result - Rechenergebnis
	 */
	public double berechne(Operation opcode, double zahl1, double zahl2){

		double result;
		/*
		if (zahl1 == 0d && zahl2 == 0d || opcode == Operation.DIV && zahl2 == 0d){
			result = 0d;
		}*/ 
		//Wir brauchen NUR die Division durch 0 auszuschließen.
		
		if(opcode == Operation.DIV && zahl2 == 0d){
			result = 0d;
		}
		else{
			result = operationen.get(opcode).apply(zahl1, zahl2);
		}
		return result;
	}
	
	@Override
	public double werteAus(double num1, double num2){		
		double result = 0d;
		return result;
	}
}
