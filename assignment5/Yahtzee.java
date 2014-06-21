/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		while (nPlayers > MAX_PLAYERS){
			nPlayers = dialog.readInt("Cannot have more than 4 players, bozo. How many players? ");
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		initializeGame();
		playGame();
	}
	
	private void initializeGame(){
		diceValues = new int[N_DICE];
		
		//DEBUG 
		diceDebug = new int[N_DICE];
		for (int i = 0; i < N_DICE; i++){
			diceDebug[i] = 5;
		}
	}

	private void playGame() {
		for (int i = 1; i <= nPlayers; i++){
			initialRoll(i);
			secondAndThirdRoll(i);
		}
		display.displayDice(diceDebug);
	}
	
	private void initialRoll(int player){
		display.waitForPlayerToClickRoll(player);
		displayFirstRoll();
	}
		
	private void displayFirstRoll(){
		for(int i = 0; i < N_DICE; i++){
			diceValues[i] = rgen.nextInt(1,6);
		}
		display.displayDice(diceValues);
	}
	
	private void secondAndThirdRoll(int player){
		for(int roll = 0; roll < (ALLOWED_ROLLS - 1); roll++){
			display.waitForPlayerToSelectDice();
			for(int i = 0; i < N_DICE; i++){
				if (display.isDieSelected(i)){
					diceValues[i] = rgen.nextInt(1,6); 
				}
			}
			display.displayDice(diceValues);
		}
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] diceValues;
	private int[] diceDebug;

}
