import java.io.*;

public class AddressBook
{
  private ExtPerson[] list;//array of objects
  
  
  

  private int length;// the index of last valid object in the array "list"

  public AddressBook()
  {
    //initialize the array of objects(null for each element), initialize length to 0
   
    for (int i = 0; i < 500; i++)
    {
      list[i] = null;
    }
    length = 0;
  }

  public void print()
  {
  //call printInfo() method to print all the information in the array of objects.
  //for-loop required(0-length)
  }

  public void printNameInTheMonth(int month)
  {
  //leverage isMonth() method to judge the month
  //if month matched, print the name of ExtPerson object
  //for-loop required(0-length)
  }

  public void printInfoOf(String lName)
  {
  //leverage search() method to locate the index of a ExtPerson object according to last name
  //if i!=-1 then call the list.printInfo() method
  //else print person not in address book.
  }


  public void insertAt(ExtPerson  eP, int i)
  {
  //insert the ExtPerson object to a specific index i in array "list"
        //intialize the content in list[i] to null
  //if array "list" size "length" equals i, then increase length by 1
 

  }

 /* public int search(String lName)
  {
  //tells whether a person's is in the AddressBook by lastname
  //intialize a boolean variable found to false.
  //for-loop required to go through the array "list" (0-length)
  //leverage isLastName method to test wether the lastname match one in the addressBook
  //if exsit, return the object's index. (-1 for not found)
  }*/
}