package lab11_pack;
/*
 * Addition Question class declaration & implementation
 * 
 * Description
 * =================
 * 
 * This file holds the outline for the AdditionQuestion class.
 * This includes the default ctor, two private attributes and 2 subroutines
 * all of which are explained below
 * 
 * NOTE:
 * ctor = short-hand for constructor
 */

public class AdditionQuestion {
	private int a, b;
	
	//default ctor
	public AdditionQuestion(){
		a = (int)(Math.random()*50)+1;
		b = (int)(Math.random()*50)+1;
		
	}
	//question function implementation
	public void question(){
		System.out.printf("What is %d + %d?\n", a, b);//display the question
	}
	//check function implementation
	public int check() {
		return (a+b);//return the sum of a & b
	}
}
