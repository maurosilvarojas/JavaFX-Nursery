package nurseryApp.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nurseryApp.Models.Children;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * Created by regga on 13/01/2017.
 */
public class ChildrenTest {
    Children newChildren = new Children(2);
    @Test
    public void createChildrenList() throws Exception {
        ObservableList<Children> newList = FXCollections.observableArrayList();
        newList= newChildren.createChildrenList();

        int counter=0;

        try {
            Connection connection=null;
            Statement statement = null;
            ResultSet rs = null;
            connection = DBConnection.getConnection();
            if (connection != null) {
                Statement ps;
                ps = connection.createStatement();
                rs = ps.executeQuery("SELECT * FROM childrenTable");
                while (rs.next()) {
                    counter ++ ;
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(newList.size(),counter);
        System.out.println("list size: "+ newList.size()+" counter :"+ counter);
    }


    @Test
    public void getName() throws Exception {
        Children newChildren = new Children(2);
        assertEquals("joseph",newChildren.getName());
    }

    //@org.junit.Test
    @Test
    public void getChild_Id() throws Exception {
       assertEquals(3,newChildren.getChild_Id());
    }

    @Test
    public void getChildAge() throws Exception { // Date of birth : 2012-12-06
        assertEquals(4,newChildren.getChildAge());
    }

}