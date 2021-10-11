package pack;

public class Person {

	//given private strings
	private String firstName;
	private String lastName;
	
	//added private string
	private String middleName;
	
	//default ctor
	public Person() {
		firstName = "";
		lastName = "";
		//added 
		middleName = "";
	}
	/*
	 * main CTOR
	 * I updated this to set the middle name as well
	 */
	public Person(String fName, String mName, String lName) {
		setName(fName, lName);
		setMiddleName(mName);
	}
	
	/*
	 * Functions given by instructions
	 */
	
	//returns full name
	public String toString(){
		return (firstName + " " + lastName);
	}

	//sets full name
	public void setName(String f, String l) {
		firstName = f;
		lastName = l;
	}
	
	//gets firstName
	public String getFirstName() {
		return firstName;
	}
	
	//gets lastName
	public String getLastName() {
		return lastName;
	}
	
	/*
	 * Theses are the functions requested by the exercise, I will comment as best as I can
	 */
	
	//gets middleName
	public String getMiddleName() {
		return middleName;
	}
	
	//sets middleName only
	public void setMiddleName(String m) {
		middleName = m;
	}
	
	//sets firstName only
	public void setFirstName(String f) {
		firstName = f;
	}
	
	//sets lastName only
	public void setLastName(String l) {
		lastName = l;
	}
	
	//compares given firstName with existing firstName, then passes back true or false
	public boolean isFirst(String f) {
		if(this.firstName.equals(f)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//compares given middleName with existing middleName, then passes back true or false
		public boolean isMid(String m) {
			if(this.middleName.equals(m)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		//compares given lastName with existing lastName, then passes back true or false
		public boolean isLast(String l) {
			if(this.lastName.equals(l)) {
				return true;
			}
			else {
				return false;
			}
		}
	
		//compares two Person objects and returns true if equal, false otherwise
		public boolean isEqual(Person p2) {
			if(!p2.firstName.equals(this.firstName)) {
				return false;
			} else if(!p2.middleName.equals(this.middleName)) {
				return false;
			} else if(!p2.lastName.equals(this.lastName)) {
				return false;
			} else {
				return true;
			}
		}
		
		//copies components of one person object into another one
		public void makeCopy(Person p1) {
			firstName = p1.firstName;
			middleName = p1.middleName;
			lastName = p1.lastName;
		}
		
		//copies and returns an entire person object
		public Person getCopy() {
			Person copy = new Person();
			copy.setFirstName(firstName);
			copy.setMiddleName(middleName);
			copy.setLastName(lastName);
			
			return copy;
		}
	
	
	

}
