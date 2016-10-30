package aufgabe2.lambdas;

import java.util.HashMap;
import java.util.function.*;

public class Rechner{
	
	private HashMap<Operation, BinaryOperator<Double>> operationen;
	private BinaryOperator<Double> add;
	private BinaryOperator<Double> sub;
	private BinaryOperator<Double> mul;
	private BinaryOperator<Double> div;
	public enum Operation {ADD, SUB, MUL, DIV};
	
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
	
	public double berechne(Operation opcode, double zahl1, double zahl2){
		double result = 0d;
		return result = operationen.get(opcode).apply(zahl1, zahl2);
		
		/*
		switch(opcode){
		case ADD: result = operationen.get(opcode).apply(zahl1, zahl2);
			break;
		case SUB: result = operationen.get(opcode).apply(zahl1, zahl2); 
			break;
		case MUL: result = operationen.get(opcode).apply(zahl1, zahl2);
			break;
		case DIV: result = operationen.get(opcode).apply(zahl1, zahl2);
			break;
		default: break;
		}
		*/	
	}
}
