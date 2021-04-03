package bean;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class PassHash implements java.io.Serializable {

    //Driver and connection string with absolute path to db
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private final String connectionString = "jdbc:ucanaccess://C:\\Users\\seanl\\Documents\\NetBeansProjects\\GCU_1\\data\\GCU_SkillsDB.accdb";
    private String generatedPassword = null;

    //Returns hashed password encrypted by BCrypt
    public String hashPassword(String passwordToHash) {
        generatedPassword = BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));
        return generatedPassword;
    }
    
    //Compares equality between plaintext password input by user and hashed password in database
    public boolean checkPassword(String email, String password, String table) throws SQLException {
        String dataPass = "";
        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(connectionString)) {
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT * FROM "+ table +" WHERE EmailAddress=\"" + email + "\"");
                while (rs.next()) {
                    dataPass = rs.getString("Password");
                }
            }
            return BCrypt.checkpw(password, dataPass);
            
            //Return as invalid if exception occurs
        } catch (ClassNotFoundException | StringIndexOutOfBoundsException e ) {
            return false;
        }
    }
}
