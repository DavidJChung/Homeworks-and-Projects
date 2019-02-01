package edu.manhattan.javaprog.midterm;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CodeCracker {
/**
 * <h1> Code Cracker </h1>
 * <b> This method is used to check user guess, compare with the generated code and output feedback. /r This method also pass the status of the game back to main inorder to keep it running or terminate it </b>
 * @param Codeword gets generated code word passed from the CodewordGen Class through the main
 * @param Guess gets user guess from main
 * @return Returns status of game to main
 */
 public boolean GuessnCheck(int[] Codeword, String Guess){ 
	int[] code = Codeword;
	int b_count = 0 ,w_count= 0;
	boolean gamestatus = false, val_flag=false;
	String playerguess = Guess;
	String wfeedback,bfeedback;
	String[] digits = playerguess.split("\\s");
	
		for(String x: digits) { 
			if (!validate(x)){
				val_flag = true;
				break;
			}
		}
		if(digits.length != 4 || val_flag) {
			System.out.println("you entered the wrong number of digits or entered an invalid digit, you loose a turn");
		}else{
			for (int i = 0; i < 4; i++) {
				if(code[i]==Integer.parseInt(digits[i])) {
						b_count++;
				}else for (int j = 0; j < 4; j++ ) {
					 if(code[i]==Integer.parseInt(digits[j])) {
						w_count++;
					}
				}
			}
			switch (w_count) {
			case 1:
				wfeedback = "W ";
				break;
			case 2:
				wfeedback = "W W ";
				break;
			case 3:
				wfeedback = "W W W";
				break;
			case 4:
				wfeedback = "W W W W";
				break;
			default:
				wfeedback = "";
				break;
			}
			switch (b_count) {
			case 1:
				bfeedback = "B ";
				break;
			case 2:
				bfeedback = "B B ";
				break;
			case 3:
				bfeedback = "B B B";
				break;
			case 4:
				bfeedback = "B B B B";
				gamestatus = true;
				break;
			default:
				bfeedback = "";
				break;
			}
			System.out.println("Feedback: \r" + bfeedback + wfeedback );
	}
		return (gamestatus);
	}
static boolean validate (String s) {
		String regex = "[0-9]";
		return (Pattern.matches(regex, s));
	}
}

	


