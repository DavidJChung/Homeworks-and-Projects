package tbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tbs.TreeSearch.node;
/**
 * 
 * @author David
 *
 */
/**
 * 
 * <h1> Balancing Binary Search Tree </h1> 
 * <p>This program is supposed read in a list of words create an unbalanced tree, balance that tree, then search for a given query word. The program then compares the time it takes to search for a word using the binary tree vs a linear search. Unfortunately I was not able to get the program to fully work even with the extended time, I ask you to please spare my grade as I spent countless hours trying to debug, reconstruct, draw out, and psudocode this project. I've finally gave up after getting it to partially be able to search for a query on the tree. I aplogize for the mediocore work and promise the final will not be this bad </p>  
 *
 */

public class TreeSearch {
	class node { 							// tree node object can serve as leaf or node

		String word;
		node leftChild, rightChild, parent;
		Integer value;
		int leftw=leftWeight(), rightw=rightWeight(), weight= weight();
		
		public int min_val(String query){
			if (leftChild == null) {
				if(rightChild == null) {
					return(query.compareTo(word));	
				}else {
					return(rightChild.min_val(query));
				}
			}else {
				return(leftChild.min_val(query));
			}
		}

		public int max_val(String query){
			if (rightChild == null) {
				if(leftChild == null) {
					return(query.compareTo(word));
				}else {
					return(leftChild.max_val(query));
				}
			}else {
				return(rightChild.max_val(query));
			}
		}

		public int weight(){

			return(leftWeight()+ rightWeight()+1);
		}

		public int leftWeight() {
			if(leftChild == null) {
				return 0;

			} else {
				return(leftChild.weight());
			}
		}

		public int rightWeight() {
			if (rightChild == null) {
				return 0;
			}else {
				return(rightChild.weight());
			}
		}

		/**
		 * 
		 * @param word
		 * <p> contains string assigned to node, used only in leaves </p>
		 * 
		 * @param index
		 * <p> contains index of the string assigned to node for later organization , used only in leaves</p>
		 */
		node(String word, Integer value){ 			// node constructor

			this.word = word;
			this.value = value;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;
		}
	}

	node root;
	node critnode;
	class link {								// object for link, I used a linked list instead of an ArrayList since this was made before the algorithm was give since it's still a linear search im keeping it
		String word;
		link next;
		int index;

		link(String word,int index){ 					// link constructor
			this.word = word;
			this.index = index;
			this.next = null;
		}
	}
	link head;
	public void addLink (String word,int index) { 		// adds link to linked list
		link newlink = new link(word,index);

		if (head == null) {
			head = newlink;

		}else {
			link focusLink;

			if(head.next==null) {
				head.next = newlink;

			}else {
				focusLink = head.next;

				while(focusLink.next != null) {
					focusLink = focusLink.next;
				}

				focusLink.next = newlink;
			}
		}
	}
	/**
	 * <h1>getHeight</h1>
	 * <p>This returns the height at a given node, used for finding critnode and checking if tree is balanced in one iteration of my code</p>
	 * 
	 */ 

	public int getHeight(node focusnode) {
		if (focusnode == null) {
			return -1;
		}

		int lefth, righth;
		lefth = getHeight(focusnode.leftChild);
		righth = getHeight(focusnode.rightChild);

		if (lefth > righth) {
			//System.out.println(lefth + " " + focusnode.word);
			return lefth + 1;
		} else {
			return righth + 1;
		}
	}

	//int i=0;
	/**
	 * <h1>balanceTree</h1>
	 * <p>This method using the focusnode passed into it uses the findCritNode method and uses the resulting node to balance the tree. By reassigning the nodes of the root to the critnode in a certain sequence we are able to emulate the pulling up of the root which we then recursively do the same for all the children of the tree. I spent the majority of my time working on this and even now im not sure if its fully working. I really tried my best on this project and even asked alot of people for help but could not get it fully working. Please spare my grade.</p>
	 * 
	 */

	public void balanceTree(node focusnode) {		// using weights of right and left branches of current root determines if tree needs re-balancing

		node prevroot, newroot,newrootLC , newrootRC, newrootPar, prevrootLC ;
		prevroot = focusnode;
		prevrootLC = focusnode.leftChild;
		
		int treeBalance = focusnode.leftWeight() - focusnode.rightWeight();
		System.out.println(treeBalance);
		//iOtraverse(focusnode);     used for debugging
		findCritNode(focusnode,Math.ceil(getHeight(focusnode)/2));
		if(Math.abs(treeBalance)> 2 ) {		// check weights on both sides of current root


			//System.out.println("critnode: " + critnode.rightChild.word + critnode.rightChild.value); // outputs critnode used for debugging
			newrootRC = critnode.rightChild;
			newrootPar = critnode.parent;
			newrootLC = critnode.leftChild;
			newroot = critnode;

			critnode.rightChild = prevroot;
			critnode.leftChild = newrootLC;
			
			critnode.parent = focusnode.parent;
			focusnode.parent = critnode;
			
			focusnode.leftChild = prevrootLC;
			prevrootLC.parent = focusnode;
			newrootLC.parent= critnode;	
			
			newrootPar.leftChild = newrootRC;
			newrootRC.parent = newrootPar;	
			
			if(prevroot.equals(root)) {
				root=critnode;
			}
			
			focusnode = critnode;

			if(focusnode.leftChild!=null) {
				if(focusnode.leftChild.weight>1){	
					balanceTree(focusnode.leftChild);
				}
			}

			if(focusnode.rightChild!=null) {
				if(focusnode.rightChild.weight>1) {
					balanceTree(focusnode.rightChild);
				}
			}
			// Used for Debugging
			//iOtraverse(focusnode);				
			//System.out.println( i++);
			//System.out.println(treeBalance + " " + root.rightChild.weight()+ " " +  root.leftChild.weight());
		}
	}
	private void findCritNode(node focusnode, double treeBalance) {			// finds crit node that will become new root
		if((treeBalance) == getHeight(focusnode)) {  					// uses focusnode height compared to current tree balance to find critnode
			critnode = focusnode;
		}else {
			findCritNode(focusnode.leftChild,treeBalance);				// uses recursion to search for node	
		}	
	}

	/**
	 * <h1>treeSearch</h1>
	 * <p>This method searches for the query word in the tree, after extensive debugging (about a week and a half) im not too sure as to why it wasn't finding every word </p>
	 * 
	 */

	private void treeSearch (String query, node matchnode) { 		// using values of the nodes searches through tree to find query string
		boolean found = false;
		String word;
		// check min and max values
		double start = System.nanoTime();	// time search
		 if (matchnode != null) {
			if(getHeight(matchnode)==0 && matchnode.word!=query) {
			
			}else if(matchnode.max_val(query)==0) {

				found = true;
			}else if(matchnode.min_val(query)==0) {
				found = true;
			}else if(matchnode.max_val(query)<matchnode.min_val(query)) {
				treeSearch(query,matchnode.leftChild);

			}else if (matchnode.max_val(query)>matchnode.min_val(query)) {			// use min max values to find matches using .compareTo
				treeSearch(query,matchnode.rightChild);
			}
		}
		if (!found) {
			System.out.println("The word " + query + " was not found in the tree");
			System.exit(0);
		}else if(found){
			double finish = System.nanoTime();
			double timeElapsed = (finish - start) / 1000000;
			word= matchnode.rightChild.word;

			System.out.println("By tree search the word " + word + " was found " + " in " + timeElapsed + " ms");

		}

	}/**
	 * <h1>listSearch</h1>
	 * <p>Searches for the entered query word and outputs time taken to do the search</p>
	 * 
	 */ 

	private void listSearch(String query) {		// using linked list searches through array to find query string
		link matchlink = head;
		double start = System.nanoTime();	// time search

		while (matchlink != null) {		// search through list to find query 

			if( query.equals(matchlink.word)){
				break;

			} else if(matchlink.next == null) {
				System.out.println("The word " + query + " was not found in list");
				System.exit(1);

			}else{
				matchlink = matchlink.next;	

			}
		}

		double finish = System.nanoTime();
		double timeElapsed = (finish - start) / 1000000;
		System.out.println("By linear search the word " + matchlink.word + " was found at index " + matchlink.index +  " in " + timeElapsed + " ms" );

	}	
/**
 *<h1> addLeaf </h1>
 *<p> Adds leaf that contains only the word and value, but the value was only used for debugging </p>
 * @param word
 * @param value
 */
	private void addLeaf (String word, Integer value) { 			//adds leaf to tree 
		node newleaf = new node(word, value);

		if (root.leftChild == null ) {			//adds two leaves for first node
			root.leftChild = newleaf;
			newleaf.parent = root;
		} else if(root.rightChild == null){		//adds new leaf to right child after first node
			root.rightChild = newleaf;
			newleaf.parent = root;
		}

	}
	/**
	 *
	 * <h1>addNode</h1>
	 * <p> creates node that has no value or word </p>
	 *
	 * 
	 **/
	private void addNode() {		// adds node to tree
		node newnode = new node(null, null);

		if (root == null) {		// if no previous root set new node as root

			root = newnode;


		} else if (root != null){		// if there exists a root make new node root and prevroot leftchild of new
			node prevroot = root;


			newnode.leftChild = prevroot;
			newnode.parent = prevroot.parent;
			prevroot.parent = newnode;
			root = newnode; 
		}
	}
/**
 * <h1> Main </h1>
 * <p> executes the functions </p>
 * @param args
 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		TreeSearch TS = new TreeSearch();
		String query; 
		TS.buildTree(); // import words into linked list and build unbalanced tree

		TS.balanceTree(TS.root);
		TS.iOtraverse(TS.root);

		System.out.println("List of words has been constructed, enter desired query:");
		query = in.nextLine();
		in.close();


		TS.treeSearch(query,TS.root); 		// search for word by tree
		TS.listSearch(query);		// search for word by list 

	}
/**
 * 
 * <h1> buildTree </h1>
 * <p> Builds unbalanced tree from the linked list </p>
 * 
 */
	private void buildTree() {			// Builds binary tree and linked list using 1000words.txt 
		try {

			Scanner in = new Scanner(new File("1000words.txt"));
			int i = 0;

			while(in.hasNext()){		// Builds list of words into linked list 

				addLink(in.next(),i);
				i++;
			}
			if(i < 1000) {				// checks if 1000 words in list
				System.out.println("There is not enough entry words ");
				System.exit(1);
			}
			in.close();
		} catch (IOException e) {

			System.out.println("Could not read file ");
		}

		link l = head;

		while (l != null) {
			System.out.println("word: "+ l.word + " of index " + l.index + " was added to tree");

			if(root == null) { 			// If no previous root, create one and assign two leaves
				addNode();
				addLeaf(l.word,l.index);

				l=l.next;
				addLeaf(l.word,l.index);
				System.out.println("word: "+ l.word + " of index " + l.index + " was added to tree");

			}else if(root != null) {		// If root exists addnode and leaf
				addNode();
				addLeaf(l.word,l.index);

			}else if(l.next != null) {		// If next on list is null break of out loop after adding new leaf
				addNode();
				addLeaf(l.word,l.index);
				break;
			}
			l = l.next;

		}

	}
	private void iOtraverse(node focusnode) { 				// in order traversal to test if tree was properly implemented
		if(focusnode != null) {

			iOtraverse(focusnode.leftChild);

			if(focusnode.word != null) {
				System.out.print( focusnode.word + " " + focusnode.value + " " + "\r");
			}

			iOtraverse(focusnode.rightChild);

		}

	}
}
