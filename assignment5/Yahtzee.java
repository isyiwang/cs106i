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
		upperScore = new int[MAX_PLAYERS];;
		lowerScore = new int[MAX_PLAYERS];
		bonusScore = new int[MAX_PLAYERS];
		usedCategories = new boolean[MAX_PLAYERS][TOTAL];
		//DEBUG 
		diceDebug = new int[N_DICE];
		for (int i = 0; i < N_DICE; i++){
			diceDebug[i] = 5;
		}
	}

	private void playGame() {
		for(int turns = 0; turns < NUMBER_OF_TURNS; turns++){
			for(int i = 1; i <= nPlayers; i++){
				initialRoll(i);
				secondAndThirdRoll(i);
				keepScore(i);
			}
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
		int category = 0;
		category = display.waitForPlayerToSelectCategory();
		while(!categoryValid(category, player)){
			display.printMessage("Invalid Category");
			category = display.waitForPlayerToSelectCategory();
		}
		setCategoryUsed(category, player);
		bucketizeRolls(diceValues);

		if (checkCategory(category)){
			//calculate the score 
			//update the score
			int score = calculateScore(category);
			display.updateScorecard(category, player, score);
			//debug: update scorecard for TOTALs category
			if(category <= SIXES){
				//update lower score
				upperScore[player-1] += score; 
				
			} else if(category <= CHANCE){
				//update upper score 
				lowerScore[player-1] += score;
			}
			//check for bonus score
			if(upperScore[player-1] >= BONUS_THRESHOLD){
				bonusScore[player-1] = BONUS_SCORE;
			}
		} else{
			display.updateScorecard(category, player, 0);
		}      
		displayScores(player);
	}

	private void displayScores(int playerInput){
		display.updateScorecard(UPPER_SCORE, playerInput, upperScore[playerInput-1]);
		display.updateScorecard(LOWER_SCORE, playerInput, lowerScore[playerInput-1]);
		display.updateScorecard(TOTAL, playerInput, lowerScore[playerInput-1]+upperScore[playerInput-1]+bonusScore[playerInput-1]);
		if(bonusScore[playerInput-1] == 35){
			display.updateScorecard(UPPER_BONUS, playerInput, bonusScore[playerInput-1]);
		}
	}
	
	private boolean categoryValid(int categoryInput, int playerInput){
		if(categoryInput < ONES || categoryInput == UPPER_SCORE || categoryInput == UPPER_BONUS
				|| categoryInput == LOWER_SCORE || categoryInput == TOTAL) return false;
		
		// debug: need to check if category is alrady selected or not!
		if (usedCategories[playerInput-1][categoryInput-1] == true) return false;		
		return true;
	}
	private void setCategoryUsed(int categoryInput, int playerInput){
		usedCategories[playerInput-1][categoryInput-1] = true;
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
			threeOfKind = checkThreeOfKind();
			return threeOfKind;
		case FOUR_OF_A_KIND:
			for (int i = 0; i < TOTAL_DICE_SIDES;i++){
				if (numberBucket[i] >= FOUR_ROLLS) return true;
			}
				return false;
		case FULL_HOUSE:
			threeOfKind = checkThreeOfKind();
			for (int i = 0; i < TOTAL_DICE_SIDES;i++){
				if (numberBucket[i] == TWO_ROLLS && threeOfKind) return true;
			}
			return false;
		case SMALL_STRAIGHT:
		case LARGE_STRAIGHT: 
			return checkStraight(categoryInput);
		case YAHTZEE: 
			return checkYahtzee();
		case CHANCE:
			return true;
			default: 
				return false;
		}
	}

	private boolean checkThreeOfKind(){
		for(int i = 0; i < TOTAL_DICE_SIDES;i++){
			if (numberBucket[i] >= THREE_ROLLS) return true;
		}
		return false;
	}
	private boolean checkStraight(int categoryInput){
		if(categoryInput == SMALL_STRAIGHT){
			if(numberBucket[0] == 0 || numberBucket[TOTAL_DICE_SIDES-1] == 0) return true;
			if(numberBucket[0] == 0 && numberBucket[1] == 0) return true;
			if(numberBucket[0] == 0 && numberBucket[TOTAL_DICE_SIDES-1] == 0) return true;
			if(numberBucket[TOTAL_DICE_SIDES-1] == 0 && numberBucket[TOTAL_DICE_SIDES-2] == 0) return true;
		} else if(categoryInput == LARGE_STRAIGHT){
			if(numberBucket[0] == 0 || numberBucket[TOTAL_DICE_SIDES-1] == 0) return true;
		}
		return false;
	}

	private boolean checkYahtzee(){
		for(int i = 0; i < TOTAL_DICE_SIDES; i++){
			if(numberBucket[i] == 5) return true;
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
			return 30;
		case LARGE_STRAIGHT: 
			return 40;
		case YAHTZEE: 
			return 50;
		case CHANCE:
			return calculateChanceScore();
			default: 
				return 0;

		}
			
			
	}

	private int calculateChanceScore(){
		int sum = 0;
		for(int i = 0; i < TOTAL_DICE_SIDES; i++){
			sum = (numberBucket[i] * (i+1)) + sum;
		}
		return sum;
	}
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] diceValues;
	private int[] diceDebug;
	private int[] numberBucket;
	private int[] upperScore;
	private int[] lowerScore;
	private int[] bonusScore;
	private boolean[][] usedCategories;

}