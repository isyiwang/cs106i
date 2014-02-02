/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.lang.Math.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		double y_dimen = getHeight(); 
		double x_dimen = getWidth();
		
		for (int i = BRICKS_IN_BASE; i>0; i--) {
			if (i % 2 == 0) {
				laybricks (i, (x_dimen/2)-(i/2)*BRICK_WIDTH, (y_dimen)-((BRICKS_IN_BASE-i)+1)*BRICK_HEIGHT);
			} else {
				laybricks (i, (x_dimen/2)-((i/2)*BRICK_WIDTH)-(BRICK_WIDTH/2), (y_dimen)-((BRICKS_IN_BASE-i)+1)*BRICK_HEIGHT);
			}
		}		
	}
	
	private void laybricks (int numOfBricks, double startXPos, double startYPos) {
		double startX = startXPos;
		for (int i = numOfBricks; i > 0; i--){
			add (new GRect(startX, startYPos, BRICK_WIDTH, BRICK_HEIGHT));
			startX += BRICK_WIDTH;
		}
	}
}

