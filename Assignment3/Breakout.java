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
	private static final int PADDLE_WIDTH = 100;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 100;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	//private static final int BRICK_WIDTH =
	//  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 30;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int DELAY = 10;
	
	private static final int BLINK = 50;

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
		livesRemain = NTURNS;
		setup();	
		if (BALL_RADIUS <= APPLICATION_HEIGHT/2 && BALL_RADIUS <= APPLICATION_WIDTH/2){
			while (livesRemain > 0){
				addLabel();
				createBall();
				runGame();
				removeLabel();
				if (totalBricks == 0) {
					break;
				}
			}
			displayEnd();
		} else {
			displayError();
		}
	}

	private void setup(){
		if (NBRICKS_PER_ROW != 0){
			BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
		} else {
			BRICK_WIDTH = 0;
		}
		totalBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
		setSize(WIDTH, HEIGHT);
		layBricks();
		layPaddle();
		paddleFlag = 0; //Initialize paddle flag
	}
	
	public void mouseClicked(MouseEvent e){

	}
	
	public void mouseMoved (MouseEvent e){
		if (e.getX() <= WIDTH - PADDLE_WIDTH)
		{
			if (getElementAt(e.getX(), (HEIGHT - PADDLE_Y_OFFSET)) != ball && 
					getElementAt(e.getX() + PADDLE_WIDTH, (HEIGHT - PADDLE_Y_OFFSET)) != ball &&
					getElementAt(e.getX(), (HEIGHT - PADDLE_Y_OFFSET) + PADDLE_HEIGHT) != ball &&
					getElementAt(e.getX() + PADDLE_WIDTH, (HEIGHT - PADDLE_Y_OFFSET) + PADDLE_HEIGHT) != ball){
				paddle.setLocation (e.getX(), (HEIGHT - PADDLE_Y_OFFSET));	

			}
		}
	}
	
	private void layBricks(){
		int xPos = 0;
		int yPos = BRICK_Y_OFFSET;
		int x = NBRICK_ROWS;

		Color brickColor = Color.BLACK;
		
		while (x > 0){
			if ((NBRICK_ROWS-x)%2 == 0 && brickColor == Color.BLACK)
				brickColor = Color.RED;
			else if (((NBRICK_ROWS-x)%2 == 0) && (brickColor == Color.RED))
				brickColor = Color.ORANGE;
			else if ((NBRICK_ROWS-x)%2 == 0 && brickColor == Color.ORANGE)
				brickColor = Color.YELLOW;
			else if ((NBRICK_ROWS-x)%2 == 0 && brickColor == Color.YELLOW)
				brickColor = Color.GREEN;
			else if ((NBRICK_ROWS-x)%2 == 0 && brickColor == Color.GREEN)
				brickColor = Color.CYAN;
			else if ((NBRICK_ROWS-x)%2 == 0 && brickColor == Color.CYAN)
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
	
	private void addLabel(){
		
		header = new GLabel ("LIVES REMAINING: " + livesRemain);
		double labelHeight = header.getHeight();
		header.move(0, labelHeight);
		header.setColor(Color.BLACK);
		add(header);
	}
	
	private void removeLabel(){
		remove (header);
	}
	
	private void createBall(){
		ball = new GOval((WIDTH-2*BALL_RADIUS)/2,(HEIGHT-PADDLE_Y_OFFSET-2*BALL_RADIUS-PADDLE_HEIGHT),2*BALL_RADIUS,2*BALL_RADIUS);
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
				//if (totalBricks == 0){
				//	break;
				//}
				pause(DELAY);
			}
			if (livesRemain > 1){
				remove(ball);
			}
			livesRemain --;
	}
	
	private void moveBall() {
		ball.move(vx, vy);
	}
	
	private void checkForCollision(){
		checkWalls();
		checkPaddleBrick();
	}
	
	private void checkWalls(){
		//check for side walls
		if(ball.getX() + 2*BALL_RADIUS > WIDTH || ball.getX() < 0){
			vx = -vx;
			if (paddleFlag == 1){
				paddleFlag = 0;
			}
		}
		//check for top wall 
		if(ball.getY() < 0){
			vy = -vy;
			if (paddleFlag == 1){
				paddleFlag = 0;
			}
		}
	}
	
	private void checkPaddleBrick(){
		double xCollision = 0;
		double yCollision = 0;
		int leftFlag = 0;
		int rightFlag = 0;
		int colFlag =0;
		int topFlag = 0; //Left or Right Flag

		if (getElementAt(ball.getX()+BALL_RADIUS, 1+ball.getY()+2*BALL_RADIUS) != null){ // checks bottom
			xCollision = ball.getX()+BALL_RADIUS;
			yCollision = 1+ball.getY()+2*BALL_RADIUS;
			colFlag = 1;
		}else if (getElementAt(ball.getX()-1, ball.getY()+BALL_RADIUS) != null){ //checks left 
			xCollision = ball.getX()-1;
			yCollision = ball.getY()+BALL_RADIUS;
			leftFlag = 1;
			colFlag = 1;
		} else if (getElementAt(1+ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS) != null){ // checks right
			xCollision = 1+ball.getX()+2*BALL_RADIUS;
			yCollision = ball.getY()+BALL_RADIUS;
			rightFlag = 1;
			colFlag = 1;
		} else if (getElementAt(ball.getX()+BALL_RADIUS, ball.getY()-1) != null){ // checks top
			xCollision = ball.getX()+BALL_RADIUS;
			yCollision = ball.getY()-1;
			colFlag = 1;
			topFlag = 1;
		}
		if (colFlag == 1 ){
			if ((getElementAt(xCollision, yCollision) == paddle)){
				if (paddleFlag == 0 && topFlag != 1){
				    if (leftFlag == 1 || rightFlag == 1){ 
						if ((vx > 0 && rightFlag == 1) || (vx < 0 && leftFlag == 1)){
							vx = -vx;
							} else if (vx < 0){
								vx -= 1;
							} else if (vx > 0){
								vx += 1;
							}
					}else {
						vy = -vy;
					}
					paddleFlag = 1;
					}
			} else if (getElementAt(xCollision, yCollision) != header){
				remove (getElementAt(xCollision, yCollision));
				if (paddleFlag == 1){
					paddleFlag = 0;
				}
				if (leftFlag == 1 || rightFlag == 1){
					if ((vx > 0 && rightFlag == 1) || (vx < 0 && leftFlag == 1)){
					vx = -vx;
					}
				} else {
					vy = -vy;
				}
				totalBricks -= 1; //untested
			}
		}
		colFlag = 0;
		leftFlag = 0;
		rightFlag = 0;
		topFlag = 0;
	}
	 
	private void displayEnd(){
		String msg = "Theres a Bug in your Code";
		if (totalBricks == 0 && livesRemain > 0){
			msg = "WINNER!!! You're AWESOME, Isaac";
		} else if (totalBricks != 0 && livesRemain == 0){
			msg = "LOSER!!! C'mon Isaac";
		}
			
		GLabel winner = new GLabel (msg);
		double height = winner.getHeight();
		double width = winner.getWidth();
		winner.move ((APPLICATION_WIDTH - width)/2, (APPLICATION_HEIGHT - height)/2);
		add (winner);
		int i = BLINK;
		while (i > 0){
			winner.setVisible(false);
			pause (DELAY*2);
			winner.setVisible(true);
			pause (DELAY*2);
			i--;
		}
	}
	
	private void displayError(){
		String msg = "ERROR! Ball Too Big";
		String msg2 = "Nice Try, Isaac";
		GLabel error1 = new GLabel (msg);
		GLabel error2 = new GLabel (msg2);
		double height = error1.getHeight();
		double width = error1.getWidth();
		error1.move ((APPLICATION_WIDTH - width)/2, (APPLICATION_HEIGHT - 3*height)/2);
		height = error2.getHeight();
		width = error2.getWidth();
		error2.move ((APPLICATION_WIDTH - width)/2, (APPLICATION_HEIGHT - height)/2);
		add (error1);
		add (error2);
	}
	private int BRICK_WIDTH;
	private int totalBricks;
	private GRect paddle;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;
	private int paddleFlag;
	private int livesRemain;
	private GLabel header;

}


