/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		int min = 0; 	
		int max = 0; 
		println ("This program finds the largest and smallest numbers.");
		int x;
		do{
			x = readInt ("? ");
			if (x != 0 && min == 0 && max == 0){
				min = x;
				max = x;
			}
			if (x != 0 && x > max) max = x;
			if (x != 0 && x < min) min = x;
			
		} while (x != 0);
		
		if (max == 0 && min == 0){
			println ("No values Entered");
		} else {
			println ("Smallest: " + min) ;
			println ("Largest: " + max);
		}
		
		
	}
}

