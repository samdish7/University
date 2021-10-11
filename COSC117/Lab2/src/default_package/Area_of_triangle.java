package default_package;
import java.util.Scanner;
import java.lang.Math; // for square root

/*
 * Sam Disharoon
 * 1/31/21
 * 
 * Description
 * ============
 * 
 * This program asks the user to give 3 sides of
 * a triangle and display the area
 */

//main class where stuff happens
public class Area_of_triangle {

	//main function
	public static void main(String[] args) {
		//initialize variables
		int a, b, c;
		double area, p;
		
		//get user input
		Scanner scan = new Scanner(System.in);
		System.out.printf("Length of side A ~> ");
		a = scan.nextInt();
		System.out.printf("Length of side B ~> ");
		b = scan.nextInt();
		System.out.printf("Length of side C ~> ");
		c = scan.nextInt();
		
		p = (0.5) * (a + b + c); //get semi-perimeter
		
		area = Math.sqrt(p * ((p-a) * (p-b) * (p-c))); //perform Heron's formula
		
		System.out.printf("Area ~> %f", area);
		
		scan.close();//close buffer
	}

}
