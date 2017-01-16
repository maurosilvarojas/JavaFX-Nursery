package nurseryApp.Controller;


import nurseryApp.Models.DBConnection;
import nurseryApp.Models.PrintToFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 * Created by regga on 25/12/2016.
 */
public class AccessLog {
    Connection connection=null;
    Statement statement = null;
    String empty = "";
    String todaysDay = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(Calendar.getInstance().getTime());

    /**
     * The method is intended for filling the database single
     * access of the user
     * @param username
     * @param employ_ID
     */
    public void fillAccess (String username,String employ_ID){
        try{
            connection = DBConnection.getConnection();
            if (connection != null){
                System.out.println("Connection succesfull");
                PreparedStatement ps ;
                ps = connection.prepareStatement("INSERT INTO sessionLog (employ_ID,username,todaysDate )" +
                        "VALUES (?,?,?)   ");
                ps.setString(1, employ_ID);
                ps.setString(2, username);
                ps.setString(3, todaysDay.toString());
                ps.executeUpdate();
                ps.close();
                connection.close();
                System.out.println("Query executed, connection terminated ");
                String message = "New login for user :"+employ_ID + " ,"  +" Name:" +username+", on :" + todaysDay.toString();
                //String location="C:\\Users\\regga\\IdeaProjects\\NurseryApp\\src\\nurseryApp\\Resources\\logs\\Stafflog.txt";
                String location="Stafflog.txt";
                PrintToFile p2f=new PrintToFile(message,location,"11 jan");
                p2f.print();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}



