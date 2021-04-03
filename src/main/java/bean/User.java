package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jackb
 */
public class User {
    
    //Private Variables
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dob;
    private String address;
    private String dobString;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //Zero-Parameter Constructor
    public User()
    {

        email = "";
        password = "";
        firstName = "";
        lastName = "";
        dob = new Date();
        address="";
        

    }

    //Overloaded Constructor
    public User(String emailIn, String firstNameIn, String lastNameIn, Date dobIn)
    {

        email = emailIn;
        password = "";
        firstName = firstNameIn;
        lastName = lastNameIn;
        dob = dobIn;
        dobString = sdf.format(dobIn);

    }

    //Overloaded Constructor
    public User(String emailIn, String passwordIn, String firstNameIn, String lastNameIn, Date dobIn)
    {

        email = emailIn;
        password = passwordIn;
        firstName = firstNameIn;
        lastName = lastNameIn;
        dob = dobIn;
        dobString = sdf.format(dobIn);

    }

    //Getters
    public String getEmail() {return email;}
    
    public String getPassword() {return password;}
    
    public String getFirstName() {return firstName;}
    
    public String getLastName() {return lastName;}

    public Date getDob() {return dob;}
    
    public String getAddress() { return address;}
    
    public String getDobString() { return dobString; }

    //Setters
    public void setEmail(String emailIn) {email = emailIn;}
    
    public void setPassword(String passwordIn) {password = passwordIn;}
    
    public void setFirstName(String firstNameIn) {firstName = firstNameIn;}
    
    public void setLastName(String lastNameIn) {lastName = lastNameIn;}

    public void setDob(Date dobIn) {dob=dobIn;}
    
    public void setAddress(String addressIn){ address=addressIn;}
}