package nurseryApp.Models;

import nurseryApp.Controller.NurseryNotifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by regga on 12/01/2017.
 */
public class NurserySession {


    private Integer childID;
    private String sessionFromDate;
    private String sessionToDate;
    private String sessionTimeRange;
    private double sessionPrice;


    public NurserySession(Integer childID, String sessionFromDate, String sessionToDate, String sessionTimeRange) {
        this.childID = childID;
        this.sessionFromDate = sessionFromDate;
        this.sessionToDate = sessionToDate;
        this.sessionTimeRange = sessionTimeRange;
        this.sessionPrice = setSessionPrice();

    }
    public Integer getChildID() {
        return childID;
    }

    public void setChildID(Integer childID) {
        this.childID = childID;
    }

    public String getSessionFromDate() {
        return sessionFromDate;
    }

    public void setSessionFromDate(String sessionFromDate) {
        this.sessionFromDate = sessionFromDate;
    }

    public String getSessionToDate() {
        return sessionToDate;
    }

    public void setSessionToDate(String sessionToDate) {
        this.sessionToDate = sessionToDate;
    }

    public String getSessionTimeRange() {
        return sessionTimeRange;
    }

    public void setSessionTimeRange(String sessionTimeRange) {
        this.sessionTimeRange = sessionTimeRange;
    }
    //System.out.println();
    public double setSessionPrice(){
        Children newChild = new Children(childID);
        int age =  newChild.getChildAge();
        System.out.println("Child age: "+ age);
        Rates newRate = new Rates(age,getSessionTimeRange());
        System.out.println("Session rate is : " + newRate.getSessionRate());
        System.out.println("Session dates: " + daysCounter(getSessionFromDate(),getSessionToDate()));
        double totalPrice=(newRate.getSessionRate())*daysCounter(getSessionFromDate(),getSessionToDate());
        System.out.println("Total price: " + totalPrice);
        sessionPrice=totalPrice;
        return sessionPrice;
    }

    public double getSessionPrice() {
        return sessionPrice;
    }


    public int daysCounter(String dateFrom,String dateTo) {
        long differenceDays=0;
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        int counterDays = 0;
        try {
            Date dateA = newFormat.parse(dateFrom);
            Date dateB = newFormat.parse(dateTo);
            counterDays = (int) (dateB.getTime() - dateA.getTime());
            System.out.println("Days: " + TimeUnit.DAYS.convert(counterDays, TimeUnit.MILLISECONDS));
            differenceDays = TimeUnit.DAYS.convert(counterDays, TimeUnit.MILLISECONDS)+1;
            return (int) differenceDays;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) differenceDays;
    }

    public  void bookSession(){
        System.out.println("Starting booking procedure for "+ getChildID());
        Connection connection=null;
        Statement statement = null;
        try{
            connection = DBConnection.getConnection();
            if (connection != null){
                System.out.println("Conection succesfull");
                PreparedStatement ps ;

                ps = connection.prepareStatement("INSERT INTO sessionTable (sessionFromDate,sessionToDate,child_id,sessionTimeRange,price)" +
                        "VALUES (?,?,?,?,?)   ");
                ps.setString(1,getSessionFromDate());
                ps.setString(2, getSessionToDate());
                Children newChild=new Children(childID);
                ps.setInt(3, newChild.getChild_Id());
                ps.setString(4, getSessionTimeRange());
                ps.setDouble(5,getSessionPrice());
                ps.executeUpdate();
                ps.close();
                connection.close();
                System.out.println("Query executed, connection terminated , session addded ");
                NurseryNotifications notifyer = new NurseryNotifications();
                notifyer.showNotification("Successfully Booked");
                //Stage stage =(Stage) addUser.getScene().getWindow();
                //stage.close();

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}


