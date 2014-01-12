/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends Karel {
	
	public void run(){
		moveToTheNewspaper();
		pickBeeper();
		returnToStart();
	}
	/*
	 * pre-condition: Karel is at the top left corner of his house facing east
	 * post-condition: Karel is at the newspaper facing east
	 * Moves Karel to the newspaper
	*/
	private void moveToTheNewspaper(){
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	/*
	 * pre-condition: none
	 * post-condition: none
	 * Turns Karel to face right
	*/
	private void turnRight(){
		turnLeft();
		turnLeft();
		turnLeft();
	}
	/*
	 * pre-condition: Karel is at the newspaper facing east
	 * post-condition: Karel is back at the top left corner of his house facing east
	 * Karel returns to starting position
	*/
	private void returnToStart(){
		faceBackwards();
		move();
		move();
		move();
		turnRight();
		move();
		turnRight();
	}
	/*
	 * pre-condition: none
	 * post-condition: none
	 * Turns Karel to face backwards
	*/
	private void faceBackwards(){
		turnLeft();
		turnLeft();
	}
}
