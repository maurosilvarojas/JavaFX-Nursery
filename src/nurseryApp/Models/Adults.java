package nurseryApp.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by regga on 13/01/2017.
 */
public  class Adults {

    protected   Integer parentID;
    protected  String name;
    protected  String surname;
    protected  String datOfBirth;
    protected  String email;
    protected  String mobile;
    protected  String address;
    String postcode;


    public Adults(){

    }

    public Adults(String name, String surname, String datOfBirth, String email, String mobile, String address, String postcode) {
        this.name = name;
        this.surname = surname;
        this.datOfBirth = datOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.postcode = postcode;
    }

    public Adults (Integer parentID){
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        this.parentID = parentID;
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull for Adult:Parent");
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
                    System.out.println("Wrong Child ID ");
                    return;
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Adults (String staff,Integer staffID){
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        this.parentID = parentID;
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull for Adult:Staff");
                Statement st = null;
                String query = "SELECT * FROM staffTable WHERE staffID="+"'"+staffID+"'"  ;
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
                    System.out.println("Wrong Child ID ");
                    return;
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getdatOfBirth() {
        return datOfBirth;
    }

    public void setdatOfBirth(String datOfBirth) {
        datOfBirth = datOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }







}

