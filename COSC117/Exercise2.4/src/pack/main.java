package pack;
import java.util.Scanner;

/*
 * Driver program for exercise 2.4
 * NOTE
 * ctor = constructor
 */
public class main {

	public static void main(String[] args) {
		//created person objects for testing
		Person p1, p2, p3;
		Scanner scan = new Scanner(System.in);
		String str;
		boolean e;
		//initialize person objects with given names
		p1 = new Person("Bobby", "Michael", "Jones");//main person ctor
		p2 = new Person();//default person ctor
		
		/*
		 * TESTING AREA
		 */
		
		//demonstrate makeCopy/isEqual function
		System.out.println("Demonstrate makeCopy & isEqual\np2 is a copy of p1");
		p2.makeCopy(p1);
		
		//display both to see results
		System.out.printf("p1 ~> %s %s %s\n", p1.getFirstName(), p1.getMiddleName(), p1.getLastName());
		System.out.printf("p2 ~> %s %s %s\n", p2.getFirstName(), p2.getMiddleName(), p2.getLastName());
		
		//since they are the same, this will return true
		e = p1.isEqual(p2);
		System.out.printf("Are p1 and p2 equal? %b\n", e);
		
		//changing p2's firstName for more testing purposes
		System.out.println("\n\nChanging p2's first name and then comparing");
		p2.setFirstName("Josh");
		System.out.printf("p2 ~> %s %s %s\n", p2.getFirstName(), p2.getMiddleName(), p2.getLastName());
		
		//since they are not the same, this will return false
		e = p1.isEqual(p2);
		System.out.printf("Are p1 and p2 equal now? %b\n", e);
		
		
		//demonstrate getCopy function
		System.out.println("\n\nNow to demonstrate getCopy, p3 is a new person and will be a copy of p1");
		p3 = p1.getCopy();
		System.out.printf("p3 ~> %s %s %s\n", p3.getFirstName(), p3.getMiddleName(), p3.getLastName());
		
		//demonstrate the isFirst, isMid, isLast functions with p1, 
		//will display either true or false 3 times
		System.out.println("\n\nNow to test the isFirst, isMid, and isLast functions with p1\nEnter a name");
		str = scan.nextLine();
		e = p1.isFirst(str);
		System.out.printf("First name? %b\n", e);
		e = p1.isMid(str);
		System.out.printf("Middle name? %b\n", e);
		e = p1.isLast(str);
		System.out.printf("Last name? %b\n", e);
		
		//free memory
		System.gc();
	}

}
