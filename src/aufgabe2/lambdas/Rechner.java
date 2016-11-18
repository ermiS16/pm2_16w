package aufgabe2.lambdas;

import java.util.HashMap;
import java.util.function.*;

/**
 * Repraesentiert einen Rechner fuer
 * die vier Grundrechenarten
 * 
 * Praktikum TIPR2, WS2016/2017
 * Praktikumsgruppe Nr. 4
 * @author Eric Misfeld, Simon Felske
 * @version 18.11.2016
 * Aufgabenblatt 2 | Aufgabe 1
 */

public class Rechner {
	
	private HashMap<Operation, BinaryOperator<Double>> operationen;
	private BinaryOperator<Double> add;
	private BinaryOperator<Double> sub;
	private BinaryOperator<Double> mul;
	private BinaryOperator<Double> div;
	
	public enum Operation {ADD, SUB, MUL, DIV};
	
	
	/*
	 * Erstellt ein Rechnerobjekt
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
	}
	
	/*
	 * Fuert die vier Grundrechenarten auf zweil Zahlen des Typs "Double" aus
	 * Berechnung erfolgt nur wenn keine Division durch 0
	 * @param opcode - Wahlparameter fuer die Rechenoperation
	 * @param zahl1 - erster Zahlenwert
	 * @param zahl2 - zweiter Zahlenwert
	 * @return result - Rechenergebnis
	 */
	public double berechne(Operation opcode, double zahl1, double zahl2){
		double result;
		if(opcode == Operation.DIV && zahl2 == 0d){
			result = 0d;
		}
		else{
			result = operationen.get(opcode).apply(zahl1, zahl2);
		}
		return result;
	}
}
