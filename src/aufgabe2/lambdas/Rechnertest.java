package aufgabe2.lambdas;

import static org.junit.Assert.*;

import org.junit.Test;

import aufgabe2.lambdas.Rechner.Operation;

public class Rechnertest {

	@Test
	public void testeBerechneAddition() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1+num2;
		
		Rechner rechner = new Rechner();
		assertEquals(5,rechner.berechne(Operation.ADD, num1, num2), result);
	}
	@Test
	public void testeBerechneSubtraktion() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1-num2;
		
		Rechner rechner = new Rechner();
		assertEquals(1,rechner.berechne(Operation.SUB, num1, num2), result);
	}
	@Test
	public void testeBerechneMultiplikation() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1*num2;
		
		Rechner rechner = new Rechner();
		assertEquals(6,rechner.berechne(Operation.MUL, num1, num2), result);
	}
	@Test
	public void testeBerechneDivision() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1/num2;
		
		Rechner rechner = new Rechner();
		assertEquals(1.5,rechner.berechne(Operation.DIV, num1, num2), result);
	}
	
	@Test
	public void testeBerechneDivisionDurch0() {
		double num1 = 0d;
		double num2 = 0d;
		double result = num1/num2;
		
		Rechner rechner = new Rechner();
		assertEquals(0.0,rechner.berechne(Operation.DIV, num1, num2), result);
	}

}
