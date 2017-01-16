package nurseryApp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nurseryApp.Models.Children;
import nurseryApp.Models.DBConnection;
import nurseryApp.Models.NurserySession;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by regga on 31/12/2016.
 */
public class SessionController implements Initializable{
    Connection connection=null;
    Statement statement = null;
    String userDB=null;
    String foundParentID = null;
    int foundChildID;
    int fouundChildDoB;

    NurseryNotifications notifyer = new NurseryNotifications();

    @FXML
    private DatePicker fromDate;


    @FXML
    private DatePicker toDate;

    @FXML
    private JFXButton bookSession;

    @FXML
    private JFXButton exitbookSession;

    @FXML
    private ChoiceBox<String> timeRange;

    @FXML
    private TextField childIDFinder;

    @FXML
    private JFXTextField foundTextField;

    @FXML
    private JFXTextField foundTextField2;

    @FXML
    void childIDFinderButtonEvent(ActionEvent event) {
        if (childIDFinder.getText().equals("") ){

            notifyer.showNotification(" Please Fill all details");
        }
        else{
            int id=Integer.parseInt(childIDFinder.getText());
            Children children = new Children(id);
            foundTextField.setText(children.getName());
            foundTextField2.setText(children.getSurname());
        }
    }


    @FXML
    void exitbookSessionEvent(ActionEvent event) {
        Stage stage =(Stage) exitbookSession.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void childIDFinderEvent (ActionEvent event) throws SQLException {

    }




    @FXML
    void bookSessionEvent(ActionEvent event) {
        if ( (childIDFinder.getText() != "" )&& (foundTextField.getText() != null) && (fromDate != null) && (toDate != null)&&(timeRange != null)){
            int id=Integer.parseInt(childIDFinder.getText());
            String from=fromDate.getValue().toString();
            String to=toDate.getValue().toString();
            String range=timeRange.getValue().toString();
            NurserySession newSession = new NurserySession(id,from,to,range);
            System.out.println("Price in Button:"+ newSession.getSessionPrice());
            newSession.bookSession();
        }
        else{
            notifyer.showNotification(" Please Fill all details");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeRange.getItems().addAll("All Day","Morning","Lunch","Afternoon","Pre School");
    }

 }





