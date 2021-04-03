package bean;

import java.util.Date;

/**
 * @author jackb
 */
public class Admin extends User {

    private int adminId;
    private boolean superUser;

    //Zero-Parameter Constructor
    public Admin()
    {

        super();
        superUser = true;

    }

    //Overloaded Constructor
    public Admin(String emailIn, String passwordIn, String firstNameIn, String lastNameIn, Date dobIn, int adminIdIn, boolean superUserIn)
    {

        super(emailIn, passwordIn, firstNameIn, lastNameIn, dobIn);
        adminId = adminIdIn;
        superUser = superUserIn;

    }

    //Getters
    public boolean isSuperUser() {return superUser;}

    public int getAdminId() {return adminId;}

    //Setter
    public void setSuperUser(boolean superUserIn) {superUser = superUserIn;}

}
