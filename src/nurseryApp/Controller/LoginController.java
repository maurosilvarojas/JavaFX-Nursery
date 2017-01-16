package nurseryApp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nurseryApp.Models.DBConnection;
import nurseryApp.Models.Parents;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    Connection connection=null;
    Statement statement = null;
    String userDB=null;

   //private Parents newParents ;



    @FXML
    private Pane rootPane;

    @FXML
    private Label homeLabel;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXPasswordField myPassword;

    @FXML
    private JFXTextField usernameField;



    @FXML
    private Pane loginBg;

    @FXML
    void onEnter(ActionEvent event) throws IOException {
        if (accessGranted()) {
            Pane homePane= FXMLLoader.load(getClass().getResource("../Views/Home.fxml"));
            rootPane.getChildren().setAll(homePane);
        } else {
            System.out.println("Access Denied");
        }
    }



    /* Initialize Home Screen */
    @FXML
     void loginButtonEvent(ActionEvent event) throws IOException {

        if (accessGranted()) {
            Pane homePane= FXMLLoader.load(getClass().getResource("../Views/Home.fxml"));
            rootPane.getChildren().setAll(homePane);

        } else {
            System.out.println("Access Denied");
        }
    }
    /* Exit Login Screen */
    @FXML
    void exitButtonEvent(ActionEvent event) {
        Stage stage =(Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private boolean accessGranted()
    {
        String newUser = usernameField.getText();
        String newPass = myPassword.getText();
        boolean access = false;
        try{
            connection = DBConnection.getConnection();
            if (connection != null){
                System.out.println("Conection succesfull");
                Statement st=null;
                String query="SELECT * FROM staffTable WHERE username="+"'"+newUser+"'" ;
                st= connection.createStatement();
                ResultSet resultSet=st.executeQuery(query);

                if (resultSet.next()){
                    userDB = resultSet.getString("username");
                    String employID = resultSet.getString("staffID");
                    String passDB = resultSet.getString("password");
                    if ((userDB.equals(newUser)) &&(passDB.equals(newPass))){
                        System.out.println("Access Granted");
                        AccessLog newAccess = new AccessLog();
                        newAccess.fillAccess(userDB,employID);
                        return true;
                    }else{
                        System.out.println("wrong credentials:"+userDB +" "+passDB);
                        String errorMsg="try again";
                        System.out.println("Access Denied");
                        //homeLabel.setTextFill(Color.red);
                        homeLabel.setText("Access Denied");
                        usernameField.setPromptText(errorMsg);
                        return false;
                    }

                }else{
                    homeLabel.setText("Access Denied");
                    System.out.println("Wrong Password");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return false;
    }



}
