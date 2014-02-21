/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 5;
	
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int rolls = 0;
		int min = 0;
		int max = 0;
		while (true) {
			int val = readInt("? ");
			if (val == SENTINEL) break;
			if (min + max + val == val) {
				min = val;
				max = val;
			}
			/* tested it for if SENTINEL != 0. Think I got it*/
			if (min > val) {
				min = val;
			}
			if (max < val) {
				max = val;
			}
			rolls++;
		}
	/*private void checkMin(int val, int min) {
		if (min > val) {
			min = val;
		}
		
	}
	private void checkMax(int val) {
		if (max < val) {
			max = val;
		}
	}
	Couldn't figure out how to make a method :\.
	*/
		switch (rolls) {
			case 0:
				println("0 is the smallest & the largest # ever!");
				break;
			default:
				println("smallest: " + min);
				println("largest: " + max);
				break;
		}
	}
}

