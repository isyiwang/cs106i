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
		while (true) {
			int n = readInt("Enter a positive integer: ");
			if (n > 0) {
				int rolls = 0;
				while (n != 1) {
					if (n % 2 == 1) {
						int m = 3*n + 1;
						println(n + " is odd, so I make 3n + 1: " + m);
						n = m;
					} else {
						int m = n/2;
						println(n + " is even so I take half: " + m);
						n = m;
					}
					rolls++;
				}
				println("It took " + rolls + " iterations to reach 1");
				break;
			}
			println("Please provide a positive integer!");
		}
	}
}
/* I don't know if it was crashable before for #s below 1,
 * but wanted to make it crash-proof! :)
 */

