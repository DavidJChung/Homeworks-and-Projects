package RPN_Calculator;
/** @author David J. Chung
 */
import java.util.Scanner;
import java.util.regex.Pattern;

	// Grab string of numbers and operators from I/O stream using scanner library
	// Tokenize string using string split w/ regular experessions (to denote what were looking for)
	// Apply cases for operations and pushing #'s on to string
	// output result from stack

class Stack{
	private int[] stack;
	private int pointer;
	
	public Stack() {
			stack = new int [100];
			pointer = 0;
	}	
	
	public void push(int a) {
			stack[pointer++] = a; // auto increments pointer value after the function is executed
	}
	
	public int pop() {
		return stack[--pointer]; // auto decrements pointer value before the fuction is executed
	}
}
public class RPNcalculator {

static boolean validate (String s) {	// validates if token is number or operator
		String regex = "[*/+\\-]|[0-9][0-9]*";
return (Pattern.matches(regex, s));
}
static boolean validate_num(String s) { // validated if token is number
	String regex = "[0-9][0-9]*";
return (Pattern.matches(regex, s));
}
public static void main(String[] args) {
	
	int operand_count=0;
	int operator_count=0;
	
	Scanner in = new Scanner (System.in);
	Stack RPNstack = new Stack();
	
	String inString = in.nextLine();
	String[] tokens = inString.split("\\s");

		for (int i = 0; i < tokens.length; i++){
			if (validate(tokens[i])) {
				if (validate_num(tokens[i])) {
						int tokenInt = Integer.parseInt(tokens[i]);
						RPNstack.push(tokenInt);
						operand_count++;
				
				}else {
					operator_count++;
						switch( tokens[i].charAt(0)){
						case '+':
							RPNstack.push(RPNstack.pop() + RPNstack.pop()); // pop1 pop2 add (pop2+pop1) push
							break;
						case '-':
							RPNstack.push(RPNstack.pop() - RPNstack.pop()); // pop1 pop2 sub (pop2-pop1) push
							break;
						case '/':
							RPNstack.push(RPNstack.pop() / RPNstack.pop());	// pop1 pop2 divide (pop2/pop1) push
							break;
						case '*': 
							RPNstack.push(RPNstack.pop() * RPNstack.pop());	// pop1 pop2 multiply (pop2*pop1) push
							break;
						default:
							break;
						}
				}
		
				}else {
					System.out.println(tokens[i] + " is not valid");
				}
			
			}
			
			if(operator_count == operand_count - 1){
				System.out.println(RPNstack.pop());
			}else {
				System.out.println("There are not enought operators or operands, needs to be one less operator than operand");
			}
			in.close();		
}
}


