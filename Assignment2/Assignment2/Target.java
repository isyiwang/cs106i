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
	private static final int OUTER_RADIUS = 72;
	private static final double MIDDLE_RADIUS = 72*0.65;
	private static final double INNER_RADIUS = 72*0.3;
	
	public void run() {
		drawTarget(getWidth()/2, getHeight()/2);
	}
	private void drawTarget(double cx, double cy) {
		drawOuter(cx - OUTER_RADIUS, cy - OUTER_RADIUS);
		drawMiddle(cx - MIDDLE_RADIUS, cy - MIDDLE_RADIUS);
		drawInner(cx - INNER_RADIUS, cy - INNER_RADIUS);
	}
	private void drawOuter(double x, double y) {
		GOval outer = new GOval(x, y, 2*OUTER_RADIUS, 2*OUTER_RADIUS);
		outer.setFilled(true);
		outer.setFillColor(Color.RED);
		add(outer);
	}
	private void drawMiddle(double x, double y) {
		GOval middle = new GOval(x, y, 2*MIDDLE_RADIUS, 2*MIDDLE_RADIUS);
		middle.setFilled(true);
		middle.setFillColor(Color.WHITE);
		middle.sendForward();
		add(middle);
	}
	private void drawInner(double x, double y) {
		GOval inner = new GOval(x, y, 2*INNER_RADIUS, 2*INNER_RADIUS);
		inner.setFilled(true);
		inner.setFillColor(Color.RED);
		inner.sendToFront();
		add(inner);
	}
}
