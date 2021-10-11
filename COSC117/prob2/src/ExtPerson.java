public class ExtPerson extends Person
{
  private Address addr;
  private Date dob;
  private String phoneNumber;
  private String personStatus;
  
  public ExtPerson()
  {
    super();
    addr = new Address("","","","");//need to create an Address object
    dob = new Date(0,0,0);//need to create a Date object
    phoneNumber = "";
    personStatus = "";
  }

  public ExtPerson(String fName, String lName, int month, int day, int year,
       String street, String c, String s, String z,
       String phone, String pStatus)
  {
    super.setName(fName, lName);
    addr = new Address(street, c, s, z);
    dob = new Date(month, day, year);
    phoneNumber = phone;
    personStatus = pStatus;
  } 
  
  public void setPhoneNumber(String num)
  {
    phoneNumber = num;
  }
    
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
    
  public void printNum()
  {
    System.out.println(phoneNumber);
  }
    
  public void setPersonStatus(String status)
  {
    personStatus = status;
  }
    
  public String getPersonStatus()
  {
    return personStatus;
  }
    
  public void printStatus()
  {
    System.out.println(personStatus);
  }
   
  


  
  
  
}