package nurseryApp.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by regga on 15/01/2017.
 */
public class WaitingParents extends Parents{
    String numOfChildren;
    String childrenAge;
    ObservableList<WaitingParents> waitingParent = FXCollections.observableArrayList();


    public WaitingParents(String name, String surname,String mobile, String email,String numOfChildren, String childrenAge) {
        this.name=name;
        this.surname=surname;
        this.mobile=mobile;
        this.email=email;
        this.numOfChildren= numOfChildren;
        this.childrenAge=childrenAge;
    }

    public WaitingParents(){

    }

    public String getNumberOfChildren() {
        return numOfChildren;
    }

    public void setNumberOfChildren(String numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public String getChildrenAge() {
        return childrenAge;
    }

    public void setChildrenAge(String childrenAge) {
        this.childrenAge = childrenAge;
    }

    public ObservableList<WaitingParents> createWaitingList() {
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        ObservableList<Adults> adults = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                Statement ps;
                ps = connection.createStatement();
                rs = ps.executeQuery("SELECT * FROM waitingList");
                while (rs.next()) {
                    waitingParent.add(new WaitingParents(
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("mobile"),
                            rs.getString("email"),
                            rs.getString("numOfChildren"),
                            rs.getString("childrenAge")

                    ));
                }
                connection.close();
                System.out.println("Query Create waiting list executed, connection terminated ");
                return waitingParent;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waitingParent;
    }
}



