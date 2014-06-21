/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;


public class HangmanCanvas extends GCanvas {
	
	private int canvasHeight;
	private int canvasWidth;
	private GLine scaffoldLine = new GLine(0,0,0,0); 
	private GLine beamLine = new GLine(0,0,0,0);
	private GLine ropeLine = new GLine(0,0,0,0);
	private GLine bodyLine = new GLine(0,0,0,0);
	private GOval headOval = new GOval (HEAD_RADIUS, HEAD_RADIUS);
	private GLabel wordLabel = new GLabel("",0,0);
	private GLabel guessLabel = new GLabel("",0,0);
	private GLabel guessHeader = new GLabel("GUESSES: ",0,0);
	private double xPos;
	private double yPos;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		initializePositions();
		drawScaffold();
		drawBeam();
		drawRope();		
	}
	
	private void initializePositions(){
		canvasHeight = getHeight();
		canvasWidth = getWidth();
		xPos = canvasWidth/X_DIVIDER; 
		yPos = (canvasHeight - SCAFFOLD_HEIGHT)/Y_DIVIDER;
		guessHeader.move(xPos - guessHeader.getWidth(), yPos + SCAFFOLD_HEIGHT + 2*OFFSET);
		guessLabel.move(xPos, yPos + SCAFFOLD_HEIGHT + 2*OFFSET);
		wordLabel.move(xPos, yPos + SCAFFOLD_HEIGHT + OFFSET);
		headOval.move(xPos + BEAM_LENGTH - (HEAD_RADIUS/2), yPos + ROPE_LENGTH);

		add(guessHeader);
	}

	private void drawScaffold(){
		scaffoldLine.setStartPoint(xPos, yPos);
		scaffoldLine.setEndPoint(xPos, yPos + SCAFFOLD_HEIGHT);
		add(scaffoldLine);
	}
	
	private void drawBeam(){
		beamLine.setStartPoint(xPos, yPos);
		beamLine.setEndPoint(xPos + BEAM_LENGTH, yPos);
		add(beamLine);
	}
	
	private void drawRope(){
		ropeLine.setStartPoint(xPos + BEAM_LENGTH, yPos);
		ropeLine.setEndPoint(xPos + BEAM_LENGTH, yPos + ROPE_LENGTH);
		add(ropeLine);
	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {

		wordLabel.setLabel(word);
		add(wordLabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int remainingGuesses) {
		addIncorrectLetter(letter);
		switch (remainingGuesses){
		case 0: 
			drawFoot(xPos + BEAM_LENGTH + HIP_WIDTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					xPos + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			break;
		case 1: 
			drawFoot(xPos + BEAM_LENGTH - HIP_WIDTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					xPos + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			break;
		case 2: 
			drawLimb(xPos + BEAM_LENGTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
					xPos + BEAM_LENGTH + HIP_WIDTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, LEG_LENGTH);
			break;
		case 3: 
			drawLimb(xPos + BEAM_LENGTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
					xPos + BEAM_LENGTH - HIP_WIDTH, yPos + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, LEG_LENGTH);
			break;
		case 4: 
			drawLimb(xPos + BEAM_LENGTH, yPos + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + HEAD_RADIUS,
					xPos + BEAM_LENGTH + UPPER_ARM_LENGTH, yPos + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + HEAD_RADIUS, LOWER_ARM_LENGTH);
			break;
		case 5: 
			drawLimb(xPos + BEAM_LENGTH, yPos + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + HEAD_RADIUS,
					xPos + BEAM_LENGTH - UPPER_ARM_LENGTH, yPos + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + HEAD_RADIUS, LOWER_ARM_LENGTH);
			break;
		case 6: 
			drawBody();
			break;
		case 7: 
			drawHead();
			break;
			default: break;
		}
	}
	
	private void addIncorrectLetter(char ch){
		String temp = guessLabel.getLabel();
		temp = temp + ch;
		guessLabel.setLabel(temp);
		add(guessLabel);
	}

	private void drawHead(){
		add(headOval);
	}
	
	private void drawBody(){
		bodyLine.setStartPoint(xPos + BEAM_LENGTH, yPos + ROPE_LENGTH + HEAD_RADIUS);
		bodyLine.setEndPoint(xPos + BEAM_LENGTH , yPos + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS);
		add(bodyLine);
	}
	
	private void drawLimb(double xPosition, double yPosition, double xPosition2, double yPosition2, int constantLength){
		GLine upperLimb = new GLine(xPosition, yPosition, xPosition2, yPosition2); 
		GLine lowerLimb = new GLine(xPosition2, yPosition2, xPosition2, yPosition2 + constantLength);
		add(upperLimb);
		add(lowerLimb);
	}
	
	private void drawFoot(double xPosition, double yPosition, double xPosition2, double yPosition2){
		GLine footLine = new GLine(xPosition, yPosition, xPosition2, yPosition2); 
		add(footLine);
	}
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private static final int X_DIVIDER = 4;
	private static final int Y_DIVIDER = 3;
	private static final int OFFSET = 20;


}
