package lab_10_pack;

/*
 * Sam Disharoon
 * 4/13/21
 * 
 * Description
 * ===============
 * 
 * This file holds all information for the Inventory class used by the MainClass.java program.
 * 
 * The variables and functions that make up the class are all defined and implemented here, 
 * although they are not used in this file, you can import this file into other programs if you want
 * and have them interact and manipulate it as you please
 * 
 * NOTE: 
 * ctor = short-hand for  constructor
 */

public class Inventory {

	//intialize private variables
	private String name;
	private int stock;
	private double price;
	
	//default ctor, set all variables to something for good programming practice
	public Inventory() {
		name = "";
		stock = 0;
		price = 0.0;
	}
	
	/*
	 * GETTERS & SETTERS
	 * 
	 * Because private variables cannot be accessed outside of the class,
	 * we must use functions to interact with them.
	 * 
	 * These are typically called getters and setters.
	 * 
	 * set functions set the variable to the variable passed in
	 * get functions get the variable from the class
	 */
	
	//set name
	public void setName(String n) {
		name = n;
	}
	//get name
	public String getName() {
		return name;
	}
	//set stock
	public void setStock(int s) {
		stock = s;
	}
	//get stock
	public int getStock() {
		return stock;
	}
	//set price
	public void setPrice(double p) {
		price = p;
	}
	//get price
	public double getPrice() {
		return price;
	}
}
