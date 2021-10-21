package lab6_pack;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 3/1/21
 * 
 * Description
 * =============
 * 
 * This program simulates keeping track of salsa sales!
 * Using 3 types of arrays, you keep track of the total sales,
 * the name, and the price of each type of salsa.
 * It will display to the user how much they owe, the tax, and the net profit 
 * for the month
 */

public class Salsa {

	public static void main(String[] args) {
		//initialize variables
		int arrSize = 5, i = 0;
		double grossSales = 0, taxConst = 0.06, taxOwed = 0, netGain = 0, x;
		Scanner scan = new Scanner(System.in);
		
		//create arrays literals 
		double price[] = {2.25, 3.00, 3.50, 4.00, 4.50};//price array literal
		String name[] = {"Mild", "Medium", "Sweet", "Hot", "Zesty"};//name array literal

		
		//allocate array memory for user
		int numSold[];
		numSold = new int[arrSize];
		
		System.out.println("Salsa Profit Chart!\n");
		
		//user input for loop
		for(; i < arrSize; i++) {
			System.out.printf("Enter the number of %s salsas sold ($%.2f):\n", name[i], price[i]);
			numSold[i] = scan.nextInt();
		}
		
		/* PSA
		 * 
		 * There are a lot of formatting symbols in here, I will list them here and what they do.
		 * Remember, print will display EXACTLY what you have in between the "",
		 * spaces are included, so it is normal for you to have to run your
		 * program a lot to get the spacing right, this is normal.
		 * 
		 * \n adds a new line
		 * \t adds a tab, like pressing the tab button on your keyboard
		 * %-5s formats the variable (a string in this case) to be left justified by 5 spaces
		 * %5s formats the variable (a string in this case) to be right justified by 5 spaces
		 * 
		 * Note:
		 * It may look a little funky when you input larger numbers of salsa sales,
		 * play around with the formats to figure out what looks best!
		 */
		
		//display for loop
		for(i = 0; i < arrSize; i++) {
			x = numSold[i] * price[i];
			System.out.format("%s ~> %-3s\t %d x %-5.2f = %-4.2f\n", name[i], "" , numSold[i], price[i], x);
			grossSales += x;//calculate gross sales here instead of its own loop to save space/time
		}
		
		//calculations
		taxOwed = grossSales * taxConst;
		netGain = grossSales - taxOwed;
		
		//display to user
		System.out.printf("----------------------------------\nGross ~> \t\t    %.2f\nTax ~> \t\t\t   (%.2f)\n", grossSales, taxOwed);
		System.out.printf("----------------------------------\nNet Profit: ~> \t\t    %.2f\n", netGain);
		System.out.printf("You made $%.2f this month!\n", netGain);
		
		//free memory allocated
		scan.close();
		System.gc();
		
	}

}
