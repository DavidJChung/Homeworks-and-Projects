package prefixtopost;
import java.util.*;
import java.util.regex.*;
/**
 * 
 * @author David
 * 
 * <h1> Infix to Postfix notation </h1>
 *
 */
public class Tree { 
	/**
	 * 
	 * <p> This class contains an object node used to create a tree from a series of scans </p>
	 *
	 */
	class node { 							// tree node object
		
			String term;
			int index;
			node leftChild;
			node rightChild;
			/**
			 * 
			 * @param term
			 * <p> contains string assigned to node </p>
			 * 
			 * @param index
			 * <p> contains index of the character assigned to node for later organization </p>
			 */
			node(String term, int index){
				
				this.term = term;
				this.index = index;
				
			}
	}
	node root;
	/**
	 * 
	 * @method AddNode
	 * <p> Adds node to binary tree with given term and index </p>
	 */
	public void addNode( String term, int index) {		// adds node to tree
		
		node newnode = new node(term,index);
		
		if (root == null) {
			
			root = newnode;
			
		} else {
			
			node focusnode = root;
			node parent;
			
			while(true) {
					parent = focusnode;
					
					if (index < focusnode.index) {
						focusnode = focusnode.leftChild;
						
						if (focusnode == null) {
							parent.leftChild = newnode;
							return;
						}
					}else {
						focusnode = focusnode.rightChild;
						
						if(focusnode == null) {
							parent.rightChild = newnode;
							return;
						}
					}
			}
		}
	}
	/**
	 * @method POtraverse
	 * <p> Allows for post order traversal through tree using recursion to return given expression in RPN </p>
	 * 
	 * @param focusnode
	 * <p> node currently being focused on </p>
	 */
	public void POtraverse(node focusnode) { 				// Post order traversal to attain RPN notation
		if(focusnode != null) {
			
			POtraverse(focusnode.leftChild);
			POtraverse(focusnode.rightChild);
			
			
			System.out.print(focusnode.term + " ");
			
		}
		
	}
	/**
	 * 
	 * @method errorCheck
	 * <p> Checks for invalid characters, division by zero, and dangling operators/integers </p>
	 */
	public static void errorCheck (String[] instrng) {      // Checks for invalid characters, division by zero, and dangling operators/integers
		int operand = 0;
		int operator = 0;
		int parcount = 0;
		
		for (int i = instrng.length - 1; i>=0; i-- ) {
			switch (priorityCheck(instrng[i])){
			
			case 0:
				System.exit(1);
				break;
			case 1: 
				operator++;
				break;
				
			case 2:
				operator++;
				break;
				
			case 3:
				operand++;
				break;
				
			default:
				break;
			}
			
			if(instrng[i].equals("(")) {
				parcount++;
				
			}else if(instrng[i].equals(")")) {
				parcount--;
				
			}
			if((instrng[i].equals("/") ) && (instrng[i+1].equals("0"))) {
				System.out.print("Division by zero attempted");
				System.exit(1);
			}
		}
		if(operand != operator+1) {
			System.out.print("There exist dangling operators or operands ");
			System.exit(1);
		}
		if (parcount != 0) {
			System.out.print("There is an incomplete parentheses in the entered string ");
			System.exit(1);
		}
	}
	/**
	 * @method scanOne
	 * <p> Scans for terms within parenthesis and operands using priorityCheck method </p>
	 * 
	 */
	public static void scanOne(String[] inStrng) {			// scans for parenthesis and number terms
		Tree tree = new Tree();
		boolean skipPar = false;
		
		for (int i = inStrng.length - 1; i >= 0; i--) {
			if(inStrng[i].equals(")")){
				skipPar = true;
				
			}else if(inStrng[i].equals("(")) {
				skipPar = false;
			}
			if (skipPar==true) {
				if ((priorityCheck(inStrng[i]) == 1)){
					tree.addNode(inStrng[i],i);
				}
			}
		}

		for (int i = inStrng.length - 1; i >= 0; i--) {
			if(inStrng[i].equals(")")){
				skipPar = true;
				
			}else if(inStrng[i].equals("(")) {
				skipPar = false;
			}
			if (skipPar == true) {
				if ((priorityCheck(inStrng[i]) == 2)){
					tree.addNode(inStrng[i],i);
				}
			}
		}
	
		for(int i = inStrng.length - 1; i >= 0; i--) {
			if(inStrng[i].equals(")")){
				skipPar = true;
				
			}else if(inStrng[i].equals("(")) {
				skipPar = false;
			}
			if((skipPar) && (priorityCheck(inStrng[i]) == 3)) {
					tree.addNode(inStrng[i], i);

			}
		}
		for (int i = inStrng.length - 1; i >= 0 ; i-- ) {

			if(inStrng[i].equals(")")) {
				skipPar = true;

			} else if (inStrng[i].equals("(")) {
				skipPar = false;
			}
			if (skipPar==false) {
				if(priorityCheck(inStrng[i]) == 3) {
					tree.addNode(inStrng[i],i);
				}
			}
		}
		tree.POtraverse(tree.root);
	}
	/**
	 * @method scanTwo
	 * <p> Scans for %,/ and * operators using priorityCheck method </p>
	 * 
	 */
	
	public static void scanTwo ( String[] inStrng) {		// scans for multiplication or division
		boolean skipPar = false;
		Tree tree = new Tree();

		for (int i = inStrng.length - 1 ; i >= 0 ; i-- ) {

			if(inStrng[i].equals(")")) {
				skipPar = true;

			} else if (inStrng[i].equals("(")) {
				skipPar = false;
			}
			if (skipPar==false) {
				if(priorityCheck(inStrng[i]) == 1) {
					tree.addNode(inStrng[i],i);
				}
			}
		}
		tree.POtraverse(tree.root);
	}
	
	/**
	 * @method scanThree
	 * <p> Scans for + and - operators using priorityCheck method </p>
	 * 
	 */
	public static void scanThree (String[] inStrng) {  			// scans for + or - 
		Tree tree = new Tree();
		boolean skipPar = false;
		
		for (int i = inStrng.length - 1; i >= 0; i--) {
			if(inStrng[i].equals(")")){
				skipPar = true;

			}else if(inStrng[i].equals("(")) {
				skipPar = false;
			}
			if (skipPar==false){
				if(priorityCheck(inStrng[i]) == 2) {
					tree.addNode(inStrng[i],i);
				}
			}
		}
		tree.POtraverse(tree.root);
	}
	/**
	 * @method main
	 * <p> executes scans and errorCheck </p>
	 * 
	 */
	public static void main(String[] args) {					// main function executes scans and error checks
		
		
		Scanner in = new Scanner(System.in);
	
		System.out.print("Enter infix expression ");
		String[] infix = in.nextLine().split("\\s");
		in.close();
		
		errorCheck(infix);
		scanOne(infix);
		scanTwo(infix);
		scanThree(infix);
		
		
		System.out.print(" ");
		
		
	}
	/**
	 * @method priorityCheck
	 * <p> Scans for operators and operands and assigns them an integer based on priority. Used in all scans and errorcheck </p>
	 * 
	 */
static int priorityCheck (String term) {					// assigns integer value to a character based on priority
		int priority = 0;
		String regex = "[*%/]";
		
		if (Pattern.matches(regex, term)) {
			priority = 1;
			
		} else {
			regex =  "[+//-]";
			
			if (Pattern.matches(regex, term)) {
				priority = 2;
				
			} else {
				regex = "[0-9][\\.0-9]*";
				if (Pattern.matches(regex, term)) {
					priority = 3;
				} else {
					regex = "[()]";
					if (Pattern.matches(regex, term)) {
						priority = 4;
					} else {
						System.out.print(term + " is an invalid term");
					}
				}
			}
		}
		return(priority);
	}
}
