/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run(){
		//Handles checkerboard STREET, covers most cases
		while(frontIsClear()){
			doFirstRow();
			if (leftIsClear()){
				advanceRow();
				doNextRow();				
			}	
			advanceRow();
		}	
		//Handles checkerboard by AVENUE only if STREET algorithm does not 
		if (!frontIsClear() && leftIsClear()){
			turnLeft();
			doFirstRow();
		}
	}
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing east
	 * This function handles all the ODD streets of the checkerboard pattern 
	*/
	private void doFirstRow(){
		while (frontIsClear()){
			alternateBeeper();
		}
		checkPreviousSquare();
	}
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing east
	 * This function checks whether or not the last square needs a beeper 
	 * Fix for my OFF by ONE bug
	*/
	private void checkPreviousSquare(){
		
		if(!frontIsClear()){
			faceBackwards();
			move();
			if(!beepersPresent()){
				faceBackwards();
				move();
				putBeeper();
			} else {
				faceBackwards();
				move();
			}
		}
	}
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing east
	 * Puts beepers starting on the FIRST square of each street
	 * For ODD streets only
	*/
	private void alternateBeeper(){
		putBeeper();
		if(frontIsClear()){
		move();
		}
		if(frontIsClear()){
			move();
		} 
	}
	/*
	 * pre-condition: Karel is facing east at end of previous street
	 * post-condition: Karel is facing east at beginning of next street
	 * Moves Karel back to the front of current street and advance to next street
	*/
	private void advanceRow(){
		faceBackwards();
		while(frontIsClear()){
			move();
		}
		turnRight();
		if(frontIsClear()){
			move();
			turnRight();
		}
	}
	/*
	 * pre-condition: none
	 * post-condition: none
	 * Karel faces in opposite direction
	*/
	private void faceBackwards(){
		turnLeft();
		turnLeft();
	}
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing east
	 * This function handles all the EVEN streets of the checkerboard pattern 
	*/
	private void doNextRow(){
		move();
		while (frontIsClear()){
			alternateBeeper();
		}
		checkPreviousSquare();	
	}
}
