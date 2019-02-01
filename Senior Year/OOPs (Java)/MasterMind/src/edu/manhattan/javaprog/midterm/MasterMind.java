package edu.manhattan.javaprog.midterm;
/** 
 * 
 * @author David J. Chung
 *
 */

import edu.manhattan.javaprog.midterm.CodewordGen;
import java.util.Scanner;
import edu.manhattan.javaprog.midterm.CodeCracker;

public class MasterMind {	
	
	private static int[] code;
	private int numTries;
	private boolean state;	
	
	public MasterMind() {
		code = new int[4];
		numTries= 0;
		state = false;
		
	}
	/**
	 * <h1> Main </h1>  
	 * <p> The Main of this Class executes the program by bringing together all the necessary classes and passing it to all private variables and outer classes </p>
	 * 
	 */
	 public static void main(String[] args) {
		 System.out.println("Welcome to MasterMind! \r In this game you will be trying to guess the randomly generated 4-digit non-repeating code-word \r you will have ten tries to guess each of the numbers \r if you get one right, a W will be feed back indicating this! \r if you get one right AND in the right position a B will appear!"
		 		+ "\r if you enter too many or any non-integer digits you will lose a turn! \r Good Luck!");
		 // create instance
		 Scanner in = new Scanner(System.in);
		 CodewordGen gen = new CodewordGen();
		 MasterMind newgame= new MasterMind();
		 code = gen.CodeGen();
		 CodeCracker game = new CodeCracker();
		 for(int tries = 1; tries<=10; tries++){
			 System.out.println("Please enter your four digit guess: \r");
			 String guess=in.nextLine();
			 newgame.setNumTries(tries);
			 newgame.setState(game.GuessnCheck(code, guess));
			 if (newgame.isState()) {
				 System.out.println("You won! \r Tries remaining: " + (10-tries));
				 break;
			 }
			 System.out.println("Try Again! \r Tries remaining: " + (10-tries));
		 }
		 in.close();
		if ( newgame.getNumTries()== 10) {
		 System.out.println(newgame.getNumTries() + "remaining! You Lose :( !");
		 }
		 
	 }

	

	/**
	 * @return the numTries
	 */
	public int getNumTries() {
		return numTries;
	}

	/**
	 * @param numTries the numTries to set
	 */
	public void setNumTries(int numTries) {
		this.numTries = numTries;
	}

	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}
}

