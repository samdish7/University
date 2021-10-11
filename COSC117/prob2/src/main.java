import java.util.*;
import java.io.*;
public class main
{
  public static void main(String args[]) throws FileNotFoundException
  {   
    ExtPerson[] list = new ExtPerson[500];
    
    File data = new File(System.getProperty("user.dir")+"/src/AddressDataExample.txt");//replace this with your file path
   
    Scanner console = new Scanner(data);
    
    int addCount = 0, i = 0;
    
    String fName;
    String lName;
    int month;
    int day;
    int year;
    String street;
    String city;
    String state;
    String zip;
    int count = 0;
    
    //need to call a constructor for each object in your ExtPerson array, this is why you were getting the error on case statement 7
    //will begin with a small number for demo purposes
    for(; i < 5; i++) {
    	list[i] = new ExtPerson();//default constructor
    }
    i = 0;
    while (console.hasNext())
    {
      String line = console.nextLine();
      
      count = count + 1;
      
     
      
      switch (count % 8) 
      {
        case 1:
          String[] splitName = line.split(" ");
          fName = splitName[0];
          lName = splitName[1];
          System.out.printf("%s %s\n", fName, lName);//test
          break;
        case 2:
          String[] splitDOB = line.split(" ");
          month = Integer.valueOf(splitDOB[0]);
          day = Integer.valueOf(splitDOB[1]);
          year = Integer.valueOf(splitDOB[2]);
          System.out.printf("%d %d %d\n", month, day, year);//
          break;
        case 3:
          street = line;      
          System.out.printf("%s\n",street);
          break;
        case 4:
          city = line;
          System.out.printf("%s\n",city);
          break;
        case 5:
          state = line;
          System.out.printf("%s\n",state);
          break;
        case 6:
          zip = line;
          System.out.printf("%s\n",zip);
          break;
        case 7:
          list[i].setPhoneNumber(line);
          break;         
        case 8:
          list[i].setPersonStatus(line);
          i++;
          break;         
      }
   
          
    System.out.println("1: To see if a person is in the address book");
    System.out.println("2: Print the information of a person");
    System.out.println("3: Print the names of person having birthday in a particular month");
    System.out.println("4: Print all person's info");
    System.out.println("9: Terminate the program"); 
    
    }

  }
}