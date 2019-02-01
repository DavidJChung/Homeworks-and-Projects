package grocery;

import java.io.*;
import java.util.Scanner;
import grocery.Shopping_list;
/**
 * 
 * @author 
 * <h1>Invoice</h1>
 * <p> This class contains the main method by which the invoice is created into an output.txt file.<p>
 *
 */
public class Invoice {
	public static void main(String[] args) {
		Shopping_list sL = new Shopping_list();					//creates instance of shopping list
		double total = 0;

		try {
			sL.makeShoppingList();
			// System.out.println(sL.l); // Used for debugging
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("output.txt"));
			PrintWriter pWriter = new PrintWriter(bWriter);

			pWriter.printf("|%-13s| %-6s | %-6s | %-8s | %-6s | %-8s | %n", "[Item]", "[Item Classification]",
					"[Price Type]", "[Price]", "[Qty]", "[Ext. Price]");											// Header for output file

			for (Produce p : sL.l) {
				pWriter.printf("|%-12s | %-21s | %-12s | %-8.2f | %-6.0f | %-12.2f | %n", p.produceType(),
						p.produceClassification(), p.priceType(), p.price(), p.getQty(), p.price() * p.getQty());
				total += p.price() * p.getQty();																	// Prints the line for each item in the shopping list along with pricing

			}
			bWriter.write(
					"-----------------------------------------------------------------------------------------\n");
			bWriter.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  Total: " + total + " \n");							// Prints total
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Your invoice was created in the output.txt file");
	}
}
