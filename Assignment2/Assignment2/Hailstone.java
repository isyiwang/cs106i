/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int input; 
		input = readInt ("Enter a Number: ");
		int iterations = 0;
		while (input != 1) {
			if (input % 2 == 0) {
				println (input + " is even, so I take half: " + input/2);
				input /= 2;
			} else {
				println (input + " is odd, so I make 3n+1: " + 3*input + 1);
				input = 3*input + 1;
			}	
			iterations ++;
		}
		println ("The process took " + iterations + " to reach 1");
	}
}

