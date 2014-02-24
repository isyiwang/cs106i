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

public class ProgramHierarchy extends GraphicsProgram {	
	private static final double WIDTH = 105;
	private static final double HEIGHT = 40;
	
	public void run() {
		drawTopBox(getWidth()/2, getHeight()/3);
		drawLines(getWidth()/2, getHeight()/3 + HEIGHT/2);
		drawBottomBoxes(getWidth()/2, getHeight()*2/3);
	}
	private void drawTopBox(double cx, double cy) {
		GRect rect = new GRect(cx - WIDTH/2, cy - HEIGHT/2, WIDTH, HEIGHT);
		GLabel label = new GLabel("Program", cx, cy);
		double dx = label.getWidth()/2;
		double dy = label.getAscent()/2;
		label.move(-dx, dy);
		add(rect);
		add(label);
	}
	private void drawLines(double x, double y) {
		double dy = getHeight()*2/3 - HEIGHT/2;
		for(int i = 2; i < 5; i++) {
			GLine line = new GLine(x, y, getWidth()*i/6, dy);
			add(line);
		}
	}
	private void drawBottomBoxes(double cx, double cy) {
		for(int i = 2; i < 5; i++) {
			GRect rect = new GRect(cx*i/3 - WIDTH/2, cy - HEIGHT/2, WIDTH, HEIGHT);
			add(rect);
		}
		/* I was thinking of trying to use switch,
		 * but would I need to extend a ConsoleProgram?
		 */
		GLabel label1 = new GLabel("GraphicsProgram", cx*2/3, cy);
		double dx1 = label1.getWidth()/2;
		double dy1 = label1.getAscent()/2;
		label1.move(-dx1, dy1);
		add(label1);
		GLabel label2 = new GLabel("ConsoleProgram", cx, cy);
		double dx2 = label2.getWidth()/2;
		double dy2 = label2.getAscent()/2;
		label2.move(-dx2, dy2);
		add(label2);
		GLabel label3 = new GLabel("DialogProgram", cx*4/3, cy);
		double dx3 = label3.getWidth()/2;
		double dy3 = label3.getAscent()/2;
		label3.move(-dx3, dy3);
		add(label3);
		/*for (int i = 1; i < 3; i++) {
			double dx = labeli.getWidth()/2;
			double dy = labeli.getAscent()/2;
			labeli.move(-dx, dy);
			add(labeli);
		}
		I tried this, but not sure how to do it this way*/
	}
}

