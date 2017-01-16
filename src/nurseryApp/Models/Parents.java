package nurseryApp.Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by regga on 12/12/2016.
 */
public class Parents extends Adults{
    Charges charges;

    public Parents(String name, String surname, String datOfBirth, String email, String mobile, String address, String postcode) {
        super(name, surname, datOfBirth, email, mobile, address, postcode);
    }

    public Parents(){
        super();
    }

    public  Parents(int parentID){

        Connection connection=null;
        Statement statement = null;
        this.parentID = parentID;
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull");
                Statement st = null;
                String query = "SELECT * FROM parentsTable WHERE parentID="+"'"+parentID+"'"  ;
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                if (resultSet.next()) {
                    this.name=resultSet.getString("name");
                    this.surname=resultSet.getString("surname");
                    this.datOfBirth=resultSet.getString("DoB");
                    this.email=resultSet.getString("email");
                    this.mobile=resultSet.getString("mobile");
                    this.address=resultSet.getString("address");
                    this.postcode=resultSet.getString("postcode");

                }else{
                    System.out.println("Wrong Parent ID ");
                    return;
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Adults> createAdultsList() {
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        ObservableList<Adults> adults = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                Statement ps;
                ps = connection.createStatement();
                rs = ps.executeQuery("SELECT * FROM parentsTable");
                while (rs.next()) {
                    adults.add(new Adults(
                            rs.getInt("parentID")

                    ));
                    System.out.println("Parent Printing List:"+ rs.getString("name"));

                }
                connection.close();
                System.out.println("Query Create Adult list executed, connection terminated ");
                return adults;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adults;
    }



    public Parents searchParentInfo(String parentSurname, String parentDoB) throws SQLException {
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
              try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Connection succesfull");
                Statement st = null;
                String query = "SELECT * FROM parentsTable WHERE surname="+"'"+parentSurname+"'" +"AND DoB ="+"'"+parentDoB+"'" ;
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                if (resultSet.next()) {
                    Parents parents = new Parents(resultSet.getInt("parentID"));
                    return parents;
                }else{
                    System.out.println("Parent Not Found");
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public int findOwnChildren(){
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Connection succesfull");
                Statement st = null;
                String query = "SELECT * FROM childrenTable WHERE parentID="+"'"+parentID+"'"  ;
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                if (resultSet.next()) {
                    Children children = new Children(resultSet.getInt("child_Id"));
                    return children.getChild_Id();
                }else{
                    System.out.println("Parent Not Found");
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }



}
