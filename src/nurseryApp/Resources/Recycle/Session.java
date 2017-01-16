//package nurseryApp.Models;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//
//import java.sql.PreparedStatement;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by regga on 21/12/2016.
// */
//public class Session {
//
//
//   //private final SimpleIntegerProperty sessionId;
//    private Integer childID;
//    private String sessionFromDate;
//    private String sessionToDate;
//    private String sessionTimeRange;
//
//
//
//
//    public Session(int childID, String sessionFromDate, String sessionToDate, String sessionTimeRange) {
//        //this.sessionId = new SimpleIntegerProperty(sessionId);
//        this.childID = new Integer(childID);
//        this.sessionFromDate = new String(sessionFromDate);
//        this.sessionToDate = new String(sessionToDate);
//        this.sessionTimeRange = new String(sessionTimeRange);
//
//    }
//    public Session(){
//        this.childID = null;
//        this.sessionFromDate = null;
//        this.sessionToDate = null;
//        this.sessionTimeRange = null;
//    }
//
//    public Session(Children children, String queryDate ){
//        this.childID= children.getChild_Id();
//        this.sessionFromDate = queryDate;
//    }
//
//
//
//
//
////    public double getPrice(int age){
//////        Rates rates = new Rates(age,);
//////        double totalPrice=rates.getSessionRate(getSessionTimeRange())*daysCounter(getSessionFromDate(),getSessionToDate());
//////        return totalPrice;
////
////
////
////    }
//
//    public int daysCounter(String dateFrom,String dateTo) {
//        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//        int counterDays = 0;
//        //String dateFrom = "2016-11-09";
//        //String dateTo = "2016-11-10";
//        try {
//            Date dateA = newFormat.parse(dateFrom);
//            Date dateB = newFormat.parse(dateTo);
//            counterDays = (int) (dateB.getTime() - dateA.getTime());
//            System.out.println("Days: " + TimeUnit.DAYS.convert(counterDays, TimeUnit.MILLISECONDS));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return counterDays;
//    }
//
//    public void getSessionInfo(String sessionDate){
//
//    }
//
//
//}
