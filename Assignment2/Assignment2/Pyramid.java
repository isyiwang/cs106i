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

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		placeBricks(BRICK_WIDTH, BRICK_HEIGHT);
	}
	private void placeBricks(double cx, double cy) {
		int j = 1;
		for (double i = BRICKS_IN_BASE; i > 0; i--) {
			for (double k = 0; k < i; k++) {
				double x = ((getWidth()/2.0) - (i/2.0*cx))+(k*cx);
				double y = getHeight() - (j*cy);
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
			j = j + 1;
		}
	}
}

