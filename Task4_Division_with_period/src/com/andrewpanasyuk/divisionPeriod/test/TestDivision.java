package com.andrewpanasyuk.divisionPeriod.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.andrewpanasyuk.divisionPeriod.LongDivider;

public class TestDivision {
	private LongDivider longDivider = new LongDivider();
	private int dividend;
	private int divisor;
	private String expected;
	private String actual;

	@Test
	public void testDivision1() {
		dividend = 8;
		divisor = 2;
		expected = "8|2\n" + "8+-\n" + "-|4\n" + "0";
		actual = longDivider.showLongDivision(dividend, divisor);
		assertEquals(expected, actual);
	}

	@Test
	public void testDivision2() {
		dividend = 129;
		divisor = 4;
		expected = "129|4\n" + "12 +-----\n" + "-- |32.25\n" + "  9\n"
				+ "  8\n" + "  -\n" + "  10\n" + "   8\n" + "  --\n" + "   20\n"
				+ "   20\n" + "   --\n" + "    0";
		actual = longDivider.showLongDivision(dividend, divisor);
		assertEquals(expected, actual);
	}

	@Test(expected = ArithmeticException.class)
	public void testDivision3() {
		dividend = 89;
		divisor = 0;
		longDivider = new LongDivider();
		longDivider.showLongDivision(dividend, divisor);
	}

	@Test
	public void testDivision4() {
		dividend = 1000;
		divisor = 3;
		expected = "1000|3\n" + " 9  +-------\n" + "--  |333.(3)\n" + " 10\n" + "  9\n"
				+ " --\n" + "  10\n" + "   9\n" + "  --\n" + "   10\n" + "    9\n" + "   --\n" + "    1";
		actual = longDivider.showLongDivision(dividend, divisor);
		assertEquals(expected, actual);
	}

	@Test
	public void testDivision5() {
		dividend = -819;
		divisor = 52;
		expected = "-819|52\n" + " 52 +------\n" + " -- |-15.75\n" + " 299\n"
				+ " 260\n" + " ---\n" + "  390\n" + "  364\n" + "  ---\n" + "   260\n" + "   260\n" + "   ---\n" + "     0";
		actual = longDivider.showLongDivision(dividend, divisor);
		assertEquals(expected, actual);
	}

	@Test
	public void testDivision6() {
		dividend = -819;
		divisor = -52;
		expected = "-819|-52\n" + " 52 +-----\n" + " -- |15.75\n" + " 299\n"
				+ " 260\n" + " ---\n" + "  390\n" + "  364\n" + "  ---\n" + "   260\n" + "   260\n" + "   ---\n" + "     0";
		actual = longDivider.showLongDivision(dividend, divisor);
		assertEquals(expected, actual);
	}

	@Test
	public void testDivision7() {
		dividend = 7;
		divisor = -12;
		expected = "7 |-12\n" + "60+--------\n" + "--|-0.58(3)\n" + "100\n" + " 96\n"
				+ "---\n" + "  40\n" + "  36\n" + "  --\n" + "   4";
		actual = longDivider.showLongDivision(dividend, divisor);
		assertEquals(expected, actual);
	}
}