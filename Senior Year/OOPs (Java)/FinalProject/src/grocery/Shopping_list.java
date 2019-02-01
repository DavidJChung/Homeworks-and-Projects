package grocery;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import grocery.Produce;
/**
 * 
 * @author David
 *<h1> Shopping_List </h1>
 *<p> This class creates the shopping list that will be used to calculate the invoice from the data.txt file </p>
 *
 */
public class Shopping_list {
	static ArrayList<Produce> l = new ArrayList<Produce>();
/**
 * <h2>makeShoppingList</h2>
 * <p> This method constructs shopping list using the readList(); and sortList() methods </p>
 * 
 */
	public void makeShoppingList() {			//Constructs shopping list using the readList(); and sortList() methods
		readList();
		sortList();
	}

	/**
	 * <h2>readList<h2>
	 * <p> This method reads the data.txt file and builds an arraylist from its content </p>
	 */
	public void readList() {					//Reads data.txt and builds arraylist from content

		Scanner in;

		String[] tokens = null;
		try {
			in = new Scanner(new File("data.txt"));
			while (in.hasNext()) {
				try {

					String line = in.nextLine(); 
					tokens = line.split("\\s");
					
					Double quantity = Double.parseDouble(tokens[1]);
					Constructor c = Class.forName("grocery." + tokens[0]).getConstructor(Double.TYPE);
					l.add((Produce) c.newInstance(quantity));
			
				} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					System.out.println(
							"The item " + tokens[0] + " on your list isn't sold here and will not be added to your list");	//Disregards any items not in the list
				}
			}in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found ");
		}
	}
/**
 * <h2> sortList()</h2>
 * <p> Utilizing user defined collection sorting via custom compare classes, this method sorts the shopping list and merges any duplicates<p>
 * 
 * 
 */
	public void sortList() {								//Sorts List and eliminates any duplicate items adding their quantity to the previously existing one
		Collections.sort(l, new compareType());				//Utilizes user defined sort
		Collections.sort(l, new compareClassification());

		String prevType = null;
		for (int i = 0; i < l.size(); i++) {

			if (l.get(i).produceType() == prevType) {
				l.get(i - 1).qty += l.get(i).qty;
				l.remove(i);
			}
			prevType = l.get(i).produceType();
		}
		//System.out.println(l); 		// Used for debugging
	}
}
/**
 * 
 * @author David
 * 
 *<h1> compareType </h1>
 *<p> This class creates an object to be used for the collection sort. It implements the Comparator method using our Produce object to find the alphabetical order to sort by for produceType</p>
 */
class compareType implements Comparator<Produce> {			//Custom compare object that implements the Comparator class on our Produce object for sorting produceType
	public int compare(Produce a, Produce b) {

		return (a.produceType().compareTo(b.produceType()));
	}

}

/**
 * 
 * @author David
 * <h1> compareClassification </h1>
 *<p> This class creates an object to be used for the collection sort. It implements the Comparator method using our Produce object to find the alphabetical order to sort by for produceClassification</p>
 *
 */
class compareClassification implements Comparator<Produce> {	//Custom compare object that implements the Comparator class on our Produce object for sorting produceClassification
	public int compare(Produce a, Produce b) {

		return (a.produceClassification().compareTo(b.produceClassification()));
	}

}