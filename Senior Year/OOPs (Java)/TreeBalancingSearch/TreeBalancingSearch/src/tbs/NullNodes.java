package tbs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Thomas Oakes
 *
 */
/**
 * <h1>Balancing an Unbalanced Binary Search Tree</h1>
 * <p>This program creates a linked list from a list of words in a txt file called RandomWords.txt.  It then takes the linked list and converts it to an unbalanced binary tree.  Once this is created, the program balances it so that it can search from it.  It then searches both the balanced binary tree and the linked list, and compares the time it takes for each to find the word</p>
 * 
 */

public class NullNodes {
	
	static NullNodes theTree = new NullNodes();
	static Node root;
	static Node balancePoint;
	static Node pivot;
	
	/**
	 * <h1>lsitSearch</h1>
	 * <p>This returns the time that it takes to search for a word linearly in a linked list.</p>
	 * 
	 */
	public static long listSearch(String word, LinkedList<String> RandomWordsList) {
		long startTime = System.nanoTime();
			
		for(int i=0; i<RandomWordsList.size();i++) {
			if(RandomWordsList.get(i).equals(word)) {
				long endTime = System.nanoTime();
				return (endTime-startTime);
			}
		}
		
		return 0;
	}

	/**
	 * <h1>Post Order Traversal of the Tree</h1>
	 * <p>This will output the tree in post order. This was mainly used for debugging of the tree balancing function.</p>
	 * 
	 */
	public void postOrderTraverseTree(Node focusNode) {
		if (focusNode !=null) {
			
			postOrderTraverseTree(focusNode.leftChild);
			postOrderTraverseTree(focusNode.rightChild);
			System.out.print(focusNode+ " ");
						
		}
	}
	
	/**
	 * <h1>In Order Traversal of the Tree</h1>
	 * <p>This will output the tree in in order. This was mainly used for debugging of the tree balancing function.</p>
	 * 
	 */
	public void inOrderTraverseTree(Node focusNode) {
		if (focusNode !=null) {
			
			inOrderTraverseTree(focusNode.leftChild);
			System.out.print(focusNode+ " ");
			inOrderTraverseTree(focusNode.rightChild);
						
		}
	}
	
	
	/**
	 * <h1>BalanceTree</h1>
	 * <p>This method takes the unbalanced binary tree and balances it.  It pulls up the root and sets all of its children and parent, and then uses recursion to check the left and right side of the focusNode to see if it also needs to be balanced.</p>
	 * 
	 */
	public static void BalanceTree(Node focusNode, char letter) {
		
		int leftH = findHeight(focusNode.leftChild);
		int rightH = findHeight(focusNode.rightChild);
		int bFactor=leftH-rightH;
		
		//used for debugging
		//System.out.println(bFactor+" "+leftH+" "+rightH);
		
		if(bFactor>1||bFactor<-1) {
			
			Node critNode = search4PivotNode(focusNode, Math.ceil(findHeight(focusNode)/2.0));
			Node tempRightChild = critNode.rightChild;
			Node tempLeftChild = critNode.leftChild, parent = critNode.parent;
			Node tempFocusNode = focusNode;//<==========
			Node oldParent = focusNode.parent;
			
			//used for debugging
			//System.out.println(critNode.leftChild.parent);
			
			critNode.rightChild=tempFocusNode;
			critNode.leftChild=tempLeftChild;
			critNode.parent=tempFocusNode.parent;
			
			if(letter=='r') {
				tempFocusNode.parent.rightChild=critNode;
				critNode.parent.rightChild=critNode;
			}
			else if(letter=='l') {
				tempFocusNode.parent.leftChild=critNode;
				critNode.parent.leftChild=critNode;
			}
			
			tempFocusNode.parent=critNode;
			tempLeftChild.parent=critNode;
			parent.leftChild=tempRightChild;
			tempRightChild.parent=parent;
			
			focusNode=critNode;
			
			if(focusNode.leftChild!=null) {
				if(focusNode.leftChild.weight()>1){//may need to be > 0
					BalanceTree(focusNode.leftChild, 'l');
				}
			}
			if(focusNode.rightChild!=null) {
				if(focusNode.rightChild.weight()>1) {
					BalanceTree(focusNode.rightChild, 'r');
				}
			}
		}
		
	}
	
	/**
	 * <h1>findHeight</h1>
	 * <p>This will return the height of the passed node.</p>
	 * 
	 */ 
	 static int findHeight(Node focusNode) {
	    if (focusNode == null) {
	        return -1;
	    }

	    int lefth, righth;
	    lefth = findHeight(focusNode.leftChild);
	    righth = findHeight(focusNode.rightChild);

	    if (lefth > righth) {
	        return lefth + 1;
	    } else {
	        return righth + 1;
	    }
	}
	
	 /**
	 * <h1>search4PivotNode</h1>
	 * <p>This will return the pivot node. This was used to set the first pivot node.</p>
	 * 
	 */
	private static Node search4PivotNode(Node focusNode, double pivot) {
		int height=findHeight(focusNode);
		
		if(height == pivot) {
			return focusNode;
		}
			
		return search4PivotNode(focusNode.leftChild, pivot);
	}
	
	/**
	 * <h1>search4Node</h1>
	 * <p>This searched for a node with the matching word. It uses the min and max values of each side of the focusNodes children.</p>
	 * 
	 */
	public static Node search4Node(Node focusNode, String word) {
				
		String max = focusNode.leftChild.maxVal();
		String min = focusNode.rightChild.minVal();
		
		if(findHeight(focusNode)==0&& focusNode.name!=word) {
			return null;
		}
		if(word.compareTo(max)==0) {
			return focusNode;
		}
		else if(word.compareTo(max)<0) {
			return search4Node(focusNode.leftChild, word);
		}
		else if(word.compareTo(max)>0) {
			return search4Node(focusNode.rightChild, word);
		}
				
		return focusNode;
	}
	
	/**
	 * <h1>createTree</h1>
	 * <p>This method creates the unbalanced binary tree. Each leaf is a node in the linked list and all other nodes are null nodes.</p>
	 * 
	 */
	private static void createTree(Node focusNode, LinkedList<String> LL, int index) {
		
		focusNode.key= 0;
		focusNode.name=null;
		focusNode.rightChild = new Node(index, LL.get(index));
		focusNode.rightChild.parent=focusNode;
		
		index--;
		
				
		if(root.rightChild==null) {
			root=focusNode;
					
		}
		
		if(index==0) {
			focusNode.leftChild = new Node(index, LL.get(index));
			focusNode.leftChild.parent=focusNode;
		}
		else if(index>0) {
			focusNode.leftChild = new Node(0, null);
			focusNode.leftChild.parent=focusNode;
			createTree(focusNode.leftChild, LL, index);
		}
	}
	
	private static Node findRoot(Node focusNode) {
		
		while(focusNode.parent.name!="rootsRoot") {
			focusNode=focusNode.parent;
		}
				
		return focusNode;
	}
	
	/**
	 * <h1>MAIN</h1>
	 * <p>This is the main function.  In here, the link list is created by reading in all the values from the RandomWord.txt file.  It then uses this to create the unbalanced tree.</p>
	 * <p>Then, it passes the tree to the BalanceTree function to be balanced.  Then it searched the tree and the linked list, and compares the time it takes for each data structure to find the word.</p>
	 */
	public static void main(String[] args) throws Exception {
		
		//Imports the list of random words from RamdomWords.txt to a linked list
		LinkedList<String> RandomWords = new LinkedList<String>();
		
		File file = new File("D:\\Eclipse\\eclipse\\Balancing Binary Trees\\src\\edu\\manhattan\\javaprog\\RandomWords.txt"); 
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String st; 
		while ((st = br.readLine()) != null) {
			RandomWords.add(st);
		}
		
		//Creating Unbalanced Tree
		root = new Node(0, null);
		root.parent=new Node(0, "rootsRoot");
		root.parent.leftChild=root;
		root.parent.rightChild=root;		
		
		int wordListSize = RandomWords.size();	
		createTree(theTree.root, RandomWords, wordListSize-1);
		pivot=search4PivotNode(root, Math.ceil(findHeight(root)/2.0));
		
		//These were used in debugging.  To check the trees, just uncomment the desired tree traversal to see the tree output
		//theTree.inOrderTraverseTree(theTree.root);
		//System.out.printf("\n");
		//theTree.postOrderTraverseTree(theTree.root);
		//System.out.printf("\n");
		
		//Balancing the Tree
		BalanceTree(theTree.root, 'r');
		theTree.root=findRoot(theTree.root);
		
		//These were used in debugging.  To check the trees, just uncomment the desired tree traversal to see the tree output
		//theTree.inOrderTraverseTree(theTree.root);
		//System.out.printf("\n");
		//theTree.postOrderTraverseTree(theTree.root);
		//System.out.printf("\n");
		
		//Search For Node
		System.out.print("Enter a word to be searched: ");
		Scanner input = new Scanner(System.in);
		String word = input.next();
		
		long treeTime = 0, listTime = 0;
		
		if(theTree.listSearch(word,RandomWords)==0) {
			System.out.println("Word was not found");
		}
		else {
			long startTime = System.nanoTime();
			Node foundNode = search4Node(theTree.root, word);
			long endTime = System.nanoTime();
			treeTime = (endTime-startTime);
			if(foundNode==null) {
				System.out.println("Word was not found");
			}
			else {
				System.out.println("It took "+ treeTime +" nanoseconds to find "+word+" using the Tree");
			}
			
			listTime=listSearch(word, RandomWords);
			if(listTime==0) {
				System.out.println("Word was not found");
			}
			else {
				System.out.println("It took "+ listTime +" nanoseconds to find "+ word +" using the Linked List");
			}
			
		}
	}	
	
}


/**
 * <h1>Node</h1>
 * <p>This is the Node class.  This is where all the values of the Trees Nodes are created.  This lists off each parameter and function of the nodes.</p>
 * 
 */
class Node {
	
	int key;
	String name;
	
	Node leftChild;
	Node rightChild;
	Node parent;
	String minVal = minVal();
	String maxVal=maxVal();
	int lWeight = lWeight();
	int rWeight = rWeight();
	int weight = weight();
	
	Node(int key, String name){
		
		this.key = key;
		
		this.name = name;
	}
	
	public String toString() {
		
		return name;
	}
	public String minVal() {
		if(leftChild==null) {
			return name;
		}
		else {
			return leftChild.minVal();
		}
	}
	public String maxVal() {
		if(rightChild==null) {
			return name;
		}
		else {
			return rightChild.maxVal();
		}
	}
	public int lWeight() {
		if(leftChild == null) {
			return 1;
		}
		else {
			return leftChild.weight+1;
		}
	}
	public int rWeight() {
		if(rightChild == null) {
			return 1;
		}
		else {
			return rightChild.weight+1;
		}
	}
	public int weight() {
		return lWeight()+rWeight()+1;
	}
	
}

