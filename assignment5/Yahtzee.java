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
		numberBucket = new int[TOTAL_DICE_SIDES];
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
			keepScore(i);
		}
		display.displayDice(diceDebug);
	}
	
	private void initialRoll(int player){
		display.waitForPlayerToClickRoll(player);
		displayFirstRoll();
	}
		
	private void displayFirstRoll(){
		if(!CHEAT_MODE){
			for(int i = 0; i < N_DICE; i++){
				diceValues[i] = rgen.nextInt(1,6);
			}
		} else{
			for(int i = 0; i < N_DICE; i++){
				IODialog dialog = getDialog();
				diceValues[i] = dialog.readInt("Dice Value " + i + " : ");
			}
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
	
	private void keepScore(int player){
		int category = display.waitForPlayerToSelectCategory();
		bucketizeRolls(diceValues);
		
	
		
		if (checkCategory(category)){
			//calculate the score 
			
			//update the score
			display.updateScorecard(category, player, calculateScore(category));
		} else{
			display.updateScorecard(category, player, 0);
		}      
	}
	
	private void bucketizeRolls(int[] diceNumbers){
		clearNumberBucket(numberBucket);
		for(int i = 0; i < N_DICE; i++){
			switch(diceNumbers[i]){
			case DICE_VALUE_ONE:
				numberBucket[0] = numberBucket[0] + 1;
				break;
			case DICE_VALUE_TWO:
				numberBucket[1] = numberBucket[1] + 1;
				break;
			case DICE_VALUE_THREE:
				numberBucket[2] = numberBucket[2] + 1;
				break;
			case DICE_VALUE_FOUR:
				numberBucket[3] = numberBucket[3] + 1;
				break;
			case DICE_VALUE_FIVE:
				numberBucket[4] = numberBucket[4] + 1;
				break;
			case DICE_VALUE_SIX:
				numberBucket[5] = numberBucket[5] + 1;
				break;
				default: break;
			}
		}
	}
	
	private void clearNumberBucket(int[] bucket){
		for(int i = 0; i < TOTAL_DICE_SIDES; i++){
			bucket[i] = 0;
		}
	}
	
	private boolean checkCategory(int categoryInput){
		boolean threeOfKind = false;
		
		switch (categoryInput){
		case ONES: 
		case TWOS:
		case THREES:
		case FOURS:
		case FIVES: 
		case SIXES: 
			if (numberBucket[categoryInput - 1] >= 1){
				return true;
			} else {
				return false;
			}
		case THREE_OF_A_KIND:
			threeOfKind = checkThreeOfKind(numberBucket);
			return threeOfKind;
		case FOUR_OF_A_KIND:
			for (int i = 0; i < TOTAL_DICE_SIDES;i++){
				if (numberBucket[i] >= FOUR_ROLLS) return true;
			}
				return false;
		case FULL_HOUSE:
			threeOfKind = checkThreeOfKind(numberBucket);
			for (int i = 0; i < TOTAL_DICE_SIDES;i++){
				if (numberBucket[i] == TWO_ROLLS && threeOfKind) return true;
			}
			return false;
		case SMALL_STRAIGHT:
		case LARGE_STRAIGHT: 
		case YAHTZEE: 
			default: 
				return false;
			
		}

	}
	
	private boolean checkThreeOfKind(int[] bucketizedNums){
		for (int i = 0; i < TOTAL_DICE_SIDES;i++){
			if (numberBucket[i] >= THREE_ROLLS) return true;
		}
		return false;
	}
	
	private int calculateScore(int categoryInput){
		int score = 0;
			switch (categoryInput){
		case ONES: 
		case TWOS:
		case THREES:
		case FOURS:
		case FIVES: 
		case SIXES: 
			return numberBucket[categoryInput - 1] * categoryInput;
		case THREE_OF_A_KIND:
		case FOUR_OF_A_KIND:
			for (int i = 0; i < N_DICE; i++){
				score = diceValues[i] + score; 
			}
			return score;
		case FULL_HOUSE:
			return 25;
		case SMALL_STRAIGHT:
		case LARGE_STRAIGHT: 
		case YAHTZEE: 
			default: 
				return 0;
			
		}
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] diceValues;
	private int[] diceDebug;
	private int[] numberBucket;

}
