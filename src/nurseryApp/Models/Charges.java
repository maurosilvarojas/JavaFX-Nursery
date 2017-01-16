package nurseryApp.Models;
import nurseryApp.Models.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * Created by regga on 12/01/2017.
 */
public class Charges {
    //int id;
    int childID;
    String generatedDate;
    double price;
    String description;




//    public Charges(int childID, int month ) {
//        this.childID = childID;
//        this.month = month;
//    }

    public Charges(int childID, String generatedDate, double price, String description) {
        this.childID = childID;
        this.generatedDate = generatedDate;
        this.price = price;
        this.description = description;
    }
     public Charges(){

     }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getChildID() {
        return childID;
    }

    public void setChildID(int childID) {
        this.childID = childID;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addCharges(int childID, String description, float price,String todaysDay){
        todaysDay = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            Connection connection=null;
            Statement statement = null;
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Connection succesfull");
                PreparedStatement ps;
                ps = connection.prepareStatement("INSERT INTO sessionTable (childID,description,price,todaysDay" +
                        "VALUES (?,?,?,?)   ");
                ps.setInt(1, childID);
                ps.setString(2, description);
                ps.setFloat(3, price);
                ps.setString(4, todaysDay);

                ps.executeUpdate();
                ps.close();
                connection.close();
                System.out.println("Query Add chrages executed, connection terminated ");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //        String todaysDay = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(Calendar.getInstance().getTime());
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        Date date = format.parse("2016-11-09");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int month = cal.get(Calendar.MONTH);


    public ObservableList<Charges> chargesList (int searchID){

        Connection connection=null;
        Statement statement = null;
        ObservableList<Charges> charges = FXCollections.observableArrayList();

        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull");
                Statement st = null;
                String query = "SELECT * FROM sessionTable WHERE child_Id="+"'"+searchID+"'"  ;
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                while (resultSet.next()) {
                   charges.add(new Charges(
                            resultSet.getInt("child_id"),
                            resultSet.getString("sessionFromDate"),
                            resultSet.getDouble("price"),
                            resultSet.getString("description")
                    ));
                }
                connection.close();
                System.out.println("Query Create Charges executed, connection terminated ");
                return charges;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charges;
    }

    public Double getFinalBalance(int childID){
        ObservableList<Charges> newCharges = FXCollections.observableArrayList();
        final double[] balance = {0};
        Charges charges = new Charges();
        newCharges=charges.chargesList(childID);
        newCharges.forEach((price)->{      // lampda expression to generate a sum of all prices found
            balance[0] = price.getPrice()+ balance[0];
            System.out.println(balance[0]);
        });
        Double finalBalance=balance[0];
        return  finalBalance;
    }

//
//    public int daysCounter(String dateFrom,String dateTo){
//        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//        int counterDays=0;
//        //String dateFrom = "2016-11-09";
//        //String dateTo = "2016-11-10";
//        try {
//            Date dateA = newFormat.parse(dateFrom);
//            Date dateB = newFormat.parse(dateTo);
//            counterDays = (int) (dateB.getTime() - dateA.getTime());
//            System.out.println ("Days: " + TimeUnit.DAYS.convert(counterDays, TimeUnit.MILLISECONDS));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return counterDays;
//    }
}
