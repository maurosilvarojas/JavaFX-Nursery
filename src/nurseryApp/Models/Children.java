package nurseryApp.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by regga on 12/01/2017.
 */
public class Children  {
    Connection connection=null;
    Statement statement = null;
    String userDB=null;
    //Session session;



    private  int child_Id;
    private  String name;
    private  String surname;
    private  String dateOfBirth;
    private  String allergy;
    private  int parentID;
    private  String category;

    public Children(){

    }

    public Children(int child_Id) {
        this.child_Id = child_Id;
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull");
                Statement st = null;
                String query = "SELECT * FROM childrenTable WHERE child_Id="+"'"+child_Id+"'"  ;
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                if (resultSet.next()) {
                    this.name=resultSet.getString("name");
                    this.surname=resultSet.getString("surname");
                    this.dateOfBirth=resultSet.getString("DoB");
                    this.allergy=resultSet.getString("allergy");
                    this.parentID=resultSet.getInt("parentID");
                    this.category=resultSet.getString("category");

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

    public int getChild_Id() {
        return child_Id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAllergy() {
        return allergy;
    }

    public int getParentID() {
        return parentID;
    }

    public String getCategory() {
        return category;
    }

    public int getChildAge(){
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        int counterDays = 0;
        String todaysDay = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(Calendar.getInstance().getTime());
        int counterYears = Period.between(LocalDate.now(), LocalDate.parse(getDateOfBirth())).getYears();
        counterYears = Math.abs(counterYears);
        System.out.println("Child Age: " + counterYears);
        return counterYears;
    }

    public ObservableList<Children> createChildrenList() {
        Connection connection=null;
        Statement statement = null;
        ResultSet rs = null;
        ObservableList<Children> children = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                Statement ps;
                ps = connection.createStatement();
                rs = ps.executeQuery("SELECT * FROM childrenTable");
                while (rs.next()) {
                    children.add(new Children(
                            rs.getInt("child_Id")
                    ));
                    System.out.println("Creating List");
                }
                connection.close();
                System.out.println("Query Create Children executed, connection terminated ");
                return children;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return children;
    }


}


