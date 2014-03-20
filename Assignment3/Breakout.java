/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int DELAY = 10;

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
		/* You fill this in, along with any subsidiary methods */
		addMouseListeners();
	}

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setup();	
		runGame();
	}

	private void setup(){
		totalBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
		setSize(WIDTH, HEIGHT);
		layBricks();
		layPaddle();
		createBall();
	}
	
	public void mouseClicked(MouseEvent e){

	}
	
	public void mouseMoved (MouseEvent e){
		if (e.getX() <= WIDTH - PADDLE_WIDTH)
		{
			paddle.setLocation (e.getX(), (HEIGHT - PADDLE_Y_OFFSET));		
		}

	}
	
	private void layBricks(){
		int xPos = 0;
		int yPos = BRICK_Y_OFFSET;
		int x = NBRICK_ROWS;

		Color brickColor = Color.BLACK;
		
		while (x > 0){
			if (x%2 == 0 && brickColor == Color.BLACK)
				brickColor = Color.RED;
			else if ((x%2 == 0) && (brickColor == Color.RED))
				brickColor = Color.ORANGE;
			else if (x%2 == 0 && brickColor == Color.ORANGE)
				brickColor = Color.YELLOW;
			else if (x%2 == 0 && brickColor == Color.YELLOW)
				brickColor = Color.GREEN;
			else if (x%2 == 0 && brickColor == Color.GREEN)
				brickColor = Color.CYAN;
			else if (x%2 == 0 && brickColor == Color.CYAN)
				brickColor = Color.RED;
			
			for (int i = NBRICKS_PER_ROW; i > 0; i --){
				GRect brick = new GRect(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);		
				brick.setFillColor(brickColor);
				brick.setColor(brickColor);
				add(brick);
				xPos += (BRICK_WIDTH + BRICK_SEP);
			}
			xPos = 0;
			yPos += (BRICK_HEIGHT + BRICK_SEP);
			x -= 1;
		}
		}
		
	private void layPaddle(){
		int xPos = (WIDTH - PADDLE_WIDTH)/2;
		int yPos = (HEIGHT - PADDLE_Y_OFFSET);
		paddle = new GRect (xPos, yPos, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		paddle.setColor(Color.BLACK);
		add(paddle);
	}
	
	private void createBall(){
		ball = new GOval((WIDTH-2*BALL_RADIUS)/2,(HEIGHT-PADDLE_Y_OFFSET-2*BALL_RADIUS),2*BALL_RADIUS,2*BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		ball.setColor(Color.BLACK);
		add(ball);
	}
	
	private void runGame(){
		vx = rgen.nextDouble (1.0, 3.0);
		vy = rgen.nextDouble (1.0, 3.0);
		vy = -vy;

		while (ball.getY() <= HEIGHT)
		{
			moveBall();
			checkForCollision();
			if (totalBricks == 0){
				break;
			}
			pause(DELAY);
		}
		
	}
	
	private void moveBall() {
		ball.move(vx, vy);
	}
	private void checkForCollision(){
		//check for side walls
		if(ball.getX() + 2*BALL_RADIUS > WIDTH || ball.getX() < 0){
			vx = -vx;
		}
		//check for top wall 
		if(ball.getY() < 0){
			vy = -vy;
		}
		//check for paddle
		if (((getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS) == paddle)
					|| (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS) == paddle))){
			vy = -vy;
		}
		//check for brick
		else if ((getElementAt(ball.getX(), ball.getY()) != null)){
			remove (getElementAt(ball.getX(), ball.getY()));
			vy = -vy;
			totalBricks -= 1; //untested
		}
		else if ()
	}

	

	private int totalBricks;
	private GRect paddle;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;

}
