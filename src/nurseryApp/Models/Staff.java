package nurseryApp.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by regga on 21/12/2016.
 */
public class Staff extends Adults {


    private String username;
    private String password;



    private String abailability;


    public Staff(String name, String surname, String datOfBirth, String email, String mobile, String address, String postcode) {
        super(name, surname, datOfBirth, email, mobile, address, postcode);
    }

    public Staff(){
        super();
    }

    public String getAbailability() {
        return abailability;
    }

    public void setAbailability(String abailability) {
        this.abailability = abailability;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObservableList<Adults> createStaffList() {
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        ObservableList<Adults> adults = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                Statement ps;
                ps = connection.createStatement();
                rs = ps.executeQuery("SELECT * FROM staffTable");
                while (rs.next()) {
                    adults.add(new Adults("",
                            rs.getInt("staffID")
                    ));
                    System.out.println("Staff Printing List:"+ rs.getString("name"));
                }
                connection.close();
                System.out.println("Query Create staff list executed, connection terminated ");
                return adults;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adults;
    }
}
