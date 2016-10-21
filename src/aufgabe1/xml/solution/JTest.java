package aufgabe1.xml.solution;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class JTest {

	@Test
	public void testSetWert() {
		Messung messung = new Messung();
		messung.setWert(1d);
		double doubleExpected = 1d;
		double doubleActual = messung.getWert();
		assertEquals(doubleExpected, doubleActual, 0d);
	}

	@Test
	public void testSetLocalDateTime(){
		Messung messung = new Messung();
		LocalDateTime timeActual = LocalDateTime.now();
		messung.setLocalDateTime(timeActual);
		LocalDateTime timeExpected = timeActual;
		assertEquals(timeExpected, timeActual);
	}
	
}
