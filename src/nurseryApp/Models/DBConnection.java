package nurseryApp.Models;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by regga on 12/12/2016.
 */

public class DBConnection {
    public static  final String JDBC_driver = "com.mysql.jdbc.Driver";
    public static  final String JDBC_url="jdbc:mysql://194.81.104.**:3306/db******" ;
   // public static  final String JDBC_dbName=******
    public static  final String JDBC_user = "s******";
    public static  final String JDBC_password = "******";


    public static Connection getConnection (){
        Connection connection = null;
        try{
            Class.forName(JDBC_driver);
            connection = DriverManager.getConnection(JDBC_url ,JDBC_user,JDBC_password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }


}
