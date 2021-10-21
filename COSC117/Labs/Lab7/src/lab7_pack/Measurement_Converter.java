package lab7_pack;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 3/9/21
 * 
 * Description
 * ================
 * 
 * This program simulates a measurement converter. 
 * It asks the user what units they have, the length they have, 
 * and what units they want their original length converted.
 * This program works by combining various methods such as
 * while loops, if/else statements, and newly learned switch statement.
 */

public class Measurement_Converter {

	public static void main(String[] args) {
		
		//initialize variables
		Scanner scan = new Scanner (System.in);
		double length = 0, convertedLength = 0, inchConverter = 0;
		int menu_input1 = 0, menu_input2 = 0;
		boolean x1 = false, x2 = false;//for while loop to keep the menu coming if invalid choice is pressed
		String unit1 = "", unit2 = "";//need to initialize, otherwise it will yell at you
		
		//display opening message
		System.out.println("Welcome to the measurement converter!\nChoose your current measurement unit\n");

		//begin 1st while loop
		while(!x1) {
			
			//output menu
			System.out.println("1: Inches\n2: Feet\n3: Yards\n4: Miles\n5: Meters\n6: Kilometers\n0: Quit");
		
			//create 1st switch statement using menu_input as the selector for user
			//this will get the units desired
			menu_input1 = scan.nextInt();
			switch(menu_input1) {
			case 1:
				unit1 = "Inches";
				x1 = true;
				break;//needed at the end of each switch statement
			case 2:
				unit1 = "Feet";
				x1 = true;
				break;
			case 3:
				unit1 = "Yards";
				x1 = true;
				break;
			case 4:
				unit1 = "Miles";
				x1 = true;
				break;
			case 5:
				unit1 = "Meters";
				x1 = true;
				break;
			case 6:
				unit1 = "Kilometers";
				x1 = true;
				break;
			case 0:
				System.out.println("Goodbye!");
				scan.close();//close buffer
				System.exit(0);//this exits program immediately 
				System.out.println("This will not execute, demonstration purposes");
				break;
			default:
				System.out.println("Invalid choice\nTry again");

			}//end of 1st switch statement
		}//end of 1st while loop
		
		//get length from user
		System.out.println("\nEnter length:");
		length = scan.nextDouble();
		
		//convert length into inches using if/else statements
		if(unit1.equals("Inches")) {
			inchConverter = length;
		} else if(unit1.equals("Feet")) {
			inchConverter = length * 12;
		} else if(unit1.equals("Yards")) {
			inchConverter = length * 36;
		} else if(unit1.equals("Miles")) {
			inchConverter = length * 63360;
		} else if(unit1.equals("Meters")) {
			inchConverter = length * 39.3701;
		} else {
			inchConverter = length * 39370.1;
		}
				
		//print out second menu to user
		System.out.printf("\nChoose what unit you will convert %.8f %s into:\n", length, unit1);
		
		//begin 2nd while loop
		while(!x2) {
			//output menu again
			System.out.println("1: Inches\n2: Feet\n3: Yards\n4: Miles\n5: Meters\n6: Kilometers\n0: Quit");
			
			//get desired conversion unit
			menu_input2 = scan.nextInt();
			
			//if units are the same, it will display the length given
			if(menu_input1 == menu_input2) {
				unit2 = unit1;
				System.out.printf("%s & %s are the same.\n", unit1, unit2);
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, length, unit2);
				x2 = true;
			}
			
			//being 2nd switch which allows user to pick the conversion units,
			//does the conversions, and displays to the user
			switch(menu_input2) {
			case 1:
				unit2 = "Inches";
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, inchConverter, unit2);
				x2 = true;
				break;//needed at the end of each switch statement
			case 2:
				unit2 = "Feet";
				convertedLength = inchConverter / 12;
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, convertedLength, unit2);
				x2 = true;
				break;
			case 3:
				unit2 = "Yards";
				convertedLength = inchConverter / 36;
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, convertedLength, unit2);
				x2 = true;
				break;
			case 4:
				unit2 = "Miles";
				convertedLength = inchConverter / 63360;
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, convertedLength, unit2);
				x2 = true;
				break;
			case 5:
				unit2 = "Meters";
				convertedLength = inchConverter / 39.3701;
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, convertedLength, unit2);
				x2 = true;
				break;
			case 6:
				unit2 = "Kilometers";
				convertedLength = inchConverter / 39370.1;
				System.out.printf("%.8f %s = %.8f %s\n", length, unit1, convertedLength, unit2);
				x2 = true;
				break;
			default:
				System.out.println("Invalid choice\nTry again");
			}//end of 2nd switch statement
		}//end of 2nd while loop

		System.out.println("Goodbye!");
		
		scan.close();//close buffer
	}
		

}
