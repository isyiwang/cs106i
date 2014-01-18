/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.lang.String.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double WIDTH = 150;
	private static final double HEIGHT = 50;
	
	public void run() {
		double xDimen = getWidth();
		double yDimen = getHeight();
		
		drawBox ((xDimen/2) - (WIDTH/2), yDimen/4, WIDTH, HEIGHT, "Program");
		drawBox ((xDimen/2) - (WIDTH/2), 2*yDimen/4, WIDTH, HEIGHT, "ConsoleProgram");
		drawBox ((xDimen/4) - (WIDTH/2), 2*yDimen/4, WIDTH, HEIGHT, "GraphicsProgram");
		drawBox ((3*xDimen/4) - (WIDTH/2), 2*yDimen/4, WIDTH, HEIGHT, "DialogProgram");
		drawLine ((xDimen/2), (yDimen/4) + HEIGHT, (xDimen/2), (2*yDimen/4));
		drawLine ((xDimen/2), (yDimen/4) + HEIGHT, (xDimen/2), (2*yDimen/4));
		drawLine ((xDimen/2), (yDimen/4) + HEIGHT, (xDimen/4), (2*yDimen/4));
		drawLine ((xDimen/2), (yDimen/4) + HEIGHT, (3*xDimen/4), (2*yDimen/4));
	}
	
	private void drawBox (double xPos, double yPos, double width, double height, String msg){
		GRect box = new GRect (xPos, yPos, width, height);
		GLabel label = new GLabel(msg, xPos, yPos);
		double labelWidth = label.getWidth();
		double labelHeight = label.getAscent();
		label.move(0, labelHeight);
		label.move((width-labelWidth)/2, (height-labelHeight)/2);
		add(box);
		add(label);
	}
	
	private void drawLine (double x1, double y1, double x2, double y2){
		GLine line = new GLine (x1, y1, x2, y2);
		add (line);
	}
}

