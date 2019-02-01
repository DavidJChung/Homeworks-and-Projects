package edu.manhattan.javaprog.midterm;

/** 
 * 
 * @author David J. Chung
 *
 */
/**
 * 
 * @param Codeword is the array that will contain the digits to be guessed
 * @param code is used to create and store random number to codeword array
 *
 */
public class CodewordGen {
	private static int[] Codeword;
	private static int code;
/**
 * <h1> Code Word Generator </h1>
 * <p> This Class is used to create the randomized 4-Digit code word the player must guess </p>
 * 
 *@return returns Codeword to be guessed
 */
	
public int[] CodeGen(){
		Codeword = new int [4];
		 for(int i = 0; i<4 ;i++) {
			 code = (int) (Math.random()*10);
			 Codeword[i] = code;}
			 while (Codeword[0] == Codeword[1]) {
					Codeword[1] = (int) (Math.random()* 10);
				}
				while (Codeword[0] == Codeword[2] || Codeword[1] == Codeword[2]) {
					Codeword[2] = (int) (Math.random()* 10);
				}
				while (Codeword[0] == Codeword[3] || Codeword[1] == Codeword[3] || Codeword[2] == Codeword[3]) {
					Codeword[3] = (int) (Math.random()* 10);
				}
return (Codeword);
}
}


