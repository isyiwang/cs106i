/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {


	public void run(){
		while(frontIsClear())	// end of world
		{
			createColumn();
			descendColumn();
			moveToNextColumn();
		}
		createColumn();
		descendColumn();
	}
	/*
	 * pre-condition: Karel is facing east 
	 * post-condition: Karel is facing north
	 * Karel creates column
	*/
	private void createColumn(){
		turnLeft();
		while(frontIsClear())
		{
			if(!beepersPresent()){
				putBeeper();
			}
			move();
		}
		
		if(!beepersPresent()){
			putBeeper();
		}
	}
	/*
	 * pre-condition: Karel is facing north 
	 * post-condition: Karel is facing east
	 * Karel descends column
	*/
	private void descendColumn(){
		faceBackwards();
		while (frontIsClear()){
			move();
		}
		turnLeft();
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
	 * Karel moves to next column
	*/
	private void moveToNextColumn(){
		move();
		move();
		move();
		move();
	}

}
