/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	//what happens when a double multiplies with int	
	private static final int RADIUS_LARGE = 72;
	private static final double MED_SCALING = 0.65;
	private static final double SMALL_SCALING = 0.3;
	
	public void run() {
		int x_dimen = getWidth();
		int y_dimen = getHeight();
		
		drawRedCircle(x_dimen/2 - RADIUS_LARGE, y_dimen/2 - RADIUS_LARGE, RADIUS_LARGE*2, RADIUS_LARGE*2);
		drawWhiteCircle(x_dimen/2 - MED_SCALING* RADIUS_LARGE, y_dimen/2 - MED_SCALING*RADIUS_LARGE, MED_SCALING*RADIUS_LARGE*2, MED_SCALING*RADIUS_LARGE*2);
		drawRedCircle(x_dimen/2 - SMALL_SCALING* RADIUS_LARGE, y_dimen/2 - SMALL_SCALING*RADIUS_LARGE, SMALL_SCALING*RADIUS_LARGE*2, SMALL_SCALING*RADIUS_LARGE*2);
	}
	
	private void drawRedCircle (double xPos, double yPos, double height, double width){
		GOval red_circ = new GOval(xPos, yPos, height, width);
		red_circ.setFilled(true);
		red_circ.setFillColor(Color.RED);
		add(red_circ);
	}
	
	private void drawWhiteCircle (double xPos, double yPos, double height, double width){
		GOval white_circ = new GOval(xPos, yPos, height, width);
		white_circ.setFilled(true);
		white_circ.setFillColor(Color.WHITE);
		add(white_circ);
	}
}
