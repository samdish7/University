package lab_10_pack;//need this to get the information from Inventory!
import java.util.Scanner;//user input

/*
 * Sam Disharoon
 * 4/9/21
 * 
 * Description
 * ==============
 * 
 * This program introduces Classes and how to work with them.
 * 
 * This is the "driver" program, which menas it does most of the work
 * 
 * This will interact with the Inventory class and allow the user to create
 * their own Inventory. 
 * 
 * This to demonstrate further class manipulation,
 * this program also includes a showInventory function
 * which will just display the Inventory given by the user
 */

public class MainClass {
	
	//showInventory function implementation
	public static void showInventory(Inventory[] inv, int size){
		int i = 0;//local i, not the same as the i in main!
		
		System.out.println("<><><><><><><><><><><><><><><><>");//display purposes

		//print out Inventory using get functions
		for(; i < size; i++) {
			System.out.printf("Inventory Item %d:\nName ~> %s\nStock ~> %d\nPrice ~> %5.2f\n", i+1 ,inv[i].getName(), inv[i].getStock(), inv[i].getPrice());
			System.out.println("<><><><><><><><><><><><><><><><>");
		}
	}

	public static void main(String[] args) {
		
		//initialize variables
		Scanner scan = new Scanner(System.in);
		int stockIn, i;
		String nameIn;
		double priceIn;
		
		//initialize Inventory array
		Inventory[] inv = new Inventory[5];//this is from the Inventory class, can be declared like all other variables!

		System.out.println("Welcome to the Inventory creater!\nYou will enter 5 different items in your inventory and it will be displayed back to you");
		
		//allow user to create their Inventory
		for(i = 0; i < 5; i++) {
			
			//create each object in the array using default ctor
			inv[i] = new Inventory();
			
			//ask and set name
			System.out.printf("What is the name of Inventory item %d?\n",i+1);
			nameIn = scan.next();
			inv[i].setName(nameIn);
			
			//ask and set stock
			System.out.printf("What is the stock amount of Inventory item %d?\n",i+1);
			stockIn = scan.nextInt();
			inv[i].setStock(stockIn);
			
			//ask and set price
			System.out.printf("What is the price of Inventory item %d?\n",i+1);
			priceIn = scan.nextDouble();
			inv[i].setPrice(priceIn);
		}
		
		//call showInventory
		showInventory(inv, 5);
		
		System.out.println("Goodbye!");
		
		//get rid of memory leaks!
		System.gc();
		scan.close();

	}

}
