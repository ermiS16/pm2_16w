package aufgabe2.lambdas;

import static org.junit.Assert.*;
import org.junit.Test;
import aufgabe2.lambdas.Rechner.Operation;

/**
 * Testklasse fuer "Rechner" und "DoubleDoubleZuDouble"
 * 
 * Praktikum TIPR2, WS2016/2017
 * Praktikumsgruppe Nr. 4
 * @author Eric Misfeld, Simon Felske
 * @version 18.11.2016
 * Aufgabenblatt 2 | Aufgabe 1
 */

public class Rechnertest {

	@Test
	public void testeBerechneAddition() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1+num2;
		
		Rechner rechner = new Rechner();
		assertEquals(result,rechner.berechne(Operation.ADD, num1, num2), result);
	}
	
	@Test
	public void testeBerechneSubtraktion() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1-num2;
		
		Rechner rechner = new Rechner();
		assertEquals(result,rechner.berechne(Operation.SUB, num1, num2), result);
	}
	
	@Test
	public void testeBerechneMultiplikation() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1*num2;
		
		Rechner rechner = new Rechner();
		assertEquals(result,rechner.berechne(Operation.MUL, num1, num2), result);
	}
	
	@Test
	public void testeBerechneDivision() {
		double num1 = 3d;
		double num2 = 2d;
		double result = num1/num2;
		
		Rechner rechner = new Rechner();
		assertEquals(result,rechner.berechne(Operation.DIV, num1, num2), result);
	}
	
	@Test
	public void testeBerechneDivisionDurch0_1() {
		double num1 = 0d;
		double num2 = 0d;
		double result = 0d;
		
		Rechner rechner = new Rechner();
		assertEquals(result,rechner.berechne(Operation.DIV, num1, num2), result);
	}
	
	@Test
	public void testeBerechneDivisionDurch0_2() {
		double num1 = 0d;
		double num2 = 1d;
		double result = num1/num2;
		
		Rechner rechner = new Rechner();
		assertEquals(result,rechner.berechne(Operation.DIV, num1, num2), result);
	}
	
	@Test
	public void testeDoubleDoubleZuDoubleMulti(){
		double num1 = 2d;
		double num2 = 3d;
		double num3 = 5d;
		double num4 = 6d;
		DoubleDoubleZuDouble multi = (x,y) -> {return x*y;};
		double result = num1*num2;
		double result2 = num3*num4;
		
		assertEquals(result, multi.werteAus(num1, num2), result);
		System.out.println("Multiplikation: " + multi.werteAus(num1, num2));
		
		assertEquals(result2, multi.werteAus(num3, num4), result2);
		System.out.println("Multiplikation: " + multi.werteAus(num3, num4));
	}
	
	@Test
	public void testeDoubleDoubleZuDoubleNullstelle(){
		double num1 = 2d;
		double num2 = 0;
		int linearFactor = 1;
		int konstante = 2;
		double num3 = 5d;
		double num4 = 0d;
		int linearFactor2 = 3;
		int konstante2 = 30;
		
		double result = (num2-(konstante)) / (num1*linearFactor);
		double result2 = (num4-(konstante2)) / (num3*linearFactor2);

		DoubleDoubleZuDouble nullstelle = (x,y) -> {return y = (y-(konstante)) / (x*linearFactor);};
		DoubleDoubleZuDouble nullstelle2 = (x,y) -> {return y = (y-(konstante2)) / (x*linearFactor2);};
		
		
		assertEquals(result, nullstelle.werteAus(num1, num2), num2);
		System.out.println("Nullstelle: " + nullstelle.werteAus(num1, num2));
		
		assertEquals(result2, nullstelle2.werteAus(num3, num4), num3);
		System.out.println("Nullstelle: " + nullstelle2.werteAus(num3, num4));
		
	}
}
