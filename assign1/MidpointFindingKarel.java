/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/*
	 * pre-condition: Karel is facing east on First STREET
	 * post-condition: Karel is facing east at End of First STREET
	 * This function puts a beeper at the midpoint of First STREET
	*/
	public void run(){
		//For the x1
		putBeeper();
		findX2();
		dividePileByTwo();
		cleanLeftPile();
		placeTopPileOnFirstStreet();
		cleanFirstStreet();
	}
	
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing east
	 * This function puts all beepers on first AVE to end of First STREET
	*/
	private void findX2(){
		putBeepersOnFirstAve();
		while (beepersPresent()){
			aveBeepersToEndFirstSt();
			goToLastAveBeeper();		
		}
	}
	
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing north
	 * This function puts beepers on first AVE
	*/
	private void putBeepersOnFirstAve(){
		turnLeft();
		while(frontIsClear()){
			putBeeper();
			move();
		}
		putBeeper();
	}
	
	/*
	 * pre-condition: Karel is facing north
	 * post-condition: Karel is facing east
	 * This function puts beepers at end of First STREET
	*/
	private void aveBeepersToEndFirstSt(){
		faceBackwards();
		pickBeeper();
		goToEndFirstStFromFirstAve();
		putBeeper();
	}
	
	/*
	 * pre-condition: Karel is facing south on First AVE
	 * post-condition: Karel is facing east
	 * This function moves Karel from facing south on First AVE to east at end of First STREET
	*/
	private void goToEndFirstStFromFirstAve(){
		while(frontIsClear()){
			move();
		}
		turnLeft();
		while(frontIsClear()){
			move();
		}
	}
	
	/*
	 * pre-condition: Karel is facing east at end of previous street
	 * post-condition: Karel is facing east at beginning of next street
	 * Moves Karel back to the front of current street and advance to next street
	*/
	private void faceBackwards(){
		turnLeft();
		turnLeft();
	}
	
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing north
	 * This function puts karel to the next available beeper on First AVE
	*/
	private void goToLastAveBeeper(){
		goToBegFirstSt();
		turnRight();
		while(beepersPresent()){
			move();
		}
		faceBackwards();
		if(frontIsClear()){
			move();
			faceBackwards();
		} else{
			turnLeft();
		}
	}
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing west
	 * This function puts Karel back at the beginning of first STREET
	*/
	private void goToBegFirstSt(){
		faceBackwards();
		while(frontIsClear()){
			move();
		}
	}
	/*
	 * pre-condition: Karel is facing east on the corner of First STREET and First AVE
	 * post-condition: Karel is facing 
	 * This function divides pile of beepers at the end of First STREET into two 
	*/
	private void dividePileByTwo(){
		goToEndSt();	
		while (beepersPresent()){
			moveToTop();
			moveToLeft();
		}
	}
	/*
	 * pre-condition: Karel is facing east
	 * post-condition: Karel is facing east
	 * This function moves Keral to end of the Street
	*/
	private void goToEndSt(){
		while (frontIsClear()){
			move();
		}
	}
	/*
	 * pre-condition: Karel is facing east at end of First STREET
	 * post-condition: Karel is facing east at end of First STREET
	 * This function moves one beeper to "top" pile
	*/
	private void moveToTop(){
		pickBeeper();
		turnLeft();
		move();
		putBeeper();
		faceBackwards();
		move();
		turnLeft();
	}
	/*
	 * pre-condition: Karel is facing east at end of First STREET
	 * post-condition: Karel is facing east at end of First STREET
	 * This function moves one beeper to "left" pile
	*/
	private void moveToLeft(){
		pickBeeper();
		faceBackwards();
		move();
		putBeeper();
		faceBackwards();
		move();
	}
	/*
	 * pre-condition: Karel is facing east at end of First STREET
	 * post-condition: Karel is facing east at end of First STREET
	 * This function cleans "left" pile
	*/
	private void cleanLeftPile(){
		faceBackwards();
		move();
		while(beepersPresent()){
			pickBeeper();
		}
		faceBackwards();
		move();
	}
	/*
	 * pre-condition: Karel is facing east at end of First STREET
	 * post-condition: Karel is facing east at end of First STREET
	 * This function cleans "left" pile
	*/	
	private void placeTopPileOnFirstStreet(){
		
		goToTopPile();
		while(beepersPresent()){
			pickBeeper();
			moveBackToFirst();
			while(beepersPresent()){
				move();
			}
			putBeeper();
			returnToTopPile();
		}
	}
	/*
	 * pre-condition: Karel is facing east at end of First STREET
	 * post-condition: Karel is facing north at end of Second STREET
	 * This function moves Karel to Top Pile
	*/	
	public void goToTopPile(){
		turnLeft();
		move();
	}
	/*
	 * pre-condition: Karel is facing north at end of Second STREET
	 * post-condition: Karel is facing west at end of First STREET
	 * This function moves to end of First STREET 
	*/	
	public void moveBackToFirst(){
		faceBackwards();
		move();
		turnRight();
	}
	/*
	 * pre-condition: Karel is facing west on First STREET
	 * post-condition: Karel is facing north at end of second STREET
	 * This function moves to end of First STREET 
	*/	
	public void returnToTopPile(){
		faceBackwards();
		while (frontIsClear()){
			move();
		}
		turnLeft();
		move();
		
	}
	/*
	 * pre-condition: Karel is facing north at end of Second STREET
	 * post-condition: Karel is facing west at end of First STREET
	 * This function cleans up first STREET and only leaves midpoint beeper
	*/	
	public void cleanFirstStreet(){
		moveBackToFirst();
		goToLastBeeper();
		move();
		while (frontIsClear()){
			pickBeeper();
			move();
		}
		pickBeeper();
	}
	/*
	 * pre-condition: Karel is facing west at end of First STREET
	 * post-condition: Karel is facing east at midpoint of Frst STREET
	 * This function leaves Keral at midpoint of First STREET
	*/	
	public void goToLastBeeper(){
		while(beepersPresent()){
			move();
		}
		faceBackwards();
		move();
	}
}
