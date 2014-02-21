/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;
import java.lang.Math;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a: ");
		int b = readInt("b: ");
		double c = Math.sqrt(a*a + b*b);
		println("c = " + c);
	}
	/*
	 * I tried to think about test cases to fail it...but couldn't :\.
	 * Non-integers, console rejects; negative numbers are fine since it's
	 * squared; 0's don't crash it either.
	 */
}
