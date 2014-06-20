/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private static final int NUM_OF_GUESSES = 8;
	
	private String gameWord;
	private HangmanLexicon word = new HangmanLexicon();
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private String playerWord = "";
	private int guessRemain;
	private char characterGuess;
	private int wordLength;
	private HangmanCanvas canvas; 
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}

    public void run() {
		initializeGame();
		while (guessRemain > 0){
			playGame();
	    	//Check if player guessed all letters
	    	if (!checkForHit(playerWord, '-')){
	    		break;
	    	}
		}
		checkWinLose();
	}
    
    private void initializeGame(){
    	guessRemain = NUM_OF_GUESSES;
		println ("Welcome to Hangman");
		gameWord = word.getWord(rgen.nextInt(0,word.getWordCount()));
		for (int i=0;i<gameWord.length();i++){
			playerWord = "-" + playerWord;
		}
		wordLength = gameWord.length();
		canvas.reset();
    	canvas.displayWord(playerWord);
		
		//DEBUG CODE 
		println (gameWord);
    }
    
    private void playGame(){
    	//Displays playerWord and Number of Remaining Guesses
    	displayStatus();
    	getGuess();
    	processGuess();
    }
    
    private void displayStatus(){
    	println ("The word now looks like this: " + playerWord);
    	println ("You have " + guessRemain + " guesses left.");   	
    }
    
    private void getGuess(){
    	// MAKE SURE TO CHECK FOR LOWER CASE
    	do{
        	String playerInput = readLine("Your guess: ");
        	characterGuess = playerInput.charAt(0);
        	characterGuess = checkCase (characterGuess);
    	} while (!validEntry(characterGuess));
    	
    }
    
    private char checkCase(char ch){
    	if (ch >= 'a' && ch <= 'z'){
    		return ch = Character.toUpperCase(ch); 
    	}
    	return ch;
    }
    
    private boolean validEntry(char ch){
    	if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private void processGuess(){    	  	
    	if (!checkForHit(gameWord, characterGuess)){
    		//Incorrect Guess
    		guessRemain--;
    		canvas.noteIncorrectGuess(characterGuess, guessRemain);
    		println ("There are no " + characterGuess + "'s in the word.");
    	} else {
    		//Correct Guess
    		println ("That guess is correct. ");
    		reformatPlayerWord();
    	}
    }

    private boolean checkForHit(String gameStr, char ch){
       	for (int i=0; i<wordLength;i++){
    		if (gameStr.charAt(i) == ch) return true;
    	}	
    	return false; 
    }
    
    private void reformatPlayerWord(){
    	for (int i=0; i<gameWord.length();i++){
    		if (gameWord.charAt(i) == characterGuess) {
    			playerWord = playerWord.substring(0, i) + characterGuess + playerWord.substring(i+1, wordLength);
    		}
    	}	
    	canvas.displayWord(playerWord);
    }
    
    private void checkWinLose(){
    	if (guessRemain == 0){
    		println ("You're Completely Hung. ");
    	} else { 
    		println ("You guessed the word: " + gameWord);
    		println ("You win, but you probably cheated.");
    	}
    }
   
}
